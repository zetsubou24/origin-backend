package com.akhil.origin.controller;

import com.akhil.origin.entity.LearntWords;
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

    @GetMapping("/words")
    public List<Word> getWords(){
        return wordService.getWords();
    }

    @PostMapping("/learnt")
    public void learntWords(@RequestBody LearntWords learntWords){
        System.out.println(learntWords);
        wordService.learntWords();
    }
}
