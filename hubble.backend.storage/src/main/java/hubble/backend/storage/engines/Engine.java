package hubble.backend.storage.engines;

public interface Engine<T> {
    void save(T entity);
}
