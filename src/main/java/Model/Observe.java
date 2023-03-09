package Model;

public interface Observe<T> {
     public void addObserveur(Observeur<T> obs);

     public void noticeObserveurs(T obj);
}
