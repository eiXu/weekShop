package es.eoi.weekShop.menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import es.eoi.weekShop.entities.Product;
import es.eoi.weekShop.enums.Category;

public class Menu {

	static Scanner scan = new Scanner(System.in);;

	public static int mainMenu() {

		int option;

		System.out.println(" ------ Week Shop 1.0 -----");
		System.out.println("1.- Nuevo producto");
		System.out.println("2.- Listado de productos");
		System.out.println("3.- Buscar producto");
		System.out.println("4.- Modificar producto");
		System.out.println("5.- Eliminar producto");
		System.out.println("6.- Vender producto");
		System.out.println("7.- Super informe ejecutivo");
		System.out.println("0.- Salir");
		System.out.println("---------------------------");
		System.out.println("Introduce una opción:");

		try {
			option = Integer.parseInt(scan.nextLine());
		} catch (InputMismatchException e) {
			System.err.println("La opción debe de ser un número entero");
			option = -1;
		}
		// scan.close();
		return option;
	}

	public static Product newProductMenu() {

		String name;
		String description;
		Double price;
		Category category = Category.ALIMENTACION;
		int optionCategory = 1;
		Integer qAvailable;

		System.out.print("Introduce el nombre del producto: ");
		name = scan.nextLine();
		System.out.print("Introduce la descripción del producto \"" + name + "\": ");
		description = scan.nextLine();
		System.out.print("Introduce el precio del producto \"" + name + "\" (Por defecto 10€): ");
		try {
			price = Double.parseDouble(scan.nextLine());
			if (price < 1) {
				price = 10.0;
			}
		} catch (IllegalStateException e) {
			System.err.println("El valor introducido no es un valor numérico válido, se aplicará el valor por defecto");
			price = 10.0;
		}
		System.out.println("Selecciona una categoría (ALIMENTACION por defecto): ");
		System.out.println("1.- ALIMENTACION");
		System.out.println("2.- MATERIAL");
		System.out.println("3.- MECANICO");
		System.out.println("4.- LUJO");
		try {
			optionCategory = Integer.parseInt(scan.nextLine());
			if (optionCategory < 1 || optionCategory > 4) {
				optionCategory = 1;
			}
		} catch (InputMismatchException e) {
			System.err.println("La opción debe de ser un número entero entre 1 y 4, se aplicará el valor por defecto");
		}
		switch (optionCategory) {
		case 1:
			category = Category.ALIMENTACION;
			break;
		case 2:
			category = Category.MATERIAL;
			break;
		case 3:
			category = Category.MECANICO;
			break;
		case 4:
			category = Category.LUJO;
			break;
		}
		System.out.print("Introduce la cantidad disponible (1 por defecto): ");
		try {
			qAvailable = Integer.parseInt(scan.nextLine());
			if (qAvailable < 1) {
				qAvailable = 1;
			}
		} catch (InputMismatchException e) {
			System.err.println("La opción debe de ser un número entero mayor que 0, se aplicará el valor por defecto");
			qAvailable = 1;
		}

		Product product = new Product(name, description, price, category, qAvailable);

		return product;
	}

	public static Product findProductMenu() {

		int option;
		Product p = new Product(null, null, null, Category.ALIMENTACION, null);
		;
		int optionCategory;

		System.out.println("¿Por qué quieres buscar?");
		System.out.println("1.- Por código");
		System.out.println("2.- Por nombre");
		System.out.println("3.- Por categoría");

		try {
			option = Integer.parseInt(scan.nextLine());
		} catch (Exception e) {
			option = 0;
		}

		switch (option) {
		case 1:
			System.out.println("Introduce el código que quieres filtrar");
			try {
				p.setCode(Integer.parseInt(scan.nextLine()));
			} catch (Exception e) {

			}
			break;
		case 2:
			System.out.println("Introduce el nombre que quieres filtrar");
			p.setName(scan.nextLine());
			break;
		case 3:
			Category category = Category.ALIMENTACION;
			System.out.println("Introduce la categoría que quieres filtrar");

			try {
				System.out.println("Selecciona una categoría (ALIMENTACION por defecto): ");
				System.out.println("1.- ALIMENTACION");
				System.out.println("2.- MATERIAL");
				System.out.println("3.- MECANICO");
				System.out.println("4.- LUJO");

				optionCategory = Integer.parseInt(scan.nextLine());
			} catch (Exception e) {
				optionCategory = 1;
			}
			switch (optionCategory) {
			case 1:
				category = Category.ALIMENTACION;
				break;
			case 2:
				category = Category.MATERIAL;
				break;
			case 3:
				category = Category.MECANICO;
				break;
			case 4:
				category = Category.LUJO;
				break;
			}
			p.setCategory(category);
			break;
		default:
			System.err.println(" Opción incorrecta");
		}

		return p;
	}

