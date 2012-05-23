package net.nowhere.service.product.service;

import com.google.inject.Inject;
import net.nowhere.service.product.storage.ProductItem;
import net.nowhere.service.product.storage.ProductStorage;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class ProductService {
    private final ProductStorage productStorage;

    @Inject
    public ProductService(final ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    @GET
    public Collection<Product> list() {
        Collection<ProductItem> productItems = productStorage.load();
        final Collection<Product> products = new ArrayList<>(productItems.size());
        for (final ProductItem productItem : productItems) {
            products.add(new Product(productItem.name));
        }
        return unmodifiableCollection(products);
    }
}
