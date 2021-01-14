package ru.topjava.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.topjava.model.Dish;

import java.util.List;

@Repository
public class DishRepositoryImpl implements DishRepository {

    private static final RowMapper<Dish> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Dish.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertDish;

    @Autowired
    public DishRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertDish = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("dishes")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Dish save(Dish dish, int rest_id) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", dish.getId())
                .addValue("name", dish.getName())
                .addValue("local_date", dish.getLocal_date())
                .addValue("price", dish.getPrice())
                .addValue("restaurant", rest_id);
        if (dish.isNew()) {
            Number newId = insertDish.executeAndReturnKey(map);
            dish.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate
                    .update("UPDATE dishes SET name=:name, local_date=:local_date, price=:price  WHERE id=:id AND restaurant_id=:restaurant_id", map) == 0) {
                return null;
            }
        }
        return dish;
    }

    @Override
    public boolean delete(int id, int restaurant_id) {
        return jdbcTemplate.update("DELETE FROM dishes WHERE id= ? AND restaurant_id=?", id, restaurant_id) != 0;
    }

    @Override
    public Dish get(int id, int restaurant_id) {
        List<Dish> list = jdbcTemplate.query("SELECT * FROM dishes WHERE id = ? AND  restaurant_id =?", ROW_MAPPER, id, restaurant_id);
        return DataAccessUtils.singleResult(list);
}

    @Override
    public List<Dish> getAllByRestaurant(int restaurant_id) {
        return jdbcTemplate.query("SELECT * FROM dishes WHERE restaurant_id = ? ORDER BY dishes.local_date DESC",ROW_MAPPER, restaurant_id);
    }

    @Override
    public List<Dish> getByName(String name) {
        return jdbcTemplate.query("SELECT * FROM dishes WHERE name = ?", ROW_MAPPER, name);
    }
}
