package gpulenta.quipu.trip.service.impl;

import gpulenta.quipu.trip.client.UserClient;
import gpulenta.quipu.trip.model.Trip;
import gpulenta.quipu.trip.model.User;
import gpulenta.quipu.trip.repository.TripRepository;
import gpulenta.quipu.trip.service.TripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TripServiceImpl implements TripService {
    @Autowired
    TripRepository tripRepository;

    @Autowired
    UserClient userClient;

    @Override
    public List<Trip> findAll() {
        List<Trip> trips = tripRepository.findAll();

        for (Trip trip : trips) {
            Long userId = trip.getUserId();
            ResponseEntity<User> userResponse = userClient.getUserById(userId);

            if (userResponse.getStatusCode() == HttpStatus.OK) {
                trip.setUser(userResponse.getBody());
            }
        }

        return trips;
    }

    @Override
    public Trip findById(Long id) {
        Trip trip = tripRepository.findById(id).orElse(null);
        if (trip != null) {
            ResponseEntity<User> userResponse = userClient.getUserById(trip.getUserId());
            if (userResponse.getStatusCode() == HttpStatus.OK) {
                trip.setUser(userResponse.getBody());
            }
        }
        return trip;
    }

    @Override
    public Trip save(Trip trip) {
        return tripRepository.save(trip);
    }

    @Override
    public Trip update(Trip trip) {
        Trip tripToUpdate = tripRepository.findById(trip.getId()).orElse(null);
        if (tripToUpdate != null) {
            tripToUpdate.setDestination(trip.getDestination());
            tripToUpdate.setOrigin(trip.getOrigin());
            tripToUpdate.setDate(trip.getDate());
            tripToUpdate.setUserId(trip.getUserId());
            return tripRepository.save(tripToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        tripRepository.deleteById(id);
    }

    @Override
    public List<Trip> findByUserId(Long userId) {
        ResponseEntity<User> userResponse = userClient.getUserById(userId);
        if (userResponse.getStatusCode() != HttpStatus.OK || userResponse.getBody() == null) {

        }
        return tripRepository.findByUserId(userId);
    }


}
