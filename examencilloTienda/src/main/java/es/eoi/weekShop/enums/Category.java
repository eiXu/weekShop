package es.eoi.weekShop.enums;

public enum Category {

	ALIMENTACION("ALIMENTACION"), MATERIAL("MATERIAL"), MECANICO("MECANICO"), LUJO("LUJO");

	private String name;

	private Category(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
