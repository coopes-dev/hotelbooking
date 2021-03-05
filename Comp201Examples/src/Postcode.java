import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Postcode {
	
	private String regexPC = "^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$";
	
	private Pattern patternPC = Pattern.compile(regexPC);
	
	private void validate(String code) {
		Matcher matcher = patternPC.matcher(code);
		if (!matcher.matches()) {
			throw new IllegalArgumentException("Bad Postcode format");
		}
	}
	
	public Postcode(String code) {
		super();
		validate(code);
		this.code = code;
	}

	private String code;

	public String getCode() {
		return code;
	}
	

}
