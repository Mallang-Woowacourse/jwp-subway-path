package subway.domain;

import java.util.Objects;

public class Station {

    private final Long id;
    private final String name;

    public Station(final String name) {
        this(null, name);
    }

    public Station(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Station)) {
            return false;
        }
        final Station station = (Station) o;
        return Objects.equals(id, station.id) && Objects.equals(name(), station.name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name());
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }
}
