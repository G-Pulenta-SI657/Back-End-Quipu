package gpulenta.quipu.payment.integration;

import gpulenta.quipu.payment.controller.PaymentController;
import gpulenta.quipu.payment.model.Payment;
import gpulenta.quipu.payment.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePayment() {
        Payment payment = new Payment();
        when(paymentService.save(payment)).thenReturn(payment);

        ResponseEntity<Payment> response = paymentController.createPayment(payment);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(payment, response.getBody());
    }

    @Test
    public void testUpdatePayment() {
        Long paymentId = 1L;
        Payment payment = new Payment();
        when(paymentService.update(payment)).thenReturn(payment);

        ResponseEntity<Payment> response = paymentController.updatePayment(paymentId, payment);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(payment, response.getBody());
    }

    @Test
    public void testGetPaymentByUserId() {
        Long userId = 1L;
        Payment payment = new Payment();
        when(paymentService.findByUserId(userId)).thenReturn(payment);

        ResponseEntity<Payment> response = paymentController.getPaymentByUserId(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(payment, response.getBody());
    }

    @Test
    public void testGetPaymentById() {
        Long paymentId = 1L;
        Payment payment = new Payment();
        when(paymentService.findById(paymentId)).thenReturn(payment);

        ResponseEntity<Payment> response = paymentController.getPaymentById(paymentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(payment, response.getBody());
    }
}
