package comp220;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;



public class Specification {
	private java.util.Random rnd;
	private int minUsernameLength;
	private int maxUsernameLength;
	private int minPasswordLength;
	private String validPassword="ABCabc123456789012";
	private String validUsername="abcABCdef123456";
	private String specialChars = "";
	private char specialCandidates[] = { '?', '@', '!', '#', '$', '%', '^', '&', '*', '(', ')' };

	private String spec = "";
	
	private Vector <String> specLines=new <String>Vector();

	public String getSpec() {
		return (spec);
	}

	private int random(int range) {
		return (Math.abs(rnd.nextInt()) % range);
	}

	public Specification(String id) {
		makeSpec(id);	
		
		
		BufferedReader reader =null;
		String line=null;
		try {
			reader=new BufferedReader(new FileReader("part1.txt"));
			line = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (line!=null) {
			spec+=line+"\n";
			specLines.add(line);
			try {
				line=reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		specLines.add("\n The following are the validation rules:");
		spec+="\n The following are the validation rules:";
		specLines.add("\n1.	If either the username or password are null then the method will return false.");
		spec+="\n1.	If either the username or password are null then the method will return false.";
		specLines.add("\n2.	The username must be at least "+minUsernameLength+" characters long and must start with an alphabetic character (A-Z, a-z).");
		spec+="\n2.	The username must be at least "+minUsernameLength+" characters long and must start with an alphabetic character (A-Z, a-z).";
		specLines.add("\n3.	The username must be no more than "+maxUsernameLength+" characters long.");
		spec+="\n3.	The username must be no more than "+maxUsernameLength+" characters long.";
		specLines.add("\n4.	The password must be at least "+minPasswordLength+" characters long.");		
		spec+="\n4.	The password must be at least "+minPasswordLength+" characters long.";
		specLines.add("\n5.	The password must contain at least one lower case letter.");		
		spec+="\n5.	The password must contain at least one lower case letter.";
		specLines.add("\n6.	The password must contain at least one upper case letter.");
		spec+="\n6.	The password must contain at least one upper case letter.";
		specLines.add("\n7.	The password must contain at least one a digit.");
		spec+="\n7.	The password must contain at least one a digit.";
		specLines.add("\n8.	The password must have a special character one of the following:    "+specialChars);
		spec+="\n8.	The password must have a special character one of the following:    "+specialChars;
		specLines.add("");
		specLines.add("");
		spec+="\n\n";
		reader =null;
		line=null;
		try {
			reader=new BufferedReader(new FileReader("part2.txt"));
			line = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (line!=null) {
			spec+=line+"\n";
			specLines.add(line);
			try {
				line=reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Main.writeToPDF("Student ID "+id,specLines);
		ZipWriter writer=new ZipWriter(id);
		
		
		
		
		writer.copeToZipFile("labtest.pdf","labtest.pdf");
		
			
		
		
		// Now we generate the source code...
		String source="";
		reader =null;
		line=null;
		try {
			reader=new BufferedReader(new FileReader("src\\labtest\\RegistrationHelper.java"));
			line = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (line!=null) {
			boolean done=false;	// has line been processed
			if (line.indexOf("int MINUSERNAMELEN")!=-1) {
				source+="private int MINUSERNAMELEN="+minUsernameLength+";\n";
				done=true;
			}
			if (line.indexOf("int MAXUSERNAMELEN")!=-1) {
				source+="private int MAXUSERNAMELEN="+maxUsernameLength+";\n";
				done=true;
			}
			if (line.indexOf("int MINPASSWORDLEN")!=-1) {
				source+="private int MINPASSWORDLEN="+minPasswordLength+";\n";
				done=true;
			}
			if (line.indexOf("public RegistrationHelper()")!=-1) {
				source+=line;
				for (int idx=0;idx<specialChars.length();idx++) {
					source+="specialCharacters.addElement('"+specialChars.charAt(idx)+"');\n";
				}
				done=true;
			}
			if (!done) {
				source+=line+"\n";
			}
			try {
				line = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			
			
			
		}
		
		
		
		
		
		//  specialCharacters.addElement(')');
		writer.writeToZipFile("RegistrationHelper.java",source);
		this.validPassword+=specialChars.charAt(0); // stick a special char on
		this.validUsername=this.validUsername.substring(0,this.minUsernameLength+1)+specialChars.charAt(1);
		reader =null;
		line=null;
		String testCode="";
		try {
			reader=new BufferedReader(new FileReader("src\\labtest\\RegistrationHelperTest.java"));			
			line = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (line!=null) {
			if (line.indexOf("checking correct credentials")!=-1) {
				line="        assertTrue(\"checking correct credentials\",helper.checkUsernamePassword(\""+validUsername+"\",\""+validPassword+"\"));\r\n";
			}
			testCode+=line+"\n";
			try {
				line = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		writer.writeToZipFile("RegistrationHelperTest.java",testCode);
		writer.close();
		
	}

	private void makeSpec(String id) {
		long seed=0;
		for (int idx=0;idx<id.length();idx++) {
			seed=seed*256+id.charAt(idx);
		}
		rnd=new java.util.Random(seed);
		// Now set up the specification
		minUsernameLength=(random(3))+8;
		maxUsernameLength=random(5)+minUsernameLength+2;
		minPasswordLength=random(5)+10;
		for (int idx=0;idx<3;idx++) {
			int offset=Math.abs(rnd.nextInt() % specialCandidates.length);
			while (specialCandidates[offset]=='A') {
				offset=Math.abs(rnd.nextInt() % specialCandidates.length);
			}
			specialChars=specialChars+specialCandidates[offset];
			specialCandidates[offset]='A';
		}
	}
	
}
