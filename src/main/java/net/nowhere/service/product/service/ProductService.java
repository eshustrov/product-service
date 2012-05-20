package net.nowhere.service.product.service;

import com.google.inject.Inject;

import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class ProductService {
    private final DataSource dataSource;

    @Inject
    public ProductService(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GET
    public Product test() {
        System.out.println("dataSource: " + dataSource);
        return new Product("test");
    }
}
