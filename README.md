# Detecting Equivalent Mutants Through Fuzzing (EquiMFuzz)

EquiMFuzz is a Plugin which can be used to detect equivalent Mutants through use of the functionalities 
from the [Mu2](https://github.com/cmu-pasta/mu2) project. Using the differential fuzzing of Mu2 we
aim to kill off any remaining non-equivalent mutants after a normal mutation testing run, thus the
remaining mutants should all be equivalent. The current implementation is a modified fork of Mu2, for that
reason naming and executing the tool is still very similar to Mu2.

EquiMFuzz is implemented as a maven plugin and is still in development, therefore changes and bugs
can still be present.



## Using EquiMFuzz
The project name is still Mu2 (from the fork), so to install EquiMFuzz, 
run the following in the mu2 root directory:

```
mvn install
```

After executing the mu2-core plugin is installed locally. 


### Using the EquiMFuzz Plugin

The core (**mu2-core**) directory contains modified functionality from the original Mu2. The added emf directory in the 
sources of mu2-core contain the main changes made to mu2. The EquiMFuzzGoal(extends AbstractMojo) is the entrypoint to the 
functionalities of our tool. With the implementation of an AbstractMojo a maven goal is provided and our tool
can be used as a maven plugin.

To try out the functions of equiMFuzz we can change into the demo directory (**mu2-demo**) and execute the 
plugin against one of the examples (Bisect).

### Example Run of EquiMFuzz

To start a demo run, change into the demo directory and execute the following command:

```
mvn clean test mu2-core:equiMFuzz -f pom.xml
```

Inside the demo directory a pom.xml is preconfigured to execute the equiMFuzz plugin against the
Bisect Example. Another configuration block is currently commented out, which can be used to run equiMFuzz
against another example (Triangle).


### Using EquiMFuzz for own projects
The configuration and prerequisites for the equiMFuzz goal is the same as the diff goal from Mu2, with
an additional parameter and Testclass needed (originalTestclass). 

The originalTestclass is the testclass which is mutation tested, e.g. the normal unittests against which
we usually will run our mutation tests. 
In our Bisect example the originalTestclass is located in
> demo/src/test/java/cmu.pasta.mu2.**test**.BisectTest

Furthermore, we need a differential fuzz testing class, which is used to differentiate the equivalent and
non-equivalent mutants. This test need the `@Diff` and `@Comparison` annotations instead of `@Test`.

In our Bisect example the originalTestclass is located in
> demo/src/test/java/cmu.pasta.mu2.**equiMFuzz**.BisectTest


The annotations are as described in the Mu2 project.

### Annotations

In the differential testing framework, each test is a pair of functions, one annotated with `@Diff` and the other with `@Comparison`.

The function annotated with `@Diff` should **not** be `static` and should return an object (neither void nor a primitive, though boxed primitives work).
The result of this diff function will be compared with other results using the comparison function (annotated with `@Comparison`). 

The comparison function should be `static`, take two arguments of the same type as the return type of any associated diff functions, and return a `Boolean` 
(`Boolean.TRUE` if the two inputs are equivalent, `Boolean.FALSE` otherwise).

Multiple functions with these annotations can be included in a single test class, so, to uniquely match the diff function with a comparison function, 
pass the name of the comparison function as an argument to `@Diff` as `cmp` (see below for example). If no argument is passed to `cmp`, `Objects.equals()` 
will be used as a default.

Each diff function is associated with up to one comparison function, but arbitrarily many diff functions may use a comparison function.

Example of a differential test for a function `populateList(List<Integer>)` when the comparison only cares about the size of the populated list:

```java
@Diff(cmp = "compare")
public List<Integer> testPopulate(List<Integer> input) {
    return populateList(input);
}

@Comparison
public static Boolean compare(List<Integer> list1, List<Integer> list2) {
    return list1.size() == list2.size();
}
```

### EquiMFuzz Goal

Running the equiMFuzz goal against custom programs we can use the following:

```sh
mvn clean test mu2-core:equiMFuzz -Dclass=package.class -Dmethod=method -Dincludes=prefix -DoriginalTestclass=originalTestclass.class
```
>The **class** parameter is the differential fuzz testing class, for Bisect it would be:
>>cmu.pasta.mu2.equiMFuzz.BisectTest
> 
>The **method** parameter is the differential fuzz testing method, for Bisect it would be:
>>testSqrt
> 
>The **includes** parameter is the class which will be mutated, for Bisect it would be:
>>cmu.pasta.mu2.Bisect
>
>The **originalTestclass** parameter is the originalTestClass, for Bisect it would be:
>>cmu.pasta.mu2.test.BisectTest

So to run our example as it would be a custom project we have to execute the following:

```sh
mvn clean test mu2-core:equiMFuzz -Dclass=cmu.pasta.mu2.equiMFuzz.BisectTest -Dmethod=testSqrt -Dincludes=cmu.pasta.mu2.Bisect -DoriginalTestclass=cmu.pasta.mu2.test.BisectTest
```
We can add a time limit to the fuzzing cycle with for example -Dtime=100s if we want the fuzzing 
only to run for 100 seconds.
The clean and test executions are not necessary if the testclasses are already compiled, but with it, 
we can guarantee to execute the plugin against the most recent version of the program.

During the beginning of the execution we will see the plugin print out how much mutants were killed/have 
survived the mutation testing, and then the fuzzing is started with the time limit provided. After the fuzzing 
the still remaining mutants and the number of additional killed mutants through fuzzing are shown in the console.

As of the state of development right now a time limit has to be set (either in the pom.xml or via commandline) to get the aforementioned console outputs


### After the Exection of EquiMFuzz
Just like Mu2 the inputseed which led to the killing of additional mutants are saved in target/fuzz-results as a corpus 
for future runs. In our case we can use the inputs to extend our existing tests and improve the testquality overall. 