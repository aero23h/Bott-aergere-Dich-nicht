package bottAergereDichNicht;

public class ColorItem {
	private String code;
	private String name;
	
	public ColorItem() {
	}

	public ColorItem(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	// ################################################################################################################

	public String getCode() {
		return this.code;
	}

	public String getName() {
		return this.name;
	}
}
