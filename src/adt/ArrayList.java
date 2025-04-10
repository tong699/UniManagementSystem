/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author Kong Jia Le
 * @param <T> Generic Type
 */
public class ArrayList<T> implements ListInterface<T>, Iterable<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;
    private boolean modifiable;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        elements = (T[]) new Object[capacity];
        size = 0;
        this.modifiable = true;
    }

    public ArrayList(boolean adjustable, int capacity) {
        elements = (T[]) new Object[capacity];
        size = 0;
        this.modifiable = true;
    }

    @Override
    public boolean add(T newEntry) {
        checkModifiable();

        ensureCapacity();
        elements[size++] = newEntry;

        return true;
    }

    @Override
    public boolean add(T newEntry, int index) {
        checkModifiable();

        if (index < 0 || index > size) {
            return false;
        }
        ensureCapacity();
        // Shift elements to make room
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = newEntry;
        size++;

        return true;
    }

    @Override
    public T remove(int index) {
        checkModifiable();

        if (index < 0 || index >= size) {
            return null;
        }
        T removedElement = elements[index];
        // Shift elements to fill the gap
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null; // Clear the last element
        size--;
        return removedElement;
    }

    public boolean removeAll(ArrayList<T> elementsToRemove) {
        checkModifiable();

        boolean modified = false;
        for (T element : elementsToRemove) {
            for (int i = 0; i < size(); i++) {
                if (get(i) == element) {
                    remove(i);
                    modified = true;
                    i--;
                }
            }
        }

        return modified;
    }

    @Override
    public void clear() {
        checkModifiable();

        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int positionOf(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return elements[index];
    }

    @Override
    public void set(int index, T element) {
        checkModifiable();

        if (index < 0 || index >= size) {
            return;
        }
        elements[index] = element;
    }

    @Override
    public void sort() {
        checkModifiable();
        mergeSort(elements, 0, size - 1);
    }

    private void mergeSort(T[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;

            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    private void merge(T[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        T[] L = (T[]) new Object[n1];
        T[] R = (T[]) new Object[n2];

        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr, m + 1, R, 0, n2);

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (((Comparable<T>) L[i]).compareTo(R[j]) <= 0) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public void reverse() {
        checkModifiable();
        int left = 0;
        int right = size - 1;
        while (left < right) {
            T temp = elements[left];
            elements[left] = elements[right];
            elements[right] = temp;
            left++;
            right--;
        }
    }

    @Override
    public T[] getArray() {
        return elements;
    }

    @Override
    public void setModifiable(boolean modifiable) {
        this.modifiable = modifiable;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            T[] newArray = (T[]) new Object[elements.length * 2];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void checkModifiable() {
        if (!modifiable) {
            throw new UnsupportedOperationException("List unmodifiable.");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public T next() {
                return elements[currentIndex++];
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T element : elements) {
            if (element == null) {
                break;
            }
            sb.append(element);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static <T> ArrayList<T> combine(ArrayList<T> list1, ArrayList<T> list2) {
        ArrayList<T> result = new ArrayList<>();
        for (T e : list1) {
            result.add(e);
        }

        for (T e : list2) {
            if (!result.contains(e)) {
                result.add(e);
            }
        }
        return result;
    }
}
