package cmu.pasta.mu2.emf;

import cmu.pasta.mu2.fuzz.DeadMutantsFilter;
import cmu.pasta.mu2.fuzz.MutantFilter;
import cmu.pasta.mu2.fuzz.MutationGuidance;
import cmu.pasta.mu2.instrument.MutationClassLoaders;
import cmu.pasta.mu2.instrument.MutationInstance;
import cmu.pasta.mu2.util.ArraySet;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class EquiMFuzzGuidance extends MutationGuidance {

	public EquiMFuzzGuidance(String testName, MutationClassLoaders mutationClassLoaders, Duration duration, Long trials, File outputDirectory, File seedInputDir, Random rand, List<MutantFilter> additionalFilters) throws IOException {
		super(testName, mutationClassLoaders, duration, trials, outputDirectory, seedInputDir, rand, additionalFilters);
	}

	public EquiMFuzzGuidance(String testName, MutationClassLoaders mutationClassLoaders, Duration duration, Long trials, File outputDirectory, File seedInputDir, Random rand) throws IOException {
		super(testName, mutationClassLoaders, duration, trials, outputDirectory, seedInputDir, rand);
	}


	protected void setDeadMutants(ArraySet deadMutants){
		this.deadMutants = deadMutants;
	}

	protected int getNumberOfDeadMutants(){
		return deadMutants.size();
	}

	protected List<MutationInstance> getSurvivingMutants(){
		List<MutationInstance> mutationInstances = getMutationInstances();
		DeadMutantsFilter filter = DeadMutantsFilter.getDeadMutantsFilter(this);
		return filter.filterMutants(mutationInstances);
	}
}
