package com.techproeducation.backendproject.initialwork.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
@Entity
public class ContactMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "Name Field can not be null!")
    @NotBlank(message = "Name Field can not be white space")
    @NotEmpty(message = "Name Field can not leave empty")
    private String name;

    @NotNull(message = "Email Field can not be null!")
    @NotBlank(message = "Email Field can not be white space")
    @NotEmpty(message = "Email Field can not leave empty")
    @Email
    private String email;

    @NotNull(message = "Subject Field can not be null!")
    @NotBlank(message = "Subject Field can not be white space")
    @NotEmpty(message = "Subject Field can not leave empty")
    private String subject;

    @NotNull(message = "Message Field can not be null!")
    @NotBlank(message = "Message Field can not be white space")
    @NotEmpty(message = "Message Field can not leave empty")
    private String message;


    @Setter(AccessLevel.NONE)
    private LocalDateTime creationDateTime;

    public ContactMessage() {
        creationDateTime = LocalDateTime.now(ZoneId.of("US/Eastern"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = creationDateTime.format(formatter);
        creationDateTime = LocalDateTime.parse(formattedDate, formatter).withSecond(0).withNano(0);
    }

//    //@PrePersist
//    public LocalDateTime prePersist() {
//        LocalDateTime now = LocalDateTime.now(ZoneId.of("US/Eastern"));
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        String formattedDate = now.format(formatter);
//        now = LocalDateTime.parse(formattedDate, formatter).withSecond(0).withNano(0);
//        return now;
//    }
}
