package com.techproeducation.backendproject.initialwork.repository;

import com.techproeducation.backendproject.initialwork.entity.ContactMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
public class ContactMessageRepositoryTest {

    @Autowired
    private ContactMessageRepository contactMessageRepository;

    @Test
    public void ContactMessageRepository_Save_ReturnsContactMessage() {
        ContactMessage contactMessage = ContactMessage.builder()
                .name("Turan")
                .email("test@" + System.currentTimeMillis() + ".com")
                .subject("Test")
                .message("test: " + System.currentTimeMillis() + " test message")
                .build();

        ContactMessage savedContactMessage = contactMessageRepository.save(contactMessage);
        Assertions.assertThat(savedContactMessage).isNotNull();
        Assertions.assertThat(savedContactMessage.getId()).isGreaterThan(0);
    }

    @Test
    public void ContactMessageRepository_GetAll_ReturnsMoreThanOneContactMessage() {
        ContactMessage contactMessage1 = ContactMessage.builder()
                .name("Turan")
                .email("test@" + System.currentTimeMillis() + ".com")
                .subject("Test")
                .message("test: " + System.currentTimeMillis() + " test message")
                .build();

        ContactMessage contactMessage2 = ContactMessage.builder()
                .name("Turan")
                .email("test@" + System.currentTimeMillis() + ".com")
                .subject("Test")
                .message("test: " + System.currentTimeMillis() + " test message")
                .build();

        contactMessageRepository.save(contactMessage1);
        contactMessageRepository.save(contactMessage2);
        List<ContactMessage> contacts = contactMessageRepository.findAll();
        Assertions.assertThat(contacts).isNotNull();
        Assertions.assertThat(contacts.size()).isEqualTo(2);
    }

    @Test
    public void ContactMessageRepository_FindById_ReturnsContactMessage() {

        ContactMessage contactMessage = ContactMessage.builder()
                .name("Turan")
                .email("test@" + System.currentTimeMillis() + ".com")
                .subject("Test")
                .message("test: " + System.currentTimeMillis() + " test message")
                .build();

        contactMessageRepository.save(contactMessage);
        ContactMessage foundContactMessage = contactMessageRepository.findById(contactMessage.getId()).get();
        Assertions.assertThat(foundContactMessage).isNotNull();
    }

    @Test
    public void ContactMessageRepository_FindByEmail_ReturnsContactMessage() {
        ContactMessage contactMessage = ContactMessage.builder()
                .name("Turan")
                .email("test@example.com")
                .subject("Test")
                .message("test: " + System.currentTimeMillis() + " test message")
                .build();

        ContactMessage contactMessage2 = ContactMessage.builder()
                .name("Turan")
                .email("test@example.com")
                .subject("Test")
                .message("test: " + System.currentTimeMillis() + " test message")
                .build();
        contactMessageRepository.save(contactMessage);
        contactMessageRepository.save(contactMessage2);
        List<ContactMessage> foundContactMessages = contactMessageRepository.findAllByEmail(contactMessage.getEmail());
        Assertions.assertThat(foundContactMessages).isNotNull();
        Assertions.assertThat(foundContactMessages.size()).isEqualTo(2);
    }

    @Test
    public void ContactMessageRepository_FindBySubject_ReturnsContactMessage() {

        ContactMessage contactMessage = ContactMessage.builder()
                .name("Turan")
                .email("test@example.com")
                .subject("Test")
                .message("test: " + System.currentTimeMillis() + " test message")
                .build();

        ContactMessage contactMessage2 = ContactMessage.builder()
                .name("Turan")
                .email("test@example.com")
                .subject("Test")
                .message("test: " + System.currentTimeMillis() + " test message")
                .build();

        contactMessageRepository.save(contactMessage);
        contactMessageRepository.save(contactMessage2);
        List<ContactMessage> allContactMessages = contactMessageRepository.findAll();
        List<ContactMessage> searchedMessages = new ArrayList<>();
        for (ContactMessage each : allContactMessages) {
            if (each.getMessage().contains("test")) {
                searchedMessages.add(each);
            }
        }
        Assertions.assertThat(searchedMessages).isNotNull();
        Assertions.assertThat(searchedMessages.size()).isEqualTo(2);
    }

    @Test
    public void ContactMessageRepository_DeleteById_ReturnsTrue() {
        ContactMessage contactMessage = ContactMessage.builder()
                .name("Turan")
                .email("test@" + System.currentTimeMillis() + ".com")
                .subject("Test")
                .message("test: " + System.currentTimeMillis() + " test message")
                .build();

        contactMessageRepository.save(contactMessage);
        contactMessageRepository.deleteById(contactMessage.getId());
        Optional<ContactMessage> messageReturn = contactMessageRepository.findById(contactMessage.getId());
        Assertions.assertThat(messageReturn).isEmpty();
    }
    @Test
    public void ContactMessageRepository_Delete_ReturnsTrue() {
        ContactMessage contactMessage = ContactMessage.builder()
                .name("Turan")
                .email("test@" + System.currentTimeMillis() + ".com")
                .subject("Test")
                .message("test: " + System.currentTimeMillis() + " test message")
                .build();

        contactMessageRepository.save(contactMessage);
        contactMessageRepository.delete(contactMessage);
        Optional<ContactMessage> messageReturn = contactMessageRepository.findById(contactMessage.getId());
        Assertions.assertThat(messageReturn).isEmpty();
    }

    @Test
    public void ContactMessageRepository_UpdateMessage_ReturnsMoreThanOneContactMessage() {
        ContactMessage contactMessage = ContactMessage.builder()
                .name("Turan")
                .email("test@" + System.currentTimeMillis() + ".com")
                .subject("Test")
                .message("test: " + System.currentTimeMillis() + " test message")
                .build();

        contactMessageRepository.save(contactMessage);

        String updateMessage = "test: 123!!test message";
        String updateName = "Burhan";

        contactMessage.setMessage(updateMessage);
        contactMessage.setName(updateName);

        contactMessageRepository.save(contactMessage);

        Assertions.assertThat(contactMessageRepository.findById(contactMessage.getId()).get().getMessage()).isEqualTo(updateMessage);
        Assertions.assertThat(contactMessageRepository.findById(contactMessage.getId()).get().getName()).isEqualTo(updateName);
    }

}
