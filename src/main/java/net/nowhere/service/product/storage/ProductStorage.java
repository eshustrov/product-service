package net.nowhere.service.product.storage;

import com.google.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class ProductStorage {
    private static final Mapper MAPPER = new Mapper();

    private final JdbcTemplate database;

    @Inject
    public ProductStorage(final JdbcTemplate database) {
        this.database = database;
    }

    public Collection<ProductItem> load() {
        return database.query(Mapper.SELECT_ALL, MAPPER);
    }

    private static class Mapper implements RowMapper<ProductItem> {
        public static final String SELECT_ALL = "SELECT * FROM products";

        private static final String ID = "id";
        private static final String NAME = "name";

        @Override
        public ProductItem mapRow(final ResultSet results, final int rowNumber) throws SQLException {
            return new ProductItem(results.getLong(ID), results.getString(NAME));
        }
    }
}
