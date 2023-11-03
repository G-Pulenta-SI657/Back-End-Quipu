package gpulenta.quipu.offer.service.impl;

import gpulenta.quipu.offer.client.UserClient;
import gpulenta.quipu.offer.model.Message;
import gpulenta.quipu.offer.model.User;
import gpulenta.quipu.offer.repository.MessageRepository;
import gpulenta.quipu.offer.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final UserClient userClient;

    public MessageServiceImpl(MessageRepository messageRepository, UserClient userClient) {
        this.messageRepository = messageRepository;
        this.userClient = userClient;
    }
    @Override
    public Message findById(Long id) {
        Message message = messageRepository.findById(id).orElse(null);
        if (message != null) {
            ResponseEntity<User> userResponse = userClient.getUserById(message.getUserId());
            if (userResponse.getStatusCode() == HttpStatus.OK) {
                message.setUser(userResponse.getBody());
            }
        }
        return message;
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message update(Message message) {
        Message messageToUpdate = messageRepository.findById(message.getId()).orElse(null);
        if (messageToUpdate != null) {
            messageToUpdate.setUserId(message.getUserId());
            messageToUpdate.setMessage(message.getMessage());
            return messageRepository.save(messageToUpdate);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        messageRepository.deleteById(id);

    }

    @Override
    public List<Message> findByOffer_Id(Long id) {
        List<Message> messages = messageRepository.findByOffer_Id(id);
        for (Message message : messages) {
            ResponseEntity<User> userResponse = userClient.getUserById(message.getUserId());
            if (userResponse.getStatusCode() == HttpStatus.OK) {
                message.setUser(userResponse.getBody());
            }
        }
        return messages;
    }

    @Override
    public List<Message> findByUserId(Long userId) {
        List<Message> messages = messageRepository.findByUserId(userId);
        for (Message message : messages) {
            ResponseEntity<User> userResponse = userClient.getUserById(message.getUserId());
            if (userResponse.getStatusCode() == HttpStatus.OK) {
                message.setUser(userResponse.getBody());
            }
        }
        return messages;
    }
}
