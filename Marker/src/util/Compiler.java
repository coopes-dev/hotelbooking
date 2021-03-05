package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;




public class Compiler {

	public static void compileFile(String sourceName) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		int result=compiler.run(null,null,System.err,"..\\studentCode\\src\\labtest\\RegistrationHelper.java");
		result=compiler.run(null,null,System.err,"..\\studentCode\\src\\labtest\\"+sourceName);
		System.out.println("Res is "+result);
	}
	

	public static void compileCommandLine(String id) {
		String classes="C:\\comp220_1920\\eclipse-workspace_photon\\Marker\\bin";
	   String command1="javac -d "+classes+" -cp lib\\junit-4.13-beta-1.jar;lib\\hamcrest-core-1.3.jar;lib\\hamcrest-library-1.3.jar;"+classes+";  src\\labtest\\RegistrationHelper.java";
	   String command2="javac -d "+classes+" -cp lib\\junit-4.13-beta-1.jar;lib\\hamcrest-core-1.3.jar;lib\\hamcrest-library-1.3.jar;"+classes+";  src\\labtest"+id+"\\RegistrationHelperTest.java";
	//   doCommand(command1);
	   doCommand(command2);
	   
	   
	}


	private static void doCommand(String command) {
		Runtime rt = Runtime.getRuntime();
        System.out.println("Executing ... "+command);		   
		   File dir=new File("C:\\comp220_1920\\eclipse-workspace_photon\\studentCode");
		   Process pr;
		try {
			pr = rt.exec(command,null, dir);
			InputStream stdIn = pr.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stdIn);
			BufferedReader br = new BufferedReader(isr);

			while (pr.isAlive()) {
				   try {
					 Thread.sleep(1000);
					 String line=br.readLine();
					 while (line!=null) {
						 System.out.println(line);
						 line=br.readLine();
					 }
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
			   System.out.println("Compiled");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
    
}
