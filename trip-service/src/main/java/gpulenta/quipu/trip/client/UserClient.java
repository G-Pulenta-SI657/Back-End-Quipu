package gpulenta.quipu.trip.client;

import gpulenta.quipu.trip.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/api/v1/users/findbyid/{id}")
    ResponseEntity<User> getUserById(@PathVariable Long id);
}
