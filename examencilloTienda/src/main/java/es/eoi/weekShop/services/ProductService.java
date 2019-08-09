package es.eoi.weekShop.services;

import java.util.List;

import es.eoi.weekShop.entities.Product;

public interface ProductService {
	
	public boolean create();

	public boolean update();
	
	public Product read();
	
	public List<Product> readAll();
	
	public boolean delete();

}
