package com.akhil.origin.repository;

import com.akhil.origin.entity.Word;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface WordRepository extends CrudRepository<Word, Integer> {
    @Query(nativeQuery=true, value="SELECT word.word_id, word.word_name, word.word_meaning FROM word where word.word_id NOT IN(SELECT status.word_id FROM status where status.user_id = ?1) ORDER BY RAND() LIMIT 3")
    public List<Word> getLesson(int id);

    @Query(nativeQuery=true, value="SELECT word.word_meaning FROM word WHERE word.word_id <> ?1 ORDER BY RAND() LIMIT 3")
    public List<String> getMeanings(int id);

    @Query(nativeQuery = true, value = "SELECT word.word_id, word.word_name, word.word_meaning FROM word INNER JOIN status ON word.word_id = status.word_id WHERE status.solved = 0 AND status.user_id = ?1 ORDER BY RAND() LIMIT 2")
    public List<Word> getQuiz(int id);

    public Word findById(int id);
}
