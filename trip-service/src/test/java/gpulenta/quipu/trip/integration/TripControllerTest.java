package gpulenta.quipu.trip.integration;

import gpulenta.quipu.trip.controller.TripController;
import gpulenta.quipu.trip.model.Trip;
import gpulenta.quipu.trip.service.TripService;
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
public class TripControllerTest {

    @InjectMocks
    private TripController tripController;

    @Mock
    private TripService tripService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllTrips() {
        List<Trip> trips = Collections.emptyList();
        when(tripService.findAll()).thenReturn(trips);

        ResponseEntity<List<Trip> > response = tripController.getAllTrips();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testCreateTrip() {
        Trip trip = new Trip();
        when(tripService.save(trip)).thenReturn(trip);

        ResponseEntity<Trip> response = tripController.createTrip(trip);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }


    @Test
    public void testGetTripsByUserId() {
        Long userId = 1L;
        List<Trip> trips = Collections.emptyList();
        when(tripService.findByUserId(userId)).thenReturn(trips);

        ResponseEntity<List<Trip>> response = tripController.getTripsByUserId(userId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}