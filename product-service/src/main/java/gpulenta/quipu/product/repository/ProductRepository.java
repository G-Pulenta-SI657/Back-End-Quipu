package gpulenta.quipu.product.repository;

import gpulenta.quipu.product.model.Category;
import gpulenta.quipu.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByCategory(Category category);
    public Product findByProductName(String productName);
}
