package myc45;

public class Val {
	public String valueName = "";
	public String itClass = "";
	public Val(String name, String inClass){
		this.valueName = new String(name);
		this.itClass = new String(inClass);
	}
	public boolean isNameEqual(Val inV){
		if(this.valueName.equals(inV.valueName)) return true;
		return false;
	}
}
