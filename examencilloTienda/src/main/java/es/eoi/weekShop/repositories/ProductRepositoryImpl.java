package es.eoi.weekShop.repositories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import es.eoi.weekShop.entities.Product;

public class ProductRepositoryImpl implements ProductRepository {

	private Gson gson;
	private String FILE;

	public ProductRepositoryImpl() {
		this.gson = new GsonBuilder().setPrettyPrinting().create();
		this.FILE = "dataBase.txt";
	}

	public void create(Product newProduct) {

		List<Product> myProducts = readAll();
		if (myProducts == null) {
			myProducts = new ArrayList<Product>();
		}
		myProducts.add(newProduct);
		writeJson(myProducts);

	}

	public boolean update(Integer index, Product newProduct) {

		boolean isUpdated = false;

		List<Product> myProducts = readAll();

		if (myProducts != null) {
			for (Product p : myProducts) {
				if (p.getName().equals(newProduct.getName()) || p.getCode() == index) {
					myProducts.remove(p);
				}
			}
			newProduct.setCode(index);
			myProducts.remove(index - 1);
			myProducts.add(newProduct);
			isUpdated = true;
			writeJson(myProducts);
		}

		return isUpdated;
	}

	public List<Product> read(Product filter) {

		List<Product> myProducts = readAll();
		List<Product> myProductsReturn = new ArrayList<Product>();

		if (myProducts != null) {
			for (Product p : myProducts) {

				if (filter.getCode() != null) {
					if (p.getCode() == filter.getCode()) {
						myProductsReturn.add(p);
					}
				} else if (filter.getName() != null) {
					if (p.getName().equals(filter.getName())) {
						myProductsReturn.add(p);
					}
				} else if (p.getCategory() == filter.getCategory()) {
					myProductsReturn.add(p);
				}

			}
		}

		return myProductsReturn;
	}

	public List<Product> readAll() {
		String productLines = "";
		try {
			FileReader reader = new FileReader(FILE);
			BufferedReader br = new BufferedReader(reader);

			String line;
			productLines = "";
			while ((line = br.readLine()) != null) {
				productLines = productLines + line;
			}
			br.close();
			reader.close();
		} catch (IOException e) {
			// System.err.println("No existe el archivo, se procede a crearlo");
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<ArrayList<Product>>() {
		}.getType();
		List<Product> products = gson.fromJson(productLines, listType);
		if (products != null && products.size() > 1) {
			Collections.sort(products);
		}
		return products;
	}

	public boolean delete(Product toDeleteProduct) {

		boolean isDeleted = false;

		List<Product> myProducts = readAll();

		for (Product p : myProducts) {
			if (toDeleteProduct.getName().equals(p.getName())) {
				toDeleteProduct = p;
				System.out.println("El objeto: " + p + " ha sido borrado");
				isDeleted = true;
			}
		}
		myProducts.remove(toDeleteProduct);

		writeJson(myProducts);

		return isDeleted;
	}

	private void writeJson(List<Product> myProducts) {
		try {
			FileWriter fw = new FileWriter(FILE, false);
			fw.write(gson.toJson(myProducts));
			fw.close();

		} catch (IOException e) {
			System.err.println("No se encuentra el archivo");
		}
	}

}