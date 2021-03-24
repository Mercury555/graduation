package ru.topjava.repository;

import org.springframework.stereotype.Repository;
import ru.topjava.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VoteRepository {
    // null if not found, when updated
    Vote save(Vote vote);

    Vote findOne(Integer id);

    // null if not found
    Vote getVoteToday(LocalDate date, int id);

    // null if not found
    List<Vote> getVoteBetween(LocalDate startDate, LocalDate endDate, int userId);

    List<Vote> getAllVoteByUser(int userId);

    boolean delete(int id, int userId);


}
