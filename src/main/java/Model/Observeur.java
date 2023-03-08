package Model;

public interface Observeur<T> {

    public void update(Observé<T> obs, T obj);
}
