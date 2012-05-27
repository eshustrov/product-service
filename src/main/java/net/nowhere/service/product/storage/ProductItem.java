package net.nowhere.service.product.storage;

public class ProductItem {
    public final long id;
    public final String name;

    public ProductItem(final long id, final String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final ProductItem that = (ProductItem) object;
        return id == that.id && (name != null ? name.equals(that.name) : that.name == null);

    }

    @Override
    public int hashCode() {
        return 31 * ((int) (id ^ (id >>> 32))) + (name != null ? name.hashCode() : 0);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ProductItem(");
        builder.append("id=").append(id).append(",");
        builder.append("name=").append(name).append(")");
        return builder.toString();
    }
}
