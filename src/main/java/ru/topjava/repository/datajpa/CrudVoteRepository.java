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
    int delete(@Param("id")int id,@Param("userId") int userId);


    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant WHERE v.user.id=?1 ORDER BY v.localDate DESC")
    List<Vote> getAll(int userId);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant WHERE v.user.id=?2 AND v.localDate = ?1")
    Vote getVoteByDate(LocalDate date, int id);


    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant WHERE v.user.id=?1 ORDER BY v.localDate DESC")
    List<Vote> getAllVoteByUser(int userId);

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant WHERE v.user.id = :userId AND" +
            " v.localDate >= :startDate AND v.localDate <= :endDate ORDER BY v.localDate DESC, v.restaurant.name ASC ")
    List<Vote> getVoteBetween(LocalDate startDate, LocalDate endDate, int userid);
}
