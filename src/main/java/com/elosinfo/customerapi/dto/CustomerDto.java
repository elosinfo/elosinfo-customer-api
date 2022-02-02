package com.elosinfo.customerapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CustomerDto {

    private Long id;

    @NotBlank(message = "First name required")
    @Length(min = 3, max = 20, message = "First Name must be between 3 and 20 characters long")
    private String firstName;

    @NotBlank(message = "Last name required")
    @Length(min = 3, max = 20, message = "Last Name must be between 3 and 20 characters long")
    private String lastName;

    @NotBlank(message = "E-mail required")
    @Email(message = "Invalid email")
    @Length(max = 60, message = "E-mail can be a maximum of 60 characters")
    private String email;

    @NotBlank(message = "Document required")
    @Length(min = 11, max = 14, message = "Document must be between 11 and 14 characters long")
    private String document;

    @NotNull
    private Integer mobilePhoneCode;

    @NotNull
    private Integer mobilePhoneNumber;

    @NotNull
    private Boolean active;
}
