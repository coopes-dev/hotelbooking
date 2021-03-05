package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Collater {

	private static int getMark(String line) {
		int result=0;
		for (int idx=0;idx<line.length();idx++) {
			if (Character.isDigit(line.charAt(idx))) {
				result=result*10+(line.charAt(idx)-'0');
			}
		}
		return(result);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String FEEDBACK_DIR="C:\\comp220_1920\\eclipse-workspace_photon\\studentCode\\src";
		File file=new File(FEEDBACK_DIR);
		String files[]=file.list();
		int studentCount=0;
		double totalMark=0;
		String resultsFile="C:\\comp220_1920\\eclipse-workspace_photon\\studentCode\\resultsComp285v2.csv";
		BufferedWriter fw=null;
		try {
			fw = new BufferedWriter(new FileWriter(resultsFile));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(1);
		}
		for (int idx=0;idx<files.length;idx++) {
			studentCount++;
			System.out.println("Dir name is "+files[idx]);
			String id=files[idx].substring(files[idx].length()-9,files[idx].length());
			System.out.println("Student is "+id);
			// Now find the feedback file 
			int mark=0;
			try {
				FileReader fr=new FileReader(FEEDBACK_DIR+"\\"+files[idx]+"\\feedback.txt");
				BufferedReader br=new BufferedReader(fr);
				String line=br.readLine();
				while (line!=null) {
					line=line.toLowerCase();
					if (line.indexOf("total")!=-1) {
						//System.out.println("line is "+line);
						mark=getMark(line);
							break;
					}
					line=br.readLine();
					if (line==null) {
						System.err.println("Error could not find mark for ..."+id);
						System.exit(1);
					}
				}
				br.close();
				totalMark+=mark;
				System.out.println(id+","+mark);
				fw.write(id+","+mark+"\n");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			}
			
		}
		
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Av = "+totalMark/studentCount);
	

	}

}
