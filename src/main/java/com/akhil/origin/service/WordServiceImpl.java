package com.akhil.origin.service;

import com.akhil.origin.dao.StatusRepository;
import com.akhil.origin.dao.UserRepository;
import com.akhil.origin.dao.WordDAO;
import com.akhil.origin.dao.WordRepository;
import com.akhil.origin.dto.Answer;
import com.akhil.origin.dto.UserInfo;
import com.akhil.origin.dto.LearntWords;
import com.akhil.origin.dto.Submission;
import com.akhil.origin.entity.Status;
import com.akhil.origin.entity.User;
import com.akhil.origin.entity.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WordServiceImpl implements WordService{

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Word> getLesson(UserInfo userInfo) {
        User user = userRepository.findByUserEmail(userInfo.getEmail());
        if(user == null){
            user = new User();
            user.setUserEmail(userInfo.getEmail());
            user.setUserName(userInfo.getName());
            userRepository.save(user);
        }
        return wordRepository.getLesson(user.getUserId());
    }

    @Override
    public List<String> getMeanings(int id) {
        return wordRepository.getMeanings(id);
    }

    @Override
    public void learntWords(LearntWords learntWords) {
        for(int id : learntWords.getIds()){
            Status status = new Status();
            status.setUser(userRepository.findByUserEmail(learntWords.getEmail()));
            status.setWord(wordRepository.findById(id));
            status.setSolved(false);
            statusRepository.save(status);
        }
    }

    @Override
    public List<Word> getQuiz(UserInfo userInfo) {
        User user = userRepository.findByUserEmail(userInfo.getEmail());
        if(user == null){
            user = new User();
            user.setUserEmail(userInfo.getEmail());
            user.setUserName(userInfo.getName());
            userRepository.save(user);
        }
        return wordRepository.getQuiz(user.getUserId());
    }

    @Override
    @Transactional
    public void solvedWords(Submission submission) {
        for(Answer answer: submission.getAnswers()){
            if(answer.isCorrect()) {
                User user = userRepository.findByUserEmail(submission.getEmail());
                statusRepository.setSolved(user.getUserId(), answer.getId());
            }
        }
    }
}
