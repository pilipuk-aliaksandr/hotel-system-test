package by.pilipuk.environment.service;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import java.sql.PreparedStatement;
import java.util.List;

@Service
public class DBTruncateTestService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public DBTruncateTestService(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static final String TRUNCATE_TABLES = "TRUNCATE TABLE %s RESTART IDENTITY";

    public void truncateAllTables() {
        // 1. Отключаем проверку внешних ключей (аналог CASCADE в Postgres)
        jdbcTemplate.getJdbcOperations().execute("SET REFERENTIAL_INTEGRITY FALSE");

        try {
            TABLES_NAMES.forEach(table ->
                    jdbcTemplate.getJdbcOperations().execute(TRUNCATE_TABLES.formatted(table))
            );
        } finally {
            jdbcTemplate.getJdbcOperations().execute("SET REFERENTIAL_INTEGRITY TRUE");
        }
    }

    public static final List<String> TABLES_NAMES = List.of(
            "amenities",
            "hotels",
            "addresses",
            "arrival_times",
            "contacts"
    );
}