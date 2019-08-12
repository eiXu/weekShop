package es.eoi.weekShop.menu;

import java.util.InputMismatchException;
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
		Product p = new Product(null, null, null, Category.ALIMENTACION, null);;
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
			p = new Product(null,null,null,Category.ALIMENTACION,null);
			p.setCode(Integer.parseInt(scan.nextLine()));
			return p;
		}catch (Exception e) {
			
		}
		return null;
	}
	
	public static void updateProductMenu(Product p) {
		
	}
	
}
