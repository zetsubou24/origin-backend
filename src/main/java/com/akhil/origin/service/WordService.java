package com.akhil.origin.service;

import com.akhil.origin.entity.Word;

import java.util.List;

public interface WordService {
    public List<Word> getWords();

    void learntWords();
}
