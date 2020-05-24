package com.akhil.origin.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    private int userId;

    private String userName;

    private String userEmail;

    @OneToMany(cascade = { CascadeType.ALL },mappedBy = "user")
    Set<Status> status = new HashSet<>();
}
