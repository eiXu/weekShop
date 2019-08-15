package es.eoi.weekShop.repositories;

import java.util.List;

import es.eoi.weekShop.entities.Product;

public interface ProductRepository {
	
	public void create(Product newProduct);

	public boolean update(Product newProduct);
	
	public List<Product> read(Product filter);
	
	public List<Product> readAll();
	
	public boolean delete(Product toDeleteProduct);

}
