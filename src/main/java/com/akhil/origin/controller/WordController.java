package com.akhil.origin.controller;

import com.akhil.origin.dto.UserInfo;
import com.akhil.origin.dto.LearntWords;
import com.akhil.origin.dto.Submission;
import com.akhil.origin.entity.User;
import com.akhil.origin.entity.Word;
import com.akhil.origin.service.UserService;
import com.akhil.origin.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WordController {

    @Autowired
    private UserService userService;

    @Autowired
    private WordService wordService;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/lesson")
    public List<Word> getLesson(@RequestBody UserInfo userInfo){
        System.out.println(userInfo.getEmail());
        return wordService.getLesson(userInfo);
    }

    @PostMapping("/quiz")
    public List<Word> getQuiz(@RequestBody UserInfo userInfo){
        System.out.println(userInfo.getEmail());
        return wordService.getQuiz(userInfo);
    }

    @GetMapping("/meanings")
    public List<String> getMeanings(@RequestParam int id){
        return wordService.getMeanings(id);
    }

    @PostMapping("/lesson/submit")
    public String learntWords(@RequestBody LearntWords learntWords){
        System.out.println(learntWords);
        System.out.println(learntWords.getEmail());
        wordService.learntWords(learntWords);
        return learntWords.toString();
    }

    @PostMapping("/quiz/submit")
    public String submitQuiz(@RequestBody Submission submission){
        System.out.println(submission);
        System.out.println(submission.getEmail());
        wordService.solvedWords(submission);
        return submission.getAnswers().toString();
    }
}