	public static Product checkProductMenu() {

		Product p;

		System.out.print("Introduce el código del producto: ");

		try {
			p = new Product(null, null, null, Category.ALIMENTACION, null);
			p.setCode(Integer.parseInt(scan.nextLine()));
			return p;
		} catch (Exception e) {

		}
		return null;
	}

	public static Product updateProductMenu(Product oldProduct) {

		int option;

		do {
			option = 0;

			System.out.println("Este es el producto que quieres modificar");
			System.out.println(oldProduct);
			System.out.println("¿Qué quieres modificar?");
			System.out.println("1.- Su nombre");
			System.out.println("2.- Su descripción");
			System.out.println("3.- Su precio");
			System.out.println("4.- Su categoría");
			System.out.println("5.- Su cantidad disponible");
			System.out.println("0.- Salir");

			try {
				option = Integer.parseInt(scan.nextLine());

				switch (option) {
				case 1:
					System.out.print("Introduce el nuevo nombre: ");
					oldProduct.setName(scan.nextLine());
					break;
				case 2:
					System.out.print("Introduce la nueva descripción: ");
					oldProduct.setDescription(scan.nextLine());
					break;
				case 3:
					System.out.print("Introduce el nuevo precio (Valor numérico): ");
					oldProduct.setPrice(Double.parseDouble(scan.nextLine()));
					break;
				case 4:
					System.out.println("Selecciona una categoría (ALIMENTACION por defecto): ");
					System.out.println("1.- ALIMENTACION");
					System.out.println("2.- MATERIAL");
					System.out.println("3.- MECANICO");
					System.out.println("4.- LUJO");

					option = Integer.parseInt(scan.nextLine());

					switch (option) {
					case 1:
						oldProduct.setCategory(Category.ALIMENTACION);
						break;
					case 2:
						oldProduct.setCategory(Category.MATERIAL);
						break;
					case 3:
						oldProduct.setCategory(Category.MECANICO);
						break;
					case 4:
						oldProduct.setCategory(Category.LUJO);
						break;
					}
					break;
				case 5:
					System.out.print("Introduce la nueva cantidad disponible: ");
					oldProduct.setqAvailable(Integer.parseInt(scan.nextLine()));
					break;
				}

			} catch (Exception e) {
				System.err.println("Valor introducido no válido");
			}

		} while (option != 0);

		System.out.println("Se procederá a actualizar el producto con los siguientes valores");
		System.out.println(oldProduct);

		return oldProduct;
	}

	public static boolean deleteMenu(Product toDelete) {

		int option = 0;
		boolean isDeleted = false;

		System.out.println("¿Quieres eliminar el producto \"".concat(toDelete.getName()).concat("\"?"));
		System.out.println("1.- Sí");
		System.out.println("2.- No");

		try {
			option = Integer.parseInt(scan.nextLine());
			if (option == 1) {
				isDeleted = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return isDeleted;

	}
	
	public static Product sellProduct(Product toSell) {
		
		int unitsToSell = 0;
		
		System.out.println("¿Cuántas unidades quieres vender? (Disponibles = ".concat(String.valueOf(toSell.getqAvailable())).concat(")"));
		
		try {
			unitsToSell = Integer.parseInt(scan.nextLine());
			
			if(unitsToSell <= toSell.getqAvailable()) {
				toSell.setqAvailable(toSell.getqAvailable()-unitsToSell);
				toSell.setqSold(toSell.getqSold()+unitsToSell);
				System.out.println("Se han vendido ".concat(String.valueOf(unitsToSell)).concat(" unidades de ").concat(toSell.getName()));
			} else {
				System.out.println("La cantidad que intentas vender de \"".concat(toSell.getName()).concat("\" es superior a la disponible"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return toSell;
	}
}
