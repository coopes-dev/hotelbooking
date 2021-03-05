package labtest;

public class Specification {
	private static int VERSION=2;	// which version of code is used, version=1 original, version=2 second sitting


	private java.util.Random rnd;
	private int minUsernameLength;
	private int maxUsernameLength;
	private int minPasswordLength;
	private String validPassword="ABCabc123456789012";
	private String validUsername="abcABCdef123456";
	private String specialChars = "";
	private char specialCandidates1[] = { '"', '@', '!', '#', '$', '%', '^', '&', '*', '(', ')' };

	private char specialCandidates2[] = { '?', '@', '!', '#', '$', '%', '^', '&', '*', '(', ')' };

	
	private int random(int range) {
		return (Math.abs(rnd.nextInt()) % range);
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
		char specialCandidates[]=null;
		switch (VERSION) {
			case 1 :
				specialCandidates=specialCandidates1;
				break;
			case 2 :
				specialCandidates=specialCandidates2;
				break;
			default :
			try {
				throw new Exception("Bad version number for spec");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			}
				break;
				
		}
		for (int idx=0;idx<3;idx++) {
			int offset=Math.abs(rnd.nextInt() % specialCandidates.length);
			while (specialCandidates[offset]=='A') {
				offset=Math.abs(rnd.nextInt() % specialCandidates.length);
			}
			specialChars=specialChars+specialCandidates[offset];
			specialCandidates[offset]='A';
		}
	}

	
	public Specification(String id) {
		makeSpec(id);
	}

	public int getMinUsernameLength() {
		return minUsernameLength;
	}

	public int getMaxUsernameLength() {
		return maxUsernameLength;
	}

	public int getMinPasswordLength() {
		return minPasswordLength;
	}

	public String getSpecialChars() {
		return specialChars;
	}

	public static void setVERSION(int vERSION) {
		VERSION = vERSION;
	}

}
