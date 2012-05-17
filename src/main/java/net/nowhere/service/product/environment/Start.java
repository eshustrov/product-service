package net.nowhere.service.product.environment;

import com.google.inject.servlet.GuiceFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Start {
    public static void main(final String... args) throws Exception {
        final Server server = new Server(8080);
        final ServletContextHandler context = new ServletContextHandler(server, "/");

        context.addEventListener(new ProductServiceConfig());
        context.addFilter(GuiceFilter.class, "/*", null);
        context.addServlet(DefaultServlet.class, "/");

        server.start();
    }
}
