package es.eoi.weekShop.entities;

import es.eoi.weekShop.enums.Category;
import es.eoi.weekShop.enums.VAT;

public class Product implements Comparable<Product> {

	private Integer code;
	private String name;
	private String description;
	private VAT VAT;
	private Double price;
	private Integer qAvailable;
	private Integer qSold;
	private Category category;

	public Product(String name, String description, Double price, Category category, Integer qAvailable) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.qAvailable = qAvailable;
		this.qSold = 0;

		switch (category) {
		case ALIMENTACION:
			this.VAT = VAT.SUPERREDUCTED;
			break;
		case MATERIAL:
			this.VAT = VAT.REDUCTED;
			break;
		case MECANICO:
			this.VAT = VAT.GENERAL;
			break;
		case LUJO:
			this.VAT = VAT.GENERAL;
			break;
		}

	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public VAT getVAT() {
		return VAT;
	}

	public void setVAT(VAT vAT) {
		VAT = vAT;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getqAvailable() {
		return qAvailable;
	}

	public void setqAvailable(Integer qAvailable) {
		this.qAvailable = qAvailable;
	}

	public Integer getqSold() {
		return qSold;
	}

	public void setqSold(Integer qSold) {
		this.qSold = qSold;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return String.format(
				"Product [code=%s, name=%s, description=%s, VAT=%s, price=%s, qAvailable=%s, qSold=%s, category=%s]",
				code, name, description, VAT, price, qAvailable, qSold, category);
	}

	public int compareTo(Product p) {

		int order;

		if (this.getCode() > p.getCode()) {
			order = 1;
		} else if (this.getCode() < p.getCode()) {
			order = -1;
		} else {
			order = 0;
		}

		return order;
	}

}
