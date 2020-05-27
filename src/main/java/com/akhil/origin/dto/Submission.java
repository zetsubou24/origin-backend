package com.akhil.origin.dto;

import lombok.Data;

@Data
public class Submission {
    private Answer[] answers;

    private String email;
}
