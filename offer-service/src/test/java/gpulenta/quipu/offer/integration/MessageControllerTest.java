package gpulenta.quipu.offer.integration;

import gpulenta.quipu.offer.controller.MessageController;
import gpulenta.quipu.offer.model.Message;
import gpulenta.quipu.offer.service.MessageService;
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
public class MessageControllerTest {

    @InjectMocks
    private MessageController messageController;

    @Mock
    private MessageService messageService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateMessage() {
        Message message = new Message();
        when(messageService.save(message)).thenReturn(message);

        ResponseEntity<Message> response = messageController.createMessage(message);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(message, response.getBody());
    }

    @Test
    public void testGetMessageByUserId() {
        Long userId = 1L;
        Iterable<Message> messages = Collections.emptyList();
        when(messageService.findByUserId(userId)).thenReturn((List<Message>) messages);

        ResponseEntity<Iterable<Message>> response = messageController.getMessageByUserId(userId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testGetMessageById() {
        Long messageId = 1L;
        Message message = new Message();
        when(messageService.findById(messageId)).thenReturn(message);

        ResponseEntity<Message> response = messageController.getMessageById(messageId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(message, response.getBody());
    }

    @Test
    public void testUpdateMessage() {
        Long messageId = 1L;
        Message message = new Message();
        when(messageService.update(message)).thenReturn(message);

        ResponseEntity<Message> response = messageController.updateMessage(messageId, message);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(message, response.getBody());
    }

    @Test
    public void testDeleteMessage() {
        Long messageId = 1L;
        Message message = new Message();
        when(messageService.findById(messageId)).thenReturn(message);

        ResponseEntity<String> response = messageController.deleteMessage(messageId);

        verify(messageService, times(1)).delete(messageId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Message deleted successfully", response.getBody());
    }

    @Test
    public void testGetMessageByOfferId() {
        Long offerId = 1L;
        Iterable<Message> messages = Collections.emptyList();
        when(messageService.findByOffer_Id(offerId)).thenReturn((List<Message>) messages);

        ResponseEntity<Iterable<Message>> response = messageController.getMessageByOfferId(offerId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
