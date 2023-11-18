package gpulenta.quipu.shoppingCart.integration;

import gpulenta.quipu.shoppingCart.client.UserClient;
import gpulenta.quipu.shoppingCart.controller.ShoppingCartController;
import gpulenta.quipu.shoppingCart.model.ShoppingCart;
import gpulenta.quipu.shoppingCart.model.User;
import gpulenta.quipu.shoppingCart.repository.ShoppingCartRepository;
import gpulenta.quipu.shoppingCart.service.ShoppingCartService;
import gpulenta.quipu.shoppingCart.service.impl.ShoppingCartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ShoppingCartServiceImplTest {

    @Mock
    private ShoppingCartService shoppingCartService;

    private ShoppingCartController shoppingCartController;

    @BeforeEach
    public void setup() {
        shoppingCartController = new ShoppingCartController(shoppingCartService);
    }

    @Test
    public void testCreateShoppingCart() {
        ShoppingCart shoppingCart = new ShoppingCart(); // Crea un objeto de ejemplo para shoppingCart
        when(shoppingCartService.save(shoppingCart)).thenReturn(shoppingCart);

        ResponseEntity<ShoppingCart> response = shoppingCartController.createShoppingCart(shoppingCart);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(shoppingCart, response.getBody());
    }

    @Test
    public void testGetAllShoppingCarts() {
        when(shoppingCartService.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<Iterable<ShoppingCart>> response = shoppingCartController.getAllShoppingCarts();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetShoppingCartById() {
        Long id = 1L; // ID de ejemplo
        ShoppingCart shoppingCart = new ShoppingCart(); // Crea un objeto de ejemplo para shoppingCart
        when(shoppingCartService.findById(id)).thenReturn(shoppingCart);

        ResponseEntity<ShoppingCart> response = shoppingCartController.getShoppingCartById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(shoppingCart, response.getBody());
    }

    @Test
    public void testUpdateShoppingCart() {
        Long id = 1L; // ID de ejemplo
        ShoppingCart shoppingCart = new ShoppingCart(); // Crea un objeto de ejemplo para shoppingCart
        when(shoppingCartService.update(shoppingCart)).thenReturn(shoppingCart);

        ResponseEntity<ShoppingCart> response = shoppingCartController.updateShoppingCart(id, shoppingCart);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(shoppingCart, response.getBody());
    }

    @Test
    public void testGetShoppingCartByUserId() {
        Long userId = 1L; // ID de usuario de ejemplo
        ShoppingCart shoppingCart = new ShoppingCart(); // Crea un objeto de ejemplo para shoppingCart
        when(shoppingCartService.findByUserId(userId)).thenReturn(shoppingCart);

        ResponseEntity<ShoppingCart> response = shoppingCartController.getShoppingCartByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(shoppingCart, response.getBody());
    }

}
