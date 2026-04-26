package by.pilipuk.environment.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DBTruncateTestService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Value("${test.sql.disable-constraints}")
    private String disableConstraints;

    @Value("${test.sql.enable-constraints}")
    private String enableConstraints;

    @Value("${test.sql.truncate-table}")
    private String truncateTableTemplate;

    public DBTruncateTestService(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void truncateAllTables() {
        jdbcTemplate.getJdbcOperations().execute(disableConstraints);

        try {
            TABLES_NAMES.forEach(table ->
                    jdbcTemplate.getJdbcOperations().execute(
                            truncateTableTemplate.formatted(table)
                    )
            );
        } finally {
            jdbcTemplate.getJdbcOperations().execute(enableConstraints);
        }
    }

    private static final List<String> TABLES_NAMES = List.of(
            "amenities",
            "hotels",
            "addresses",
            "arrival_times",
            "contacts"
    );
}