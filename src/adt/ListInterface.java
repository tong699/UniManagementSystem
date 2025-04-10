/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package adt;

/**
 *
 * @author Team
 * @param <T> Generic Type
 */
public interface ListInterface<T> {
    /**
     * Adds a new entry to the end of the list.
     * @param newEntry The object to be added as a new entry.
     * @return True if the addition is successful, false otherwise.
     */
    boolean add(T newEntry);

    /**
     * Adds a new entry at a specified index in the list.
     * @param newEntry The object to be added as a new entry.
     * @param index The index at which the new entry is to be added.
     * @return True if the addition is successful, false otherwise.
     */
    boolean add(T newEntry, int index);

    /**
     * Removes the entry at the specified index from the list.
     * @param index The index of the entry to be removed.
     * @return The removed entry, null if removed unsuccessful.
     */
    T remove(int index);

    /**
     * Clears all entries from the list.
     */
    void clear();

    /**
     * Gets the current number of entries in the list.
     * @return The number of entries in the list.
     */
    int size();

    /**
     * Checks if the list is empty.
     * @return True if the list is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Checks if the list contains a specific entry.
     * @param element The object to be checked for presence in the list.
     * @return True if the list contains the specified element, false otherwise.
     */
    boolean contains(T element);

    /**
     * Retrieves the position of the first occurrence of a specified entry in the list.
     * @param element The object to find in the list.
     * @return The index of the first occurrence of the element, or -1 if not found.
     */
    int positionOf(T element);

    /**
     * Gets the entry at a specified index in the list.
     * @param index The index of the entry to retrieve.
     * @return The entry at the specified index.
     */
    T get(int index);

    /**
     * Replaces the entry at a specified index with a new object.
     * @param index The index of the entry to replace.
     * @param element The new object to be placed at the specified index.
     */
    void set(int index, T element);

    /**
     * Sorts the elements of the list in ascending order, if applicable.
     */
    void sort();
    
    /**
     * Retrieves an array representation of the list.
     * @return An array containing all elements in the list.
     */
    T[] getArray();

    /**
     * Sets whether the list is modifiable (can change its elements).
     * @param modifiable True if the list should be modifiable, false otherwise.
     */
    void setModifiable(boolean modifiable);
}
