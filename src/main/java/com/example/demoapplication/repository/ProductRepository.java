package com.example.demoapplication.repository;

import com.example.demoapplication.model.OrderInfo;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final String sql;

    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.sql = read("fetch_product_by_name.sql");
    }

    public List<OrderInfo> getOrdersByName(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource("name", name);
        return jdbcTemplate.query(sql, params, (rs, rowNum) ->
            new OrderInfo(
                rs.getString("product_name"),
                rs.getBigDecimal("amount"),
                rs.getDate("date").toLocalDate()
            )
        );
    }

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read script file", e);
        }
    }
}