package com.twovtwok.backend.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(unique = true)
    private String username;

    @NonNull
    private String name;

    @NonNull
    @Column(unique = true)
    private String email;
    private String password;
    @NonNull
    private String permissions;

    @NonNull
    private String phone;

    @NonNull
    private String country;

    @NonNull
    private String city;

    @NonNull
    private Boolean verified = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Photo> photos;
    @OneToOne
    @JoinColumn(name = "profile_photo_id")
    private Photo profilePhoto;

    @ManyToMany
    @JoinTable(
            name = "user_savedUsers",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "savedUser_id")
    )
    private List<User> savedUsers;

    public void eraseCredentials() {
        this.password = null;
    }
}
