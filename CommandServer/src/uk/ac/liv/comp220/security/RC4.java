package uk.ac.liv.comp220.security;


/**
 * Please not this algorithm has known weaknesses
 * and should not be used for applications where
 * security is critical
 * @author coopes
 *
 */


public class RC4 {
	private static final int BOX_COUNT=256;
	private short [] boxes=new short[BOX_COUNT];
	private String keyPhrase;
	int i=0;
	int j=0;
	
	/**
	 * Contructor for RC4 encryption
	 * @param keyPhrase
	 */
	public RC4(String keyPhrase) {
		this.keyPhrase=keyPhrase;
	}
	
	public String decrypt(String cipherText) {
		// set up the key boxes
		i=0;j=0;
		setKey();
		byte cipherData[]=Base64.decode(cipherText);;
		byte plainData[]=new byte[cipherData.length];
		for (int idx=0;idx<cipherData.length;idx++) {
			byte keyData = getNextKeyData();
			plainData[idx]=(byte)(cipherData[idx] ^ keyData);			
		}
		return(new String(plainData));
	}
	
	
	/**
	 * Encrypts plain text and returns cipher data in Base 64 format
	 * @param plainText  Message to be encrypted
	 * @return  Base64 encoded cipher text
	 */
	public String encrypt(String plainText) {
		// set up the key boxes
		i=0;j=0;
		setKey();
		byte plainData[]=plainText.getBytes();
		byte cipherData[]=new byte[plainData.length];
		
		for (int idx=0;idx<plainData.length;idx++) {
			byte keyData = getNextKeyData();
			cipherData[idx]=(byte)(plainData[idx] ^ keyData);			
		}
		return(Base64.encode(cipherData));
		
	}

	private byte getNextKeyData() {
		i = (i+1) % 256;
		j = (j+boxes[i]) % 256;
		short t=boxes[i];
		boxes[i]=boxes[j]; boxes[j]=t;
		byte keyData=(byte)(boxes[(boxes[i]+boxes[j]) % 256]);
		return keyData;
	}

	private void setKey() {
		byte keyData[]=keyPhrase.getBytes();	// convert to byte string
		for (int index=0;index<boxes.length;index++) {
			boxes[index]=keyData[index % keyData.length];
		}
	}
	
	

}
