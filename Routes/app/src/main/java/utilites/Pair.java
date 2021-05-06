package utilites;
@SuppressWarnings("ALL")
public class Pair<K, V> {
    public K first;
    public V second;

    public Pair(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public Pair(){

    }
    public Pair(Pair<K, V> newPair) {
        this.first = newPair.first;
        this.second = newPair.second;
    }
    public K getFirst() {
        return first;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    @Override public int hashCode() { return first.hashCode() ^ second.hashCode(); }

    @Override public boolean equals(Object o) {
        Pair<K, V> p = (Pair<K, V>)o;
        return (first.equals(p.first) && second.equals(p.second));
    }
}
