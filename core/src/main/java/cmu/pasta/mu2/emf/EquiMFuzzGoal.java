package cmu.pasta.mu2.emf;

import cmu.pasta.mu2.fuzz.*;
import cmu.pasta.mu2.instrument.*;
import cmu.pasta.mu2.util.ArraySet;
import edu.berkeley.cs.jqf.fuzz.junit.GuidedFuzzing;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import edu.berkeley.cs.jqf.fuzz.ei.ZestGuidance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static edu.berkeley.cs.jqf.instrument.InstrumentingClassLoader.stringsToUrls;
import static java.net.URLClassLoader.newInstance;

@Mojo(name="equiMFuzz",
		requiresDependencyResolution= ResolutionScope.TEST,
		defaultPhase= LifecyclePhase.VERIFY)
public class EquiMFuzzGoal extends AbstractMojo {

	@Parameter(defaultValue="${project}", required=true, readonly=true)
	MavenProject project;

	@Parameter(property="target", defaultValue="${project.build.directory}", readonly=true)
	private File target;

	/**
	 * The fully-qualified name of the test class containing methods
	 * to fuzz.
	 *
	 * <p>This class will be loaded using the Maven project's test
	 * classpath. It must be annotated with {@code @RunWith(JQF.class)}</p>
	 */
	@Parameter(property="class", required=true)
	private String testClassName;

	/**
	 * The name of the method to fuzz.
	 *
	 * <p>This method must be annotated with {@code @Fuzz}, and take
	 * one or more arguments (with optional junit-quickcheck
	 * annotations) whose values will be fuzzed by JQF.</p>
	 *
	 * <p>If more than one method of this name exists in the
	 * test class or if the method is not declared
	 * {@code public void}, then the fuzzer will not launch.</p>
	 */
	@Parameter(property="method", required=true)
	private String testMethod;

	/**
	 * Comma-separated list of FQN prefixes to forcibly include,
	 * even if they match an exclude.
	 *
	 * <p>Typically, these will be a longer prefix than a prefix
	 * in the excludes clauses.</p>
	 */
	@Parameter(property="includes")
	private String includes;

	@Parameter(property="targetIncludes")
	private String targetIncludes;

	/**
	 * The duration of time for which to run fuzzing.
	 *
	 * <p>
	 * If neither this property nor {@code trials} are provided, the fuzzing
	 * session is run for an unlimited time until the process is terminated by the
	 * user (e.g. via kill or CTRL+C).
	 * </p>
	 *
	 * <p>
	 * Valid time durations are non-empty strings in the format [Nh][Nm][Ns], such
	 * as "60s" or "2h30m".
	 * </p>
	 */
	@Parameter(property="time")
	private String time;

	/**
	 * The number of trials for which to run fuzzing.
	 *
	 * <p>
	 * If neither this property nor {@code time} are provided, the fuzzing
	 * session is run for an unlimited time until the process is terminated by the
	 * user (e.g. via kill or CTRL+C).
	 * </p>
	 */
	@Parameter(property="trials")
	private Long trials;

	/**
	 * A number to seed the source of randomness in the fuzzing algorithm.
	 *
	 * <p>
	 * Setting this to any value will make the result of running the same fuzzer
	 * with on the same input the same. This is useful for testing the fuzzer, but
	 * shouldn't be used on code attempting to find real bugs. By default, the
	 * seed is chosen randomly based on system state.
	 * </p>
	 */
	@Parameter(property="randomSeed")
	private Long randomSeed;

	/**
	 * The fuzzing engine.
	 *///TODO
	@Parameter(property="engine", defaultValue="mutation")
	private String engine;

	/**
	 * The name of the input directory containing seed files.
	 *
	 * <p>If not provided, then fuzzing starts with randomly generated
	 * initial inputs.</p>
	 */
	@Parameter(property="in")
	private String inputDirectory;

