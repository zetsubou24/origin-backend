package com.akhil.origin.controller;

import com.akhil.origin.dto.UserInfo;
import com.akhil.origin.dto.LearntWords;
import com.akhil.origin.dto.Submission;
import com.akhil.origin.entity.User;
import com.akhil.origin.entity.Word;
import com.akhil.origin.exception.UserNotFoundException;
import com.akhil.origin.exception.WordNotFoundException;
import com.akhil.origin.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WordController {

    @Autowired
    private WordService wordService;

    @GetMapping("/word")
    public Word getWord(@RequestParam int id){
        return wordService.getWord(id);
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
        try {
            return wordService.getMeanings(id);
        }
        catch(WordNotFoundException wordNotFoundException){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"Word Not Found", wordNotFoundException
            );
        }
    }

    @PostMapping("/lesson/submit")
    public ResponseEntity<LearntWords> learntWords(@RequestBody LearntWords learntWords){
        System.out.println(learntWords);
        System.out.println(learntWords.getEmail());
        try {
            wordService.learntWords(learntWords);
        }
        catch(UserNotFoundException exception){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"User Not Found", exception
            );
        }
        return new ResponseEntity<LearntWords>(learntWords,HttpStatus.ACCEPTED);
    }

    @PostMapping("/quiz/submit")
    public String submitQuiz(@RequestBody Submission submission){
        System.out.println(submission);
        System.out.println(submission.getEmail());
        try {
            wordService.solvedWords(submission);
            return submission.getAnswers().toString();
        }
        catch(UserNotFoundException exception){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"User Not Found", exception
            );
        }
    }
}
