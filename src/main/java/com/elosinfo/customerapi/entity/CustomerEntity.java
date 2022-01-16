package com.elosinfo.customerapi.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@Data //coloca todos os getters e setters
@NoArgsConstructor //criar construtor default
public class CustomerEntity implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(generator = "increment") //indica que será controlado pelo banco
    @GenericGenerator(name = "increment", strategy = "increment") //estratégia de geração de ID
    @Column(name = "id")
    private Long id;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "first_name")
    private String firstName;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "last_name")
    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "email")
    private String email;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Column(name = "document")
    private String document;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "mobile_phone_code")
    private Integer mobilePhoneCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "mobile_phone_number")
    private Integer mobilePhoneNumber;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "active")
    private Boolean active;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

}
