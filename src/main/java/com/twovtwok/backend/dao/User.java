package com.twovtwok.backend.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
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

    private String name;

    @NonNull
    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    @NonNull
    private String permissions;

    private String bio;

    private String phone;

    @NonNull
    private String country;

    @NonNull
    private String city;

    @NonNull
    private int price;

    private String telegramLink = "";
    private String whatsappLink = "";

    private Boolean verified = false;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Photo> photos;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Album> albums = new ArrayList<>();

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
