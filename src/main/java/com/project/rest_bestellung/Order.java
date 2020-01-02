package com.project.rest_bestellung;

public class Order {
    private String handlebartype;
    private String material;
    private String gearLevels;
    private String handle;
    
	public String getHandlebartype() {
		return handlebartype;
	}
	public void setHandlebartype(String handlebartype) {
		this.handlebartype = handlebartype;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getGearLevels() {
		return gearLevels;
	}
	public void setGearLevels(String gearLevels) {
		this.gearLevels = gearLevels;
	}
	public String getHandle() {
		return handle;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
    
	@Override
	public String toString() {
		System.out.println("Lenkertyp: " + this.handlebartype);
		System.out.println("Material: " + this.material);
		System.out.println("Schaltung: " + this.gearLevels);
		System.out.println("Griff: " + this.handle);
		return "";
	}

}
