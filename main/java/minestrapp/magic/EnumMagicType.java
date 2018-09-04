package minestrapp.magic;

public enum EnumMagicType {
	NONE(0, "none", 16777215),
	WATER(1, "water", 9983),
	FIRE(2, "fire", 16711680),
	EARTH(3, "earth", 32526),
	WIND(4, "wind", 16777215),
	FROST(5, "frost", 65535),
	ELEC(6, "elec", 16580431),
	LIFE(7, "life", 8323072),
	ARCANE(8, "arcane", 7413116);
	
	private int id;
	private int color;
	private String name;
	
	private EnumMagicType(int id, String name, int color) {
		this.id = id;
		this.name = name;
		this.color = color;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getColor() {
		return this.color;
	}
	
	public static EnumMagicType getById(int id) {
	    for(EnumMagicType e : values()) {
	        if(e.id == id) {
	        	return e;
	        }
	    }
	    return NONE;
	}

}
