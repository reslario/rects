package util;

import java.util.stream.Stream;

public class Streams {
    public static <T> Stream<Indexed<T>> enumerate(Stream<T> stream) {
        Ref<Long> index = new Ref<>(0L);
        return stream.map(obj -> new Indexed<>(index.val++, obj));
    }

    public static class Indexed<T> {
        private long index;
        private T value;

        public Indexed(long index, T value) {
            this.index = index;
            this.value = value;
        }

        public long getIndex() {
            return index;
        }

        public T getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Indexed{" +
                "index=" + index +
                ", value=" + value +
                '}';
        }
    }
}
