package com.akhil.origin.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LearntWords {

    private List<Integer> ids;

    private String email;
}
