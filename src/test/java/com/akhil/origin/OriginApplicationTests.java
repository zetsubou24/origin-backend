package com.akhil.origin;

import com.akhil.origin.controller.WordController;
import com.akhil.origin.dto.UserInfo;
import com.akhil.origin.entity.User;
import com.akhil.origin.entity.Word;
import com.akhil.origin.repository.UserRepository;
import com.akhil.origin.repository.WordRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class OriginApplicationTests {
	@Autowired
	WordController wordController;

	@MockBean
	WordRepository wordRepository;

	@MockBean
	UserRepository userRepository;

	Word word1;
	Word word2;
	Word word3;
	Word word4;
	List<Word> words;

	List<String> wordMeanings;

	@Before
	void init(){
		word1 = new Word(1, "one", "1", null);
		word2 = new Word(2, "two", "2", null);
		word3 = new Word(3, "three", "3", null);
		word4 = new Word(4, "four", "4", null);

		words = new ArrayList<>();

		words.add(word2);
		words.add(word3);
		words.add(word4);

		wordMeanings = new ArrayList<>();

		wordMeanings.add(word2.getWordMeaning());
		wordMeanings.add(word3.getWordMeaning());
		wordMeanings.add(word4.getWordMeaning());
	}
	@Test
	public void findById(){
		Word word = new Word();
		word.setWordId(1);
		word.setWordName("hello");
		word.setWordMeaning("world");
		word.setStatus(null);

		when(wordRepository.findById(1)).thenReturn(word);
		assertEquals(wordController.getWord(1).getWordId(),1);
	}

	@Test void getMeanings(){
		Word word1 = new Word(1, "one", "1", null);
		Word word2 = new Word(2, "two", "2", null);
		Word word3 = new Word(3, "three", "3", null);
		Word word4 = new Word(4, "four", "4", null);



		when(wordRepository.getMeanings(1)).thenReturn(wordMeanings);
		when(wordRepository.existsById(1)).thenReturn(true);

		assertEquals(wordController.getMeanings(1),wordMeanings);
	}

	@Test
	void getLesson(){
		UserInfo userInfo = new UserInfo();
		userInfo.setName("Anon");
		userInfo.setEmail("anon@gmail.com");

		User user = new User();
		user.setUserId(1);
		user.setUserName("Anon");
		user.setUserEmail("anon@gmail.com");

		when(wordRepository.getLesson(1)).thenReturn(words);
		when(userRepository.findByUserEmail(userInfo.getEmail())).thenReturn(user);
		assertEquals(wordController.getLesson(userInfo),words);
	}
	@Test
	void contextLoads() {
	}

}
