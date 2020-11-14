package staslibs;

public class QueueItem<E> {

    private E value;

    private QueueItem<E> parent;

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public QueueItem<E> getParent() {
        return parent;
    }

    public void setParent(QueueItem<E> parent) {
        this.parent = parent;
    }

    public QueueItem(E value) {
        this.value = value;
    }

    public QueueItem(E value, QueueItem parent) {
        this.value = value;
        this.parent = parent;
    }
}
