package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import labtest.JUnitHelper;
import labtest.Mutation;
import labtest.RegistrationHelper;



public class Main {

	//private static final String CourseWorkDir="C:\\comp220_1920\\comp285_cw1";
	//private static final String CourseWorkDir="C:\\comp220_1920\\comp285_second_cw1";
	//private static final String CourseWorkDir="C:\\comp220_1920\\comp285_second_cwemail";
	
	private static final String CourseWorkDir="C:\\comp220_1920\\comp220_cw1";
	
	
	private static FileWriter feedback=null;
	
	
	private static void writeToFile(String message) {
		try {
			System.out.println(message);
			feedback.write(message+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// First list out all the directories containing the students work
		File file=new File(CourseWorkDir);
		String files[]=file.list();
		int studentCount=0;
		double totalPerc=0.0;
		double maxPerc=0.0;
		for (int idx=0;idx<files.length;idx++) {
			File studentDir=new File(CourseWorkDir+"\\"+files[idx]);
			studentCount++;
			String sfiles[]=studentDir.list();
			/*if ((sfiles.length!=1) && (!files[idx].equals("extras"))) {
				System.err.println("Could not determine zip file.... or zip file missing please clean up "+files[idx]);
				System.exit(1);
			}*/
			String zipName=studentDir+"\\"+sfiles[0];
			int zipPos=zipName.indexOf(".zip");
			if (zipPos==-1) {  // no zip found... don't bother
				continue;
			}
			String id=zipName.substring(zipPos-9,zipPos);
			if (!id.equals("201317989")) {
			   continue;	
			}
			System.out.println("Id is "+id);
			MutationController.loadID(id);
			String destDir="..\\studentCode\\src\\labtest"+id;
			File f=new File(destDir);
			f.mkdirs();
			String feedbackDir="..\\studentCode\\src\\labtest"+id;
			UnZipper.unzipAll(feedbackDir, zipName);
			if (!UnZipper.zipCopy("RegistrationHelperTest.java","test/src/labtest","..\\studentCode\\src\\labtest"+id, zipName,id)) {
			
			     System.err.println("Could not find test file... for students "+files[idx]);
			     System.exit(1);
			}
			try {	
				feedback=new FileWriter(destDir+"\\feedback.txt");
			} catch (IOException e1) {
				System.err.println("Failed to created feedback file...");
				e1.printStackTrace();
				System.exit(1);
			}
			System.out.println("Doing compile... for "+files[idx]);
			Compiler.compileCommandLine(id);
			int totalTests=RegistrationHelper.mutations.length-1;
			writeToFile("Feedback for "+id+"\n");
			
			double score=0;
			// now try and load up reg helper...
			try {
				Class targetCodeT=Class.forName("labtest"+id+".RegistrationHelperTest");
				
				System.out.println("Class loaded...running tests...");
				boolean ok=JUnitHelper.runTests(targetCodeT,true); // first test is should just pass
				if (!ok) {
					JUnitHelper.showTest(targetCodeT);
					writeToFile("Failed to correct version of code mark for testing is "+0);
					System.exit(1);
				
				} else {
				for (int mutationIndex=1;mutationIndex<Mutation.END_MUTATIONS.ordinal();mutationIndex++) {
					RegistrationHelper.setMutation(Mutation.values()[mutationIndex]);
					ok=JUnitHelper.runTests(targetCodeT,false); // all the buggy code should fail...
					if (!ok) {
						writeToFile("Bug "+mutationIndex+": Failed to discover bug "+RegistrationHelper.getMutationMessage());
					} else {
						score++;
						writeToFile("Bug "+mutationIndex+": Uncovered bug "+RegistrationHelper.getMutationMessage());
					}
				}
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			}
			double perc=Math.round((score/totalTests)*50);
			totalPerc+=perc;
			if (perc>maxPerc) {
				maxPerc=perc;
			}
			int perci=(int)perc;
			
			writeToFile("Score is "+score+"/"+totalTests+" = "+perci+" %  /  50%");
			writeToFile("");
			writeToFile("Format and style of JUnit file   /10%");
			writeToFile("");
			writeToFile("Production of correct outputs from Ant file    /20%");
			writeToFile("");
			writeToFile("Format readabiliy and good use of properties for build.xml    /20%");
			writeToFile("");
			writeToFile("Total mark      %");
			
			
			
			System.out.println("Done ..."+(idx+1));
			try {
				feedback.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			
		}
		
		
		System.out.println("Average score is "+totalPerc/studentCount);
		System.out.println("Max score is "+maxPerc);
		

	}

}
