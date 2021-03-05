package util;

import java.io.File;

import labtest.RegistrationHelper;
import labtest.Specification;

public class MutationController {
	public static void loadID(String id) {
		id=id.trim();
		String fname="C:\\comp220_1920\\eclipse-workspace_photon\\CourseworkGenerator\\labtest\\"+id+".zip";
		File f=new File(fname);
		/*if (!f.exists()) {
			System.err.println("Failed to verfy student..."+id);
			System.exit(1);
		}*/
		Specification spec=new Specification(id);
		RegistrationHelper helper=new RegistrationHelper(spec);
		
	}

}
