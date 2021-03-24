package ru.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.model.Dish;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restId")
    int delete(@Param("id") int id, @Param("restId") int restId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restId ORDER BY d.local_date DESC")
    List<Dish> getAllByRestaurant(@Param("restId") int restId);


    @Query("SELECT d FROM Dish d WHERE d.name LIKE %:name%")
    List<Dish> getByName(@Param("name") String name);

    @Query("SELECT d FROM Dish d WHERE d.id=:Id")
    Dish getById(@Param("Id") int Id);
}
