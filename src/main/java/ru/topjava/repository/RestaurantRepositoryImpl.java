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
import ru.topjava.model.Restaurant;

import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private static final RowMapper<Restaurant> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Restaurant.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertDish;

    @Autowired
    public RestaurantRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        this.insertDish = new SimpleJdbcInsert(jdbcTemplate).withTableName("restaurants").usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", restaurant.getId())
                .addValue("name", restaurant.getName())
                .addValue("description", restaurant.getDescription());
        if (restaurant.isNew()) {
            Number newId = insertDish.executeAndReturnKey(map);
            restaurant.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate
                    .update("UPDATE restaurants SET name=:name, description=:description", map) == 0) {
                return null;
            }
        }
        return restaurant;
    }


    @Override
    public boolean delete(int id) {

        return jdbcTemplate.update("DELETE FROM restaurants WHERE id = ?", id) != 0;
    }

    @Override
    public Restaurant get(int id) {

        return DataAccessUtils.singleResult(jdbcTemplate.query("SELECT * FROM restaurants WHERE id =?", ROW_MAPPER, id));
    }

    @Override
    public Restaurant getByName(String name) {
      return   DataAccessUtils.singleResult(jdbcTemplate.query("SELECT * FROM restaurants WHERE name =?", ROW_MAPPER, name));
    }

    @Override
    public List<Restaurant> getAll() {
        return jdbcTemplate.query("SELECT * FROM  restaurants ORDER BY name", ROW_MAPPER);
    }
}
