package ru.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.id=:id AND v.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v  WHERE v.user.id=?1 ORDER BY v.local_date DESC")
    List<Vote> getAll(int userId);

    @Query("SELECT v FROM Vote v  WHERE v.user.id=?2 AND v.local_date = ?1")
    Vote getVoteByDate(LocalDate date, int id);

    @Query("SELECT v FROM Vote v  WHERE v.user.id=?1 ORDER BY v.local_date DESC")
    List<Vote> getAllVoteByUser(int userId);

    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId AND" +
            " v.local_date >= :startDate AND v.local_date <= :endDate ORDER BY v.local_date DESC, v.restaurant.name ASC ")
    List<Vote> getVoteBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.id=:id AND v.user.id=:userId")
    Vote get(@Param("id") int id, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v JOIN FETCH v.user WHERE v.id=:id AND v.user.id=:userId")
    Vote getWithUser(@Param("id") int id, @Param("userId") int userId);
}
