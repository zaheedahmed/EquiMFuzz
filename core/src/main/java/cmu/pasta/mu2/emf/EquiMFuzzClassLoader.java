package cmu.pasta.mu2.emf;

import cmu.pasta.mu2.instrument.CartographyClassLoader;
import cmu.pasta.mu2.instrument.OptLevel;

import java.net.URL;

public class EquiMFuzzClassLoader extends CartographyClassLoader {
	/**
	 * Constructor
	 *
	 * @param paths
	 * @param mutableClasses
	 * @param parent
	 * @param opt
	 */

	public EquiMFuzzClassLoader(URL[] paths, String[] mutableClasses, ClassLoader parent, OptLevel opt) {
		super(paths, mutableClasses, parent, opt);
	}

	public void setMutantsCreated(){
		mutantsCreated = true;
	}

}
