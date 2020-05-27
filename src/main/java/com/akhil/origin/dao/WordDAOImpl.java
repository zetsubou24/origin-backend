package com.akhil.origin.dao;

import com.akhil.origin.entity.Status;
import com.akhil.origin.entity.User;
import com.akhil.origin.entity.Word;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class WordDAOImpl implements WordDAO{
    private EntityManager entityManager;

    public WordDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<Word> getWords() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Word> query = currentSession.createQuery("from Word order by rand()", Word.class).setMaxResults(3);

        List<Word> words = query.getResultList();

        return words;
    }

    @Override
    @Transactional
    public void learntWords() {
        Session currentSession = entityManager.unwrap(Session.class);
        Status status = new Status();
        status.setUser(currentSession.get(User.class, 1));
        status.setWord(currentSession.get(Word.class, 1));
        status.setSolved(false);
        currentSession.save(status);
    }

    @Override
    public List<String> getMeanings() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<Word> query = currentSession.createQuery("from Word ", Word.class).setMaxResults(3);

        List<Word> words = query.getResultList();

        return null;
    }
}
