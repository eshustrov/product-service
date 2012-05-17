package net.nowhere.service.product.environment;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import net.nowhere.service.product.service.ProductService;

import java.util.HashMap;
import java.util.Map;

public class ProductServiceConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new JerseyServletModule() {
            @Override
            protected void configureServlets() {
                bind(ProductService.class).asEagerSingleton();

                final Map<String, String> featureMap = new HashMap<String, String>();
                featureMap.put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE.toString());
                serve("/*").with(GuiceContainer.class, featureMap);
            }
        });
    }
}
