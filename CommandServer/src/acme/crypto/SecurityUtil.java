package acme.crypto;

public final class SecurityUtil {
	public static String makeResponse(String challenge,String password) {
		IdeaCipher cipher=new IdeaCipher(password);
		byte [] plain=challenge.getBytes();
		int len=plain.length;
		int padding=8-(len % 8);
		len+=padding;
		byte [] plainBuffer=new byte[len];
		byte [] cipherBuffer=new byte[len];
		for (int idx=0;idx<plain.length;idx++) {
			plainBuffer[idx]=plain[idx];
		}
		// We now have our data rwady for enctyption in 8 byte blocks
		for (int idx=0;idx<len;idx+=8) {
		    cipher.encrypt(plainBuffer,idx, cipherBuffer,idx);
		}
		return(Base64.encode(cipherBuffer));
	}

}
