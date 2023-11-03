package gpulenta.quipu.offer.service.impl;

import gpulenta.quipu.offer.client.UserClient;
import gpulenta.quipu.offer.model.Offer;
import gpulenta.quipu.offer.model.User;
import gpulenta.quipu.offer.repository.OfferRepository;
import gpulenta.quipu.offer.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    private final UserClient userClient;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, UserClient userClient) {
        this.offerRepository = offerRepository;
        this.userClient = userClient;
    }
    @Override
    public Offer findByUserId(Long userId) {
        Offer offer = offerRepository.findByUserId(userId);
        if (offer != null) {
            ResponseEntity<User> userResponse = userClient.getUserById(offer.getUserId());
            if (userResponse.getStatusCode() == HttpStatus.OK) {
                offer.setUser(userResponse.getBody());
            }
        }
        return offer;
    }

    @Override
    public Offer findById(Long id) {
        Offer offer = offerRepository.findById(id).orElse(null);
        if (offer != null) {
            ResponseEntity<User> userResponse = userClient.getUserById(offer.getUserId());
            if (userResponse.getStatusCode() == HttpStatus.OK) {
                offer.setUser(userResponse.getBody());
            }
        }
        return offer;
    }

    @Override
    public Offer save(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public Offer update(Offer offer) {
        Offer offerToUpdate = offerRepository.findById(offer.getId()).orElse(null);
        if (offerToUpdate != null) {
            offerToUpdate.setUserId(offer.getUserId());
            offerToUpdate.setOfferPrice(offer.getOfferPrice());
            offerToUpdate.setOfferStatus(offer.getOfferStatus());
            return offerRepository.save(offerToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public List<Offer> findAll() {
        List<Offer> offers = offerRepository.findAll();

        for (Offer offer : offers) {
            ResponseEntity<User> userResponse = userClient.getUserById(offer.getUserId());
            if (userResponse.getStatusCode() == HttpStatus.OK) {
                offer.setUser(userResponse.getBody());
            }
        }
        return offers;
    }
}
