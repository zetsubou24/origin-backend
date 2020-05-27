package com.akhil.origin.dao;

import com.akhil.origin.entity.Word;

import java.util.List;

public interface WordDAO {
    public List<Word> getWords();

    void learntWords();

    List<String> getMeanings();

}
