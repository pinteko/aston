package org.trainee;

import java.util.*;

/**
 * by Kiryl Korsuk at 28.07.2023
 */

public class CustomArrayListImpl<E> implements CustomArrayList<E>{
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_VALUES = {};

    Object[] elements;
    private int size;


    public CustomArrayListImpl() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    public CustomArrayListImpl(int capacity) {
        if (capacity > 0)
            this.elements = new Object[capacity];
        else if (capacity == 0)
            this.elements = EMPTY_VALUES;
        else throw new IllegalArgumentException("Illegal Capacity: "+ capacity);
    }

    public CustomArrayListImpl(Collection<? extends E> c) {
        Object[] a = c.toArray();
        if ((size = a.length) != 0)
            elements = Arrays.copyOf(a, size, Object[].class);
        else
            elements = EMPTY_VALUES;
    }

    public boolean add(E e) {
        ensureCapacity(size + 1);
        elements[size++] = e;
        return true;
    }

    public void add(int index, E element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;
        ensureCapacity(size + numNew);
        System.arraycopy(a, 0, elements, size, numNew);
        size += numNew;
        return true;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        Objects.checkIndex(index, size);
        return (E) elements[index];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E remove(int index) {
        Objects.checkIndex(index, size);
        E oldValue = get(index);
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        elements[--size] = null;
        return oldValue;
    }

    public boolean remove(Object o) {
        for (int index = 0; index < size; index++) {
            if (o.equals(elements[index])) {
                remove(index);
                return true;
            }
        }
        return false;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(Object o) {
        return indexOfRange(o, size);
    }

    int indexOfRange(Object o, int end) {
        if (o == null) {
            for (int i = 0; i < end; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < end; i++) {
                if (o.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomArrayListImpl<?> that = (CustomArrayListImpl<?>) o;
        if (this.size() != that.size()) return false;

        for (int i = 0; i < this.size(); i++) {
            E thisElement = this.get(i);
            Object thatElement = that.get(i);

            if (thisElement == null) {
                if (thatElement != null) return false;
            } else {
                if (!thisElement.equals(thatElement)) return false;
            }
        }
        return true;
    }

    public void sort(Comparator<? super E> c) {
        if (size > 1) {
            Object[] aux = elements.clone();
            mergeSort(aux, 0, size, elements, c);
        }
    }

    @SuppressWarnings("unchecked")
    private static <E> void mergeSort(Object[] src, int low, int high, Object[] dest, Comparator<? super E> c) {
        int length = high - low;
        if (length < 7) {
            for (int i = low; i < high; i++) {
                for (int j = i; j > low && c.compare((E) dest[j - 1], (E) dest[j]) > 0; j--) {
                    swap(dest, j, j - 1);
                }
            }
            return;
        }
        int mid = (low + high) >>> 1;
        mergeSort(dest, low, mid, src, c);
        mergeSort(dest, mid, high, src, c);
        if (c.compare((E) src[mid - 1], (E) src[mid]) <= 0) {
            System.arraycopy(src, low, dest, low, length);
            return;
        }
        for (int i = low, p = low, q = mid; i < high; i++) {
            if (q >= high || p < mid && c.compare((E) src[p], (E) src[q]) <= 0) {
                dest[i] = src[p++];
            } else {
                dest[i] = src[q++];
            }
        }
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = Math.max(elements.length * 2, minCapacity);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    private static void swap(Object[] x, int a, int b) {
        Object t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomArrayListIterator();
    }

    private class CustomArrayListIterator implements Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) elements[currentIndex++];
        }
    }
}
