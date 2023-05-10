package subway.infrastructure.persistence.entity;

import java.util.List;
import subway.domain.Line;
import subway.domain.Section;
import subway.domain.Sections;

public class LineEntity {

    private final Long id;
    private final String name;

    public LineEntity(final String name) {
        this(null, name);
    }

    public LineEntity(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static LineEntity from(final Line line) {
        return new LineEntity(line.id(), line.name());
    }

    public Line toDomain(final List<Section> sections) {
        return new Line(id, name, new Sections(sections));
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }
}
