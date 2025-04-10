/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.util.Iterator;

/**
 *
 * @author Team
 * @param <T>
 */
public class DoublyLinkedList<T> implements ListInterface<T>, Iterable<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;
    private boolean modifiable;

    private static class Node<T> {

        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
        this.modifiable = true;
    }

    @Override
    public boolean add(T newEntry) {
        checkModifiable();
        return add(newEntry, size);
    }

    @Override
    public boolean add(T newEntry, int index) {
        checkModifiable();
        if (index < 0 || index > size) {
            return false;
        }
        Node<T> newNode = new Node<>(newEntry);
        if (index == 0) {
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            } else {
                tail = newNode;
            }
            head = newNode;
        } else if (index == size) {
            newNode.prev = tail;
            if (tail != null) {
                tail.next = newNode;
            } else {
                head = newNode;
            }
            tail = newNode;
        } else {
            Node<T> current = getNodeAtIndex(index);
            Node<T> prevNode = current.prev;
            newNode.prev = prevNode;
            newNode.next = current;
            prevNode.next = newNode;
            current.prev = newNode;
        }
        size++;
        return true;
    }

    @Override
    public T remove(int index) {
        checkModifiable();
        if (index < 0 || index >= size) {
            return null;
        }
        Node<T> removedNode;
        if (size == 1) {
            removedNode = head;
            head = null;
            tail = null;
        } else if (index == 0) {
            removedNode = head;
            head = head.next;
            head.prev = null;
        } else if (index == size - 1) {
            removedNode = tail;
            tail = tail.prev;
            tail.next = null;
        } else {
            Node<T> current = getNodeAtIndex(index);
            removedNode = current;
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        size--;
        return removedNode.data;
    }

    @Override
    public void clear() {
        checkModifiable();
        head = null;
        tail = null;
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
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int positionOf(T element) {
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(element)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getNodeAtIndex(index).data;
    }

    @Override
    public void set(int index, T element) {
        checkModifiable();
        if (index < 0 || index >= size) {
            return;
        }
        getNodeAtIndex(index).data = element;
    }

    private Node<T> getNodeAtIndex(int index) {
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public void sort() {
        checkModifiable();
        mergeSort(head, size);
    }

    private Node<T> mergeSort(Node<T> head, int size) {
        if (size <= 1) {
            return head;
        }

        Node<T> middle = getMiddle(head);
        Node<T> nextToMiddle = middle.next;
        middle.next = null;

        Node<T> left = mergeSort(head, size / 2);
        Node<T> right = mergeSort(nextToMiddle, size - size / 2);

        return merge(left, right);
    }

    private Node<T> merge(Node<T> left, Node<T> right) {
        Node<T> result = null;

        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        if (((Comparable<T>) left.data).compareTo(right.data) <= 0) {
            result = left;
            result.next = merge(left.next, right);
            result.next.prev = result;
        } else {
            result = right;
            result.next = merge(left, right.next);
            result.next.prev = result;
        }
        return result;
    }

    private Node<T> getMiddle(Node<T> head) {
        if (head == null) {
            return head;
        }
        Node<T> slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    @Override
    public T[] getArray() {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) new Object[size];
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

    @Override
    public void setModifiable(boolean modifiable) {
        this.modifiable = modifiable;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    private void checkModifiable() {
        if (!modifiable) {
            throw new UnsupportedOperationException("List unmodifiable.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data).append("\n");
            current = current.next;
        }
        return sb.toString();
    }

    public static <T> DoublyLinkedList<T> combine(DoublyLinkedList<T> list1, DoublyLinkedList<T> list2) {
        DoublyLinkedList<T> result = new DoublyLinkedList<>();
        Node<T> current = list1.head;
        while (current != null) {
            result.add(current.data);
            current = current.next;
        }

        current = list2.head;
        while (current != null) {
            if (!result.contains(current.data)) {
                result.add(current.data);
            }
            current = current.next;
        }
        return result;
    }
}
