package com.datastore.datastore.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=5, message = "first_name must be at least 5 character long")
    private String first_name;

    @NotNull
    @Column(name = "last_name")
    @Size(min=5, message = "last_name must be at least 5 character long")
    private String last_name;

    @NotNull
    private String course;

    @NotBlank(message = "age must be in 1 to 100 years")
    @Min(1)
    @Max(100)
    private String age;

    @NotBlank(message = "username is not Empty")
    private String username;

    @NotBlank(message = "password is not Empty")
    private String password;

    @NotNull
    private Date created_at = new Date();

}
