package com.example.demo.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.HashSet;
import java.util.Set;

@Data
public class PostDTO {

    private Long id;
    private String title;
    private String caption;
    private String location;
    private String username;
    private Integer likes;
    private Set<String> likedUsers;

}
