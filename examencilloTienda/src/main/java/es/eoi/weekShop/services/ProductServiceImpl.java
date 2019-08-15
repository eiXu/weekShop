package es.eoi.weekShop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import es.eoi.weekShop.entities.Product;
import es.eoi.weekShop.enums.Category;
import es.eoi.weekShop.menu.Menu;
import es.eoi.weekShop.repositories.ProductRepository;
import es.eoi.weekShop.repositories.ProductRepositoryImpl;

public class ProductServiceImpl implements ProductService {

	private ProductRepository pr;

	public ProductServiceImpl() {
		pr = new ProductRepositoryImpl();
	}

	public boolean create(Product newProduct) {
		List<Product> myProducts = readAll();
		Boolean isOnDataBase = false;
		Integer newIndex = 1;

		if (myProducts != null) {
			for (Product p : myProducts) {
				if (p.getName().equals(newProduct.getName())) {
					isOnDataBase = true;
					break;
				}
			}

			if (!(isOnDataBase)) {

				for (int i = 0; i < myProducts.size(); i++) {
					if (myProducts.get(i).getCode() != (newIndex)) {
						newProduct.setCode(newIndex);
						pr.create(newProduct);
						isOnDataBase = true;
						break;
					} else {
						newIndex++;
					}
				}

				if (myProducts.size() < newIndex) {
					newProduct.setCode(newIndex);
					pr.create(newProduct);
					isOnDataBase = true;
				}

			}
		} else {
			myProducts = new ArrayList<Product>();
			newProduct.setCode(newIndex);
		}

		// pr.create(newProduct);

		return isOnDataBase;
	}

	public boolean update(Integer index, Product newProduct) {

		boolean isUpdated = false;
		List<Product> myProducts = read(newProduct);
		Scanner scan = new Scanner(System.in);

		if (myProducts != null) {
			System.out.println("¿Quieres modificar el producto " + myProducts.get(0).getName() + "?");
			System.out.println("1.- Sí");
			System.out.println("2.- No");
			int option = Integer.parseInt(scan.nextLine());
			if (option == 1) {
				isUpdated = pr.update(Menu.updateProductMenu(myProducts.get(0)));
			}
		}

		return isUpdated;
	}

	public List<Product> read(Product filter) {
		return pr.read(filter);
	}

	public List<Product> readAll() {
		return pr.readAll();
	}

	public boolean delete() {

		List<Product> toDelete = read(Menu.checkProductMenu());
		boolean isDeleted = false;

		if (Menu.deleteMenu(toDelete.get(0))) {
			isDeleted = pr.delete(toDelete.get(0));
			System.out.println("El objeto: " + toDelete.get(0).getName() + " ha sido borrado");
		} else {
			System.out.println("El producto no se ha borrado");
		}

		return isDeleted;
	}

	public boolean sell(Product product) {

		product = Menu.sellProduct(product);

		return pr.update(product);
	}

	public boolean report() {

		Double totalSold = 0.0;
		Double VAT = 0.0;

		Product filter = new Product(null, null, null, Category.ALIMENTACION, null);
		List<Product> food = read(filter);

		filter.setCategory(Category.MATERIAL);
		List<Product> materials = read(filter);

		filter.setCategory(Category.MECANICO);
		List<Product> mechanics = read(filter);

		filter.setCategory(Category.LUJO);
		List<Product> luxuries = read(filter);

		printReport(food);

		printReport(materials);

		printReport(mechanics);

		printReport(luxuries);

		return true;
	}

	private void printReport(List<Product> myProducts) {

		Double totalSold = 0.0;
		Double VAT = 0.0;
		boolean anySold = false;

		if (myProducts.size() > 0) {
			for (Product p : myProducts) {
				if (p.getqSold() > 0) {
					anySold = true;
					break;
				}
			}
			if (anySold) {
				System.out.println(myProducts.get(0).getCategory());
			}

			for (Product p : myProducts) {
				VAT = p.getVAT().getVat();
				if (p.getqSold() > 0) {
					System.out.println(String.format("%04d", p.getCode()).concat(" - ")
							.concat(String.format("%-55s", p.getName()).replace(' ', '.')).concat(" Total sin IVA: ")
							.concat(String.valueOf(p.getPrice())).concat("€ / Total con IVA: ")
							.concat(String.valueOf(p.getPrice() * p.getVAT().getVat())).concat("€"));
					totalSold += p.getPrice();
				}
			}
			if (totalSold > 0.0) {
				System.out.println("TOTAL".concat(String.format("%57s", "")).replace(' ', '.')
						.concat(" Total sin IVA: ").concat(String.valueOf(totalSold)).concat("€ / Total con IVA: ")
						.concat(String.valueOf(totalSold * VAT)).concat("€"));
				System.out.println();
			}

		}

	}

}
