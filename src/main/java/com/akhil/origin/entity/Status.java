package com.akhil.origin.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Status {
    @Id
    private int status_id;

    private boolean learnt;

    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user;


    @ManyToOne()
    @JoinColumn(name="word_id")
    private Word word;
}
