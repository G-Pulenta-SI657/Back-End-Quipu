package gpulenta.quipu.trip.service;

import gpulenta.quipu.trip.model.Trip;

import java.util.List;

public interface TripService {
    List<Trip> findAll();

    Trip findById(Long id);

    Trip save(Trip trip);

    Trip update(Trip trip);

    void delete(Long id);

    List<Trip> findByUserId(Long userId);

}
