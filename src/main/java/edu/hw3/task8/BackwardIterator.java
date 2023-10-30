package edu.hw3.task8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class BackwardIterator<E> implements Iterator<E> {
    private final ArrayList<E> itObject;
    private int currIndex;

    public BackwardIterator(Collection<E> itObject) {
        this.itObject = new ArrayList<>(itObject);
        this.currIndex = this.itObject.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return currIndex >= 0;
    }

    @Override
    public E next() {
        return itObject.get(currIndex--);
    }
}
