package gpulenta.quipu.product.controller;

import gpulenta.quipu.product.model.Category;
import gpulenta.quipu.product.model.Product;
import gpulenta.quipu.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    // Find all products
    @Operation(summary = "Get all products", description = "Get all products details")
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        if (productService.findAll().isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
    // Find product by id
    @Operation(summary = "Get product by id", description = "Get a product details by ID")
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        if (productService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }
    // Create product
    @Operation(summary = "Create product", description = "Create a new product")
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }
    // Update product
    @Operation(summary = "Update product by ID", description = "Update an existing product's information by their ID")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
    }
    // Delete product
    @Operation(summary = "Delete product", description = "Delete an existing product")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        if (productService.findById(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        productService.delete(id);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }
    // Find products by category
    @Operation(summary = "Get products by category", description = "Get products by category object")
    @GetMapping("/findbycategory")
    public ResponseEntity<List<Product>> listProduct(@RequestParam(name = "categoryId", required = false) Long categoryId){
        List<Product> products = new ArrayList<>();
        if (null ==  categoryId){
            products = productService.findAll();
            if (products.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else{
            products = productService.findByCategory(Category.builder().id(categoryId).build());
            if (products.isEmpty()){
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.ok(products);
    }
}
