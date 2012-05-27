package net.nowhere.service.product.storage;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.nowhere.service.product.environment.ProductStorageTestModule;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class ProductStorageTest {
    private JdbcTemplate database;

    @Before
    public void setup() {
        final Injector injector = Guice.createInjector(new ProductStorageTestModule());
        database = injector.getInstance(JdbcTemplate.class);
    }

    @Test
    public void test() {
        final ProductStorage storage = new ProductStorage(database);
        assertThat(storage.load(), containsInAnyOrder(new ProductItem(1, "apple"), new ProductItem(2, "pear")));
    }
}
