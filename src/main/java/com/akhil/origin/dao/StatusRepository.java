package com.akhil.origin.dao;

import com.akhil.origin.entity.Status;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StatusRepository extends CrudRepository<Status, Integer> {
    public Status save(Status status);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE status SET status.solved = 1 WHERE status.user_id = ?1 AND status.word_id = ?2")
    void setSolved(int user_id, int word_id);
}
