package gpulenta.quipu.product.integration;


import gpulenta.quipu.product.controller.ProductController;
import gpulenta.quipu.product.model.Category;
import gpulenta.quipu.product.model.Product;
import gpulenta.quipu.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = Collections.emptyList();
        when(productService.findAll()).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.getAllProducts();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        when(productService.save(product)).thenReturn(product);

        ResponseEntity<Product> response = productController.createProduct(product);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testUpdateProduct() {
        int productId = 1;
        Product product = new Product();
        when(productService.update(product)).thenReturn(product);

        ResponseEntity<Product> response = productController.updateProduct(productId, product);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }



    @Test
    public void testListProductAll() {
        List<Product> products = Collections.emptyList();
        when(productService.findAll()).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.listProduct(null);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testListProductByCategory() {
        Long categoryId = 1L;
        List<Product> products = Collections.emptyList();
        when(productService.findByCategory(Category.builder().id(categoryId).build())).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.listProduct(categoryId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
