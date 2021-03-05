package labtest;

public class RunTests {

	public static void main(String[] args) {
		String id="201448022";
		Specification.setVERSION(1); // first sitting test
		Specification spec=new Specification(id); // set up spec for tests
		RegistrationHelper helper=new RegistrationHelper(spec);
		int totalTests=RegistrationHelper.mutations.length-1;
		
		int score=0;
		for (int idx=0;idx<Mutation.END_MUTATIONS.ordinal();idx++) {		
			try {
				boolean pass=true;
				//idx=Mutation.MIN_PASSWORD_TOO_LONG.ordinal();
				if (idx>0) pass=false;
				Class testClass=Class.forName("labtest.labtest201448022.RegistrationHelperTest");
				RegistrationHelper.setMutation(Mutation.values()[idx]);
				boolean ok=JUnitHelper.runTests(testClass, pass);
				System.out.print(""+idx+" :");
				if (ok) {
					if (idx>0) {
 					   score++;
					}
					System.out.println("Uncovered bug "+RegistrationHelper.getMutationMessage());
				} else {
					if (idx==0) {
						System.err.println("Test fails with good code score is 0");
						JUnitHelper.showTest(testClass);
						System.exit(1);
					}
					System.out.println("Failed to ucover "+RegistrationHelper.getMutationMessage());
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				
		}
		System.out.println("Score is "+score+" total test is "+totalTests);

	}

}
