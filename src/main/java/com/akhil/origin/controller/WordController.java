package com.akhil.origin.controller;

import com.akhil.origin.dto.Answer;
import com.akhil.origin.dto.Email;
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
    public List<Word> getLesson(@RequestBody Email email){
        System.out.println(email.getEmail());
        return wordService.getLesson(email);
    }

    @PostMapping("/quiz")
    public List<Word> getQuiz(@RequestBody Email email){
        System.out.println(email.getEmail());
        return wordService.getQuiz(email);
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
