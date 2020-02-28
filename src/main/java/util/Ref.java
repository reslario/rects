package util;

import java.util.Objects;

public class Ref<T> {
    public T val;

    public Ref(T val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ref<?> ref = (Ref<?>) o;
        return Objects.equals(val, ref.val);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }
}
