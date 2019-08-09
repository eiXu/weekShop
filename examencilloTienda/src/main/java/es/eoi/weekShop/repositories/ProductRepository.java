package es.eoi.weekShop.repositories;

import java.util.List;

import es.eoi.weekShop.entities.Product;

public interface ProductRepository {
	
	public boolean create(Product newProduct);

	public boolean update(Integer index, Product newProduct);
	
	public Product read(Product filter);
	
	public List<Product> readAll();
	
	public boolean delete(Product toDeleteProduct);

}
