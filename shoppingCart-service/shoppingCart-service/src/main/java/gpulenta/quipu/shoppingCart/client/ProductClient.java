package gpulenta.quipu.shoppingCart.client;

import gpulenta.quipu.shoppingCart.model.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/api/v1/products/findbyid/{id}")
    @CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "fallbackGetProductById")
    ResponseEntity<Product> getProductById(@PathVariable Long id);
    default ResponseEntity<Product> fallbackGetProductById(Long id, Exception e) {
        Product defaultProduct = new Product();
        defaultProduct.setId(-1L);
        defaultProduct.setProductName("Not found");
        defaultProduct.setProductDescription("Not found");
        defaultProduct.setProductPrice(0.0);
        defaultProduct.setProductRating(0);
        defaultProduct.setProductImageUrl("Not found");
        defaultProduct.setProductStock(0);
        return ResponseEntity.status(HttpStatus.OK).body(defaultProduct);
    }
}
