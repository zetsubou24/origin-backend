package com.akhil.origin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "word")
@AllArgsConstructor
@NoArgsConstructor
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
