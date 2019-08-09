package es.eoi.weekShop.enums;

public enum VAT {
	REDUCTED(1.1), SUPERREDUCTED(1.04), GENERAL(1.21);

	private Double vat;

	private VAT(Double numVAT) {
		this.vat = numVAT;
	}

	public Double getVat() {
		return vat;
	}

}
