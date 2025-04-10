/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

/**
 *
 * @author Kong Jia Le
 * @param <T>
 */
public interface QueueInterface<T> {

    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to be added
     * @return true if enqueue successful, false if enqueue rejected
     */
    boolean enqueue(T element);

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element removed from the front of the queue
     */
    T dequeue();

    /**
     * Retrieves, but does not remove, the element at the front of the queue.
     *
     * @return the element at the front of the queue
     */
    T peek();

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements in the queue
     */
    int size();

    /**
     * Removes all elements from the queue.
     */
    void clear();
}
