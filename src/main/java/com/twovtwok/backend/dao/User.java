package com.twovtwok.backend.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.NonNull;

import java.util.List;

@Entity(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String permissions;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Photo> photos;
    @OneToOne
    @JoinColumn(name = "profile_photo_id")
    private Photo profilePhoto;
}
