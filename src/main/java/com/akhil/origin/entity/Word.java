package com.akhil.origin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "word")
@Data
public class Word {
    @Id
    private int wordId;

    private String wordName;

    private String wordMeaning;

    @OneToMany(mappedBy = "word")
    @JsonIgnore
    private Set<Status> status = new HashSet<>();
}
