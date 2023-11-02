package gpulenta.quipu.product.service;

import gpulenta.quipu.product.model.Category;
import gpulenta.quipu.product.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();
    public Product findById(Long id);
    public Product save(Product product);
    public Product update(Product product);
    public void delete(Long id);
    public List<Product> findByCategory(Category category);
    public Product getProductByProductName(String productName);
}
