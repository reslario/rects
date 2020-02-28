package geometry;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Intersection {
    private Rectangle rect;
    private Set<Long> members;

    public Intersection(Rectangle rect, Set<Long> members) {
        this.rect = rect;
        this.members = members;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Set<Long> getMembers() {
        return members;
    }

    public Optional<Intersection> and(Intersection other) {
        if (equals(other))
            return Optional.empty();
        return rect
            .intersection(other.rect)
            .map(rect -> {
                Set<Long> participants = new HashSet<>(getMembers());
                participants.addAll(other.members);
                return new Intersection(rect, participants);
            });
    }

    @Override
    public String toString() {
        return "Between rectangles "
            + memberString()
            + ": "
            + rect.toString();
    }

    private String memberString() {
        return members
            .stream()
            .map(String::valueOf)
            .collect(Collectors.joining(", "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Intersection that = (Intersection) o;
        return Objects.equals(rect, that.rect) &&
            Objects.equals(members, that.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rect, members);
    }
}
