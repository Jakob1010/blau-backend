package com.blau.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
public class AppUser {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    public AppUser(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
