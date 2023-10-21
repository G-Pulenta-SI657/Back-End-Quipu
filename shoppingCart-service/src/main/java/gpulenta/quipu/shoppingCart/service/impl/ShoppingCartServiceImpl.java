package gpulenta.quipu.shoppingCart.service.impl;

import gpulenta.quipu.shoppingCart.client.UserClient;
import gpulenta.quipu.shoppingCart.model.ShoppingCart;
import gpulenta.quipu.shoppingCart.model.User;
import gpulenta.quipu.shoppingCart.repository.ShoppingCartRepository;
import gpulenta.quipu.shoppingCart.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    private final UserClient userClient;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserClient userClient) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userClient = userClient;
    }

    @Override
    public ShoppingCart findByUserId(Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId);
        if (shoppingCart != null) {
            ResponseEntity<User> userResponse = userClient.getUserById(shoppingCart.getUserId());
            if (userResponse.getStatusCode() == HttpStatus.OK) {
                shoppingCart.setUser(userResponse.getBody());
            }
        }
        return shoppingCart;
    }

    @Override
    public ShoppingCart findById(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElse(null);
        if (shoppingCart != null) {
            ResponseEntity<User> userResponse = userClient.getUserById(shoppingCart.getUserId());
            if (userResponse.getStatusCode() == HttpStatus.OK) {
                shoppingCart.setUser(userResponse.getBody());
            }
        }
        return shoppingCart;
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        ShoppingCart shoppingCartToUpdate = shoppingCartRepository.findById(shoppingCart.getId()).orElse(null);
        if (shoppingCartToUpdate != null) {
            shoppingCartToUpdate.setCartStatus(shoppingCart.getCartStatus());
            shoppingCartToUpdate.setUserId(shoppingCart.getUserId());
            return shoppingCartRepository.save(shoppingCartToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        shoppingCartRepository.deleteById(id);
    }

    @Override
    public List<ShoppingCart> findAll() {
        List<ShoppingCart> shoppingCarts = shoppingCartRepository.findAll();

        for (ShoppingCart shoppingCart : shoppingCarts) {
            ResponseEntity<User> userResponse = userClient.getUserById(shoppingCart.getUserId());
            if (userResponse.getStatusCode() == HttpStatus.OK) {
                shoppingCart.setUser(userResponse.getBody());
            }
        }
        return shoppingCarts;
    }
}
