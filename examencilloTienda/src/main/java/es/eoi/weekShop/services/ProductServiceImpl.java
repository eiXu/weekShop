package es.eoi.weekShop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import es.eoi.weekShop.entities.Product;
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
			pr.create(newProduct);
			isOnDataBase = true;
		}

		//pr.create(newProduct);
		
		return isOnDataBase;
	}

	public boolean update(Integer index, Product newProduct) {

		List<Product> myProducts = read(newProduct);
		Scanner scan = new Scanner(System.in);
		
		if(myProducts != null) {
			System.out.println("¿Quieres modificar el producto "+myProducts.get(0).getName()+"?");
			System.out.println("1.- Sí");
			System.out.println("2.- No");
			int option = Integer.parseInt(scan.nextLine());
			// SI
			//	Llamar menu que rellene el producto newProduct
			// NO
			//	Salir
		}
		
		return exists;
	}

	public List<Product> read(Product filter) {
		return pr.read(filter);
	}

	public List<Product> readAll() {
		return pr.readAll();
	}

	public boolean delete(Product toDeleteProduct) {
		return pr.delete(toDeleteProduct);
	}

}
