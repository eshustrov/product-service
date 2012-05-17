package net.nowhere.service.product.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class ProductService {
    @GET
    public Product test() {
        return new Product("test");
    }
}
