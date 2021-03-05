package labtest;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class JUnitHelper {
	public static void showTest(Class classT) {
		JUnitCore junit = new JUnitCore();
		// Now try and load class file...
		junit.addListener(new TextListener(System.out));
		junit.run(classT);
	}

	
	public static boolean runTests(Class classT,boolean pass) {
		JUnitCore junit = new JUnitCore();
		// Now try and load class file...
		junit.addListener(new TextListener(System.out));
		Result result=junit.runClasses(classT);
		if (pass) {
			return(result.getFailureCount()==0);
		} 
		return(result.getFailureCount()>0);
	}

}
