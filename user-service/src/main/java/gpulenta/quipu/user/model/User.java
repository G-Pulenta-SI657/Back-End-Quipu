package gpulenta.quipu.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_username", nullable = false, length = 30)
    private String username;
    @Column(name = "user_password", nullable = false, length = 30)
    private String password;
    @Column(name = "user_name", nullable = false, length = 30)
    private String name;
    @Column(name = "user_lastname", nullable = false, length = 30)
    private String lastname;
    @Column(name = "user_address", nullable = false, length = 50)
    private String address;
    @Column(name = "user_email", nullable = false, length = 50)
    private String email;
    @Column(name = "user_phone", nullable = false, length = 9)
    private String phone;
}
