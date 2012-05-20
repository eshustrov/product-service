package net.nowhere.service.product.environment;

import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import net.nowhere.service.product.service.ProductService;

import java.util.HashMap;
import java.util.Map;

public class ServiceModule extends ServletModule {
    @Override
    protected void configureServlets() {
        bind(ProductService.class).in(Scopes.SINGLETON);

        final Map<String, String> featureMap = new HashMap<>();
        featureMap.put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE.toString());
        serve("/*").with(GuiceContainer.class, featureMap);
    }
}
