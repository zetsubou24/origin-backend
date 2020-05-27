package com.akhil.origin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import com.akhil.origin.entity.Status;


@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    private int userId;

    private String userName;

    private String userEmail;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    Set<Status> status = new HashSet<>();
}
