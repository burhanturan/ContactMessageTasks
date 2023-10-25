package com.techproeducation.backendproject.initialwork.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString

public class ContactMessageDTO {

    private String name;

    @Email
    private String email;

    private String subject;

    private String message;

    @Setter(AccessLevel.NONE)
    private LocalDateTime updateDateTime;

    public ContactMessageDTO() {
        updateDateTime = LocalDateTime.now(ZoneId.of("US/Eastern"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = updateDateTime.format(formatter);
        updateDateTime = LocalDateTime.parse(formattedDate, formatter).withSecond(0).withNano(0);
    }

}
