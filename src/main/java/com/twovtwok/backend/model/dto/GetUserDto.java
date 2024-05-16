package com.twovtwok.backend.model.dto;

import com.twovtwok.backend.dao.Photo;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetUserDto {
    private Long id;
    private String username;

    private String name;

    private String email;

    private String permissions;

    private String bio;

    private String phone;

    private String country;

    private String city;

    private int price;

    private String telegramLink;
    private String whatsappLink;

    private Boolean verified;

    private Photo profilePhoto;

}
