package net.nowhere.service.product.service;

import com.google.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class ProductService {
    private final JdbcTemplate database;

    @Inject
    public ProductService(final JdbcTemplate database) {
        this.database = database;
    }

    @GET
    public Product test() {
        System.out.println("database: " + database);
        return new Product("test");
    }
}
