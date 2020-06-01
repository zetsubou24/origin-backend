package com.akhil.origin.service;

import com.akhil.origin.exception.UserNotFoundException;
import com.akhil.origin.exception.WordNotFoundException;
import com.akhil.origin.repository.StatusRepository;
import com.akhil.origin.repository.UserRepository;
import com.akhil.origin.repository.WordRepository;
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
    public List<String> getMeanings(int id) throws WordNotFoundException {
        if(!wordRepository.existsById(id)) throw new WordNotFoundException("Word Not Found");
        return wordRepository.getMeanings(id);
    }

    @Override
    public void learntWords(LearntWords learntWords) throws UserNotFoundException{
        User user = userRepository.findByUserEmail(learntWords.getEmail());
        if(user == null) throw new UserNotFoundException("User Not Found");
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
    public void solvedWords(Submission submission) throws UserNotFoundException {
            User user = userRepository.findByUserEmail(submission.getEmail());
            if(user == null) throw new UserNotFoundException("User Not Found");
            for (Answer answer : submission.getAnswers()) {
                if (answer.isCorrect()) {
                    statusRepository.setSolved(user.getUserId(), answer.getId());
                }
            }
    }

    @Override
    public Word getWord(int id) {
        return wordRepository.findById(id);
    }
}
