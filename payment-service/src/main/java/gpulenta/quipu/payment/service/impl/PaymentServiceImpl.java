package gpulenta.quipu.payment.service.impl;

import gpulenta.quipu.payment.client.UserClient;
import gpulenta.quipu.payment.model.Payment;
import gpulenta.quipu.payment.model.User;
import gpulenta.quipu.payment.repository.PaymentRepository;
import gpulenta.quipu.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserClient userClient;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, UserClient userClient) {
        this.paymentRepository = paymentRepository;
        this.userClient = userClient;
    }

    @Override
    public Payment findByUserId(Long userId) {
        ResponseEntity<User> userResponse = userClient.getUserById(userId);
        if (userResponse.getStatusCode() != HttpStatus.OK || userResponse.getBody() == null) {
            return null;
        }
        return paymentRepository.findByUserId(userId);
    }

    @Override
    public Payment findById(Long id) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment != null) {
            ResponseEntity<User> userResponse = userClient.getUserById(payment.getUserId());
            if (userResponse.getStatusCode() == HttpStatus.OK) {
                payment.setUser(userResponse.getBody());
            }
        }
        return payment;
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment update(Payment payment) {
        Payment paymentToUpdate = paymentRepository.findById(payment.getId()).orElse(null);
        if (paymentToUpdate != null) {
            paymentToUpdate.setPaymentNumber(payment.getPaymentNumber());
            paymentToUpdate.setPaymentExpiration(payment.getPaymentExpiration());
            paymentToUpdate.setPaymentCvv(payment.getPaymentCvv());
            return paymentRepository.save(paymentToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
}
