package com.akhil.origin.service;

import com.akhil.origin.dto.Answer;
import com.akhil.origin.dto.Email;
import com.akhil.origin.dto.LearntWords;
import com.akhil.origin.dto.Submission;
import com.akhil.origin.entity.Word;

import java.util.List;

public interface WordService {
    List<Word> getLesson(Email email);

    List<String> getMeanings(int id);

    void learntWords(LearntWords learntWords);

    List<Word> getQuiz(Email email);

    void solvedWords(Submission submission);
}
