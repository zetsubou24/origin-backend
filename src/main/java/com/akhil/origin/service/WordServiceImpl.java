package com.akhil.origin.service;

import com.akhil.origin.dao.WordDAO;
import com.akhil.origin.entity.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordServiceImpl implements WordService{

    @Autowired
    private WordDAO wordDAO;

    @Override
    public List<Word> getWords() {
        return wordDAO.getWords();
    }

    @Override
    public void learntWords() {
        wordDAO.learntWords();
    }
}
