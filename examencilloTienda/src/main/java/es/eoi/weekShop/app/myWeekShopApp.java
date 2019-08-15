package es.eoi.weekShop.app;

import java.util.List;

import es.eoi.weekShop.entities.Product;
import es.eoi.weekShop.menu.Menu;
import es.eoi.weekShop.services.ProductService;
import es.eoi.weekShop.services.ProductServiceImpl;

public class myWeekShopApp {

	public static void main(String[] args) {

		ProductService ps = new ProductServiceImpl();
		Product product;
		boolean isDone;
		int option;
		List<Product> products;

		/*
		 * Product p1 = new Product("Producto1", "descripcion", 5.0,
		 * Category.ALIMENTACION,10); Product p2 = new Product("Producto2",
		 * "descripcion", 5.0, Category.ALIMENTACION,5); Product p3 = new
		 * Product("Producto3", "descripcion", 5.0, Category.ALIMENTACION,6); Product p4
		 * = new Product("Producto4", "descripcion", 5.0, Category.ALIMENTACION,7);
		 * Product p5 = new Product("Producto5", "descripcion", 5.0,
		 * Category.ALIMENTACION,1);
		 * 
		 * ps.create(p1); ps.create(p2); ps.create(p3); ps.create(p4);
		 * 
		 * mostrar(ps);
		 * 
		 * ps.delete(p2);
		 * 
		 * mostrar(ps);
		 * 
		 * ps.create(p2);
		 * 
		 * mostrar(ps);
		 * 
		 * ps.delete(p3); ps.delete(p4);
		 * 
		 * mostrar(ps);
		 * 
		 * ps.create(p4); ps.create(p3);
		 * 
		 * mostrar(ps);
		 * 
		 * ps.update(3, p5);
		 * 
		 * mostrar(ps);
		 */

		do {
			option = Menu.mainMenu();
			switch (option) {
			case 1:	// 
				product = Menu.newProductMenu();
				isDone = ps.create(product);
				if (isDone) {
					System.out.println("Se ha creado correctamente");
				} else {
					System.out.println("No se ha podido crear el producto porque ya existía");
				}
				break;
			case 2:	// READALL
				mostrar(ps, ps.readAll());
				break;
			case 3:	// READ
				mostrar(ps, ps.read(Menu.findProductMenu()));
				break;
			case 4:	// UPDATE
				product = Menu.checkProductMenu();
				if (ps.update(product.getCode(), product)) {
					System.out.println("Se ha actualizado con éxito");
				} else {
					System.out.println("No se ha podido actualizar");
				}
				break;
			case 5:	// BORRAR
				ps.delete();
				break;
			case 6:	// SELL
				products = ps.read(Menu.checkProductMenu());
				((ProductServiceImpl) ps).sell(products.get(0));
				break;
			case 7:	// CHECK SOLD ITEMS
				((ProductServiceImpl) ps).report();
				break;
			}
		} while (option != 0);

		System.out.println("Finalizando el programa");

	}

	public static void mostrar(ProductService pr, List<Product> myProducts) {

		for (Product p : myProducts) {
			System.out.println(p);
		}

	}

}
