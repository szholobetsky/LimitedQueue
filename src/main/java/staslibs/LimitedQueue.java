package staslibs;

import org.jetbrains.annotations.NotNull;

public class LimitedQueue<E> {


    private QueueItem<E> currentQueueItem;
    private int limit;

    public LimitedQueue(int limit) {
        this.limit = limit;

    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (this.limit > limit &&  getSize() > limit ) {
            // if new limit less then old limit then we have to delete all items after new limit
            removeAllAfterN(currentQueueItem , limit);
            this.limit = limit;
        }
        else {
            this.limit = limit;
        }
    }

    public void add(E o) {
        if (currentQueueItem == null) {
            currentQueueItem = new QueueItem(o);
        }
        else {
            currentQueueItem = new QueueItem(o, currentQueueItem);
            // if we have add one extra item we have to delete the oldest one
            if (getSize() > limit) {
                removeLast(currentQueueItem);
            }
        }
    }

    public E get() {
        if (this.currentQueueItem == null) {
            return null;
        }
        else {
            if (currentQueueItem.getParent() == null) {
                E resValue = currentQueueItem.getValue();
                currentQueueItem = null;
                return resValue;
            }
            else {
                return rGet(currentQueueItem);
            }
        }
    }

    private E rGet(@NotNull QueueItem<E> item) {

        if ( item.getParent() != null && item.getParent().getParent() == null) {
            E resValue = item.getParent().getValue();
            item.setParent(null);
            return resValue;
        }
        else {
           return rGet(item.getParent());
        }
    }

    public int getSize() {
        if (currentQueueItem == null)
            return 0;
        return rSize(currentQueueItem);
    }

    private int rSize(@NotNull QueueItem<E> item) {
        if (item.getParent() == null) {
            return 1;
        }
        else {
           return rSize(item.getParent())+1;
        }
    }

    private void removeLast(QueueItem<E> item) {
        if (item.getParent() != null) {
            //recursive
            if (item.getParent().getParent() != null) {
                removeLast(item.getParent());
            }
            else {
                removeLast(item.getParent());
                item.setParent(null);
            }
        }
    }

    private void removeAllAfterN(QueueItem<E> item, int n) {
        if (n != 0 && item.getParent() != null) {
            //recursive
            if (n != 1 && item.getParent().getParent() != null) {
                removeAllAfterN(item.getParent(),n-1);
            }
            else {
                removeAllAfterN(item.getParent(),n-1);
                item.setParent(null);
            }
        }
    }


    public void clear() {
        currentQueueItem = null;
    }

}
