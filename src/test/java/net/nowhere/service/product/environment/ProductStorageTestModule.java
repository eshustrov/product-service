package net.nowhere.service.product.environment;

import com.google.inject.AbstractModule;

public class ProductStorageTestModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new DatabaseModule());
        install(new DatabaseStructureModule());
    }
}
