package net.nowhere.service.product.environment;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import net.nowhere.service.product.service.ProductService;

public class ProductServiceConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                bind(ProductService.class).asEagerSingleton();

                serve("/*").with(GuiceContainer.class);
            }
        });
    }
}
