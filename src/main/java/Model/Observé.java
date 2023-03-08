package Model;

public interface Observé<T> {
     public void addObserveur(Observeur<T> obs);

     public void noticeObserveurs(T obj);
}