	/**
	 * The name of the output directory where fuzzing results will
	 * be stored.
	 *
	 * <p>The directory will be created inside the standard Maven
	 * project build directory.</p>
	 *
	 * <p>If not provided, defaults to
	 * <em>jqf-fuzz/${testClassName}/${$testMethod}</em>.</p>
	 */
	@Parameter(property="out")
	private String outputDirectory;

	/**
	 * Allows user to set optimization level for mutation-guided fuzzing.
	 * Does nothing if mutation engine is not used.
	 *
	 * <p> If not provided, defaults to {@code EXECUTION}.
	 */
	@Parameter(property="optLevel", defaultValue = "EXECUTION")
	private String optLevel;

	/**
	 * Allows user to input list of filters to be used in mutation-guided fuzzing.
	 *
	 * <p> If not provided, defaults to no filters.
	 */
	@Parameter(property="filters", defaultValue = "")
	private String filters;
	@Parameter(property="originalTestclass")
	private String originalTestclass;


	@Override
	public void execute() throws MojoExecutionException {
		MavenProject project = getMavenProject();
		Log log = getLog();
		log.info("Executing Tests for Project: " + project.getName());

		ClassLoader loader;
		EquiMFuzzGuidance guidance;
		PrintStream out = System.out;
//				= log.isDebugEnabled() ? System.out : null;
		Result result;

		Duration duration = null;
		if (time != null && !time.isEmpty()) {
			try {
				duration = Duration.parse("PT"+time);
			} catch (DateTimeParseException e) {
				throw new MojoExecutionException("Invalid time duration: " + time);
			}
		}

		if (outputDirectory == null || outputDirectory.isEmpty()) {
			outputDirectory = "fuzz-results" + File.separator + testClassName + File.separator + testMethod;
		}

		if(targetIncludes == null) {
			targetIncludes = "";
		}

		OptLevel ol;
		try {
			ol = OptLevel.valueOf(optLevel.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new MojoExecutionException("Invalid Mutation OptLevel!");
		}

		// Set filters to default value of empty string
		if (filters == null){
			filters = "";
		}

		// Parse string of filters for list of MutantFilters
		List<MutantFilter> filterList = parseFilters(filters);
		int mutants = 0;
		int successful = 0;
		int fail = 0;

		try {

			List<String> classpathElements = project.getTestClasspathElements();
			URL[] classPath = stringsToUrls(classpathElements.toArray(new String[0]));
			ClassLoader baseClassLoader = getClass().getClassLoader();
			String targetName = testClassName + "#" + testMethod;
			Random rnd = randomSeed != null ? new Random(randomSeed) : new Random();
			File seedsDir = inputDirectory == null ? null : new File(inputDirectory);
			File resultsDir = new File(target, outputDirectory);
			String[] mutableClasses = includes.split(",");

			URLClassLoader urlClassLoader= new URLClassLoader(classPath, baseClassLoader);
			Class<?> originalTestclazz = urlClassLoader.loadClass(originalTestclass);

			JUnitCore jUnitCore = new JUnitCore();
			result = jUnitCore.run(originalTestclazz);
			if(!result.wasSuccessful()){
				throw new RuntimeException("There are Test failures with the original run!");
			}
			System.out.println("Original run successful!");

			EquiMFuzzClassLoader equiMFuzzClassLoader = new EquiMFuzzClassLoader(classPath, mutableClasses ,baseClassLoader, ol);
			equiMFuzzClassLoader.findClass(mutableClasses[0]);
			equiMFuzzClassLoader.setMutantsCreated();

			ArraySet deadMutants = new ArraySet();

			for(MutationInstance mi : equiMFuzzClassLoader.getMutationInstances()){
				MutationClassLoader mutationClassLoader = new MutationClassLoader(mi, classPath, baseClassLoader);
				mutationClassLoader.findClass(mutableClasses[0]);

				Thread.currentThread().setContextClassLoader(baseClassLoader);
				originalTestclazz = mutationClassLoader.loadClass(originalTestclass);
				result = jUnitCore.run(originalTestclazz);


				mutants ++;
				if(result.wasSuccessful()){
					successful ++;
				}else{
					fail ++;
					deadMutants.add(mi.id);
				}
			}

			System.out.println("Mutants killed: " + deadMutants.size());
			System.out.println("Number of all Mutants: " + mutants);
			System.out.println("Number of surviving Mutants: " + successful);
			System.out.println("Number of killed Mutants: " + fail);



			MutationClassLoaders mcl = new MutationClassLoaders(classPath, includes, targetIncludes, ol, baseClassLoader);
			mcl.setCartographyClassLoader(equiMFuzzClassLoader);
			loader = mcl.getCartographyClassLoader();
			guidance = new EquiMFuzzGuidance(targetName, mcl, duration, trials, resultsDir, seedsDir, rnd, filterList);
			guidance.setDeadMutants(deadMutants);

			System.out.println("Detecting Equivalent Mutants Through Fuzzing ...");


		} catch (ClassNotFoundException e) {
			log.info(e);
			throw new MojoExecutionException("Testklasse nicht gefunden: " + originalTestclass, e);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (DependencyResolutionRequiredException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}

		try {
			result = GuidedFuzzing.run(testClassName, testMethod, loader, guidance, out);
			System.out.println("--------------------------------");
			System.out.println("Total Generated!");
			System.out.println("Number of all Mutants: " + mutants);
			System.out.println("--------------------------------");
			System.out.println("After Testing!");
			System.out.println("Number of surviving Mutants: " + successful);
			System.out.println("Number of killed Mutants: " + fail);

			System.out.println("After Fuzzing!");
			System.out.println("Dead Mutants after Fuzzing: " + guidance.getNumberOfDeadMutants());
			System.out.println("Remaining Mutants: " + guidance.getSurvivingMutants());

		} catch (ClassNotFoundException e) {
			throw new MojoExecutionException("Could not load test class", e);
		} catch (IllegalArgumentException e) {
			throw new MojoExecutionException("Bad request", e);
		} catch (RuntimeException e) {
			throw new MojoExecutionException("Internal error", e);
		}

	}

	private MavenProject getMavenProject() {
		return (MavenProject) getPluginContext().get("project");
	}

	private List<MutantFilter> parseFilters (String filterSpec) throws MojoExecutionException{
		// Initialize empty list of filters
		List<MutantFilter> filterList = new ArrayList<>();
		int i = 0;
		char[] filterChars = filters.toCharArray();
		String current = "";
		Filter f;
		int len = filterChars.length;

		// Parse input string (of format "filter1:arg1,arg2,...,filter2:arg1,arg2,...") to create list of filters
		while(i < len){
			char c = filterChars[i];
			if (c == ':') {
				try {
					f = Filter.valueOf(current.toUpperCase());
				} catch (IllegalArgumentException e) {
					throw new MojoExecutionException("Invalid Mutation Filter!");
				}
				if (f == Filter.KRANDOM){

					i++;
					String arg = "";
					while (i < len && filterChars[i] != ','){
						arg += filterChars[i];
						i ++;
					}
					try {
						filterList.add(new KRandomFilter(Integer.parseInt(arg)));
					} catch (NumberFormatException e) {
						throw new MojoExecutionException("Invalid Mutation Filter Argument(s)!");
					}

				} else if (f == Filter.KLEASTEXEC){

					i++;
					String arg = "";
					while (i < len && filterChars[i] != ','){
						arg += filterChars[i];
						i ++;
					}
					try {
						filterList.add(new KLeastExecutedFilter(Integer.parseInt(arg)));
					} catch (NumberFormatException e) {
						throw new MojoExecutionException("Invalid Mutation Filter Argument(s)!");
					}

				} else if (f == Filter.FILE) {

					i++;
					String arg = "";
					while (i < len && filterChars[i] != ','){
						arg += filterChars[i];
						i ++;
					}
					try {
						filterList.add(new FileMutantFilter(arg));
					} catch (FileNotFoundException e) {
						throw new MojoExecutionException("Invalid Mutation Filter Argument(s)!");
					}
				}
				current = "";
			} else {
				current += c;
			}
			i += 1;
		}

		return filterList;
	}
}
