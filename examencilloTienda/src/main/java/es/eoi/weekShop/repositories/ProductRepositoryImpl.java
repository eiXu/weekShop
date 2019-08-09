package es.eoi.weekShop.repositories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import es.eoi.weekShop.entities.Product;

public class ProductRepositoryImpl implements ProductRepository {

	private Gson gson;
	private String FILE;

	public ProductRepositoryImpl(Gson gson) {
		this.gson = gson;
		this.FILE = "dataBase.txt";
	}

	public boolean create(Product newProduct) {

		List<Product> myProducts = readAll();
		Boolean isOnDataBase = false;
		Integer newIndex = 0;

		if (myProducts != null) {
			for (Product p : myProducts) {
				if (p.getName().equals(newProduct.getName())) {
					isOnDataBase = true;
					break;
				}
			}

			if (!(isOnDataBase)) {

				for (; newIndex < myProducts.size(); newIndex++) {

				}
				newProduct.setCode(newIndex + 1);
				myProducts.add(newProduct);
			}
		} else {
			myProducts = new ArrayList<Product>();
			newProduct.setCode(newIndex + 1);
			myProducts.add(newProduct);
		}

		try {
			FileWriter fw = new FileWriter(FILE, false);
			fw.write(gson.toJson(myProducts));
			fw.close();

		} catch (IOException e) {
			System.err.println("No se encuentra el archivo");
		}

		return isOnDataBase;
	}

	public boolean update(Integer index, Product newProduct) {
		// TODO Auto-generated method stub
		return false;
	}

	public Product read(Product filter) {
		// TODO Auto-generated method stub
		return null;
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
			System.err.println("No existe el archivo");
		}

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Type listType = new TypeToken<ArrayList<Product>>() {
		}.getType();
		List<Product> products = gson.fromJson(productLines, listType);
		return products;
	}

	public boolean delete(Product toDeleteProduct) {
		// TODO Auto-generated method stub
		return false;
	}

}
