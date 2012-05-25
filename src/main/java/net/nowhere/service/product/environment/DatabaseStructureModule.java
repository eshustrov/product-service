package net.nowhere.service.product.environment;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Set;

public class DatabaseStructureModule extends AbstractModule {
    private static final String SCRIPT = "script";

    @Override
    protected void configure() {
        final Multibinder<String> scriptBinder = Multibinder.newSetBinder(binder(), String.class, Names.named(SCRIPT));
        scriptBinder.addBinding().toInstance("/structure.sql");
        scriptBinder.addBinding().toInstance("/data.sql");

        bind(DatabaseScriptRunner.class).asEagerSingleton();
    }

    private static class DatabaseScriptRunner {
        private static final Charset UTF8 = Charset.forName("UTF-8");

        @Inject
        public DatabaseScriptRunner(final JdbcTemplate database, @Named(SCRIPT) final Set<String> scripts) {
            for (final String script : scripts) {
                database.execute(readScript(script));
            }
        }

        private static String readScript(final String script) {
            final InputStream stream = DatabaseScriptRunner.class.getResourceAsStream(script);
            try (final BufferedReader reader = new BufferedReader(new InputStreamReader(stream, UTF8))) {
                final StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append('\n');
                }
                return builder.toString();
            } catch (IOException e) {
                throw new RuntimeException("Cannot read database script: " + script, e);
            }
        }
    }
}
