package es.eoi.weekShop.app;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.eoi.weekShop.entities.Product;
import es.eoi.weekShop.enums.Category;
import es.eoi.weekShop.repositories.ProductRepository;
import es.eoi.weekShop.repositories.ProductRepositoryImpl;

public class myWeekShopApp {
	
	public static void main(String[] args) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		ProductRepository pr = new ProductRepositoryImpl(gson);
		pr.create(new Product("Producto1", "descripcion", 5.0, Category.ALIMENTACION));
		pr.create(new Product("Producto2", "descripcion", 5.0, Category.ALIMENTACION));
		pr.create(new Product("Producto3", "descripcion", 5.0, Category.ALIMENTACION));
		pr.create(new Product("Producto4", "descripcion", 5.0, Category.ALIMENTACION));
		List<Product> myProducts = pr.readAll() ;
		

		
		for( Product p : myProducts) {
			System.out.println(p);
		}
	}

}
