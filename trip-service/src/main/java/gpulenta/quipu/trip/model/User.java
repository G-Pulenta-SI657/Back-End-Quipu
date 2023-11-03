package gpulenta.quipu.trip.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String address;
    private String email;
    private String phone;
}
