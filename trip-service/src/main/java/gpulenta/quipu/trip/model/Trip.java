package gpulenta.quipu.trip.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trip_origin", nullable = false, length = 50)
    private String origin;

    @Column(name = "trip_destination", nullable = false, length = 50)
    private String destination;

    @Column(name = "trip_date", nullable = false, length = 50)
    private String date;

    @Column(name = "user_id", nullable = false, length = 50)
    private Long userId;

    @Transient
    private User user;
}
