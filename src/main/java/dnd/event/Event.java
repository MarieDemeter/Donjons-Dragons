package dnd.event;

public interface Event {
    public default String trigger() {
        return null;
    }
}
