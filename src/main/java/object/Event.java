package object;

import java.util.Objects;

abstract class Event {
    protected String type;
    protected String link_to_data_file;
    protected double x,y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return Double.compare(event.x, x) == 0 &&
                Double.compare(event.y, y) == 0 &&
                type.equals(event.type) &&
                link_to_data_file.equals(event.link_to_data_file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, link_to_data_file, x, y);
    }
}
