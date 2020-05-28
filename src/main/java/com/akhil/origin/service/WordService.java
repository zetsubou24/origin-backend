package com.akhil.origin.service;

import com.akhil.origin.dto.UserInfo;
import com.akhil.origin.dto.LearntWords;
import com.akhil.origin.dto.Submission;
import com.akhil.origin.entity.Word;
import com.akhil.origin.exception.UserNotFoundException;

import java.util.List;

public interface WordService {
    List<Word> getLesson(UserInfo userInfo);

    List<String> getMeanings(int id);

    void learntWords(LearntWords learntWords) throws UserNotFoundException;

    List<Word> getQuiz(UserInfo userInfo);

    void solvedWords(Submission submission) throws UserNotFoundException;
}
