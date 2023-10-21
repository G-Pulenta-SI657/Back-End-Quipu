package gpulenta.quipu.shoppingCart.client;

import gpulenta.quipu.shoppingCart.model.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/api/v1/users/findbyid/{id}")
    @CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "fallbackGetUserById")
    ResponseEntity<User> getUserById(@PathVariable Long id);

    default ResponseEntity<User> fallbackGetUserById(Long id, Exception e) {
        User defaultUser = new User();
        defaultUser.setId(-1L);
        defaultUser.setUsername("Not found");
        defaultUser.setPassword("Not found");
        defaultUser.setName("Not found");
        defaultUser.setLastname("Not found");
        defaultUser.setAddress("Not found");
        defaultUser.setEmail("Not found");
        defaultUser.setPhone("Not found");
        return ResponseEntity.status(HttpStatus.OK).body(defaultUser);
    }
}

