package es.eoi.weekShop.services;

import java.util.List;

import es.eoi.weekShop.entities.Product;

public interface ProductService {
	
	public boolean create(Product newProduct);

	public boolean update(Integer index, Product newProduct);
	
	public List<Product> read(Product filter);
	
	public List<Product> readAll();
	
	public boolean delete();

}
