package org.howard.edu.lsp.assignment5;

import java.util.ArrayList;
import java.util.Collections;

/**
 * IntegerSet represents a mathematical set of integers.
 * Duplicate elements are not allowed.
 * Uses an ArrayList internally for storage.
 */
public class IntegerSet {

    private ArrayList<Integer> set;

    /**
     * Default constructor initializes an empty IntegerSet.
     */
    public IntegerSet() {
        set = new ArrayList<>();
    }

    /**
     * Removes all elements from the set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     *
     * @return size of the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Determines if two IntegerSets contain exactly the same elements.
     *
     * @param b the IntegerSet to compare with
     * @return true if both sets contain the same elements
     */
    public boolean equals(IntegerSet b) {
        if (b == null || this.length() != b.length()) {
            return false;
        }

        return this.set.containsAll(b.set) && b.set.containsAll(this.set);
    }

    /**
     * Checks whether the set contains a specific value.
     *
     * @param value the integer value to check
     * @return true if value exists in the set
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest value in the set.
     *
     * @return largest integer
     * @throws RuntimeException if the set is empty
     */
    public int largest() {
        if (set.isEmpty()) {
            throw new RuntimeException("Set is empty");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest value in the set.
     *
     * @return smallest integer
     * @throws RuntimeException if the set is empty
     */
    public int smallest() {
        if (set.isEmpty()) {
            throw new RuntimeException("Set is empty");
        }
        return Collections.min(set);
    }

    /**
     * Adds an item to the set if it is not already present.
     *
     * @param item the integer to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set if it exists.
     *
     * @param item the integer to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Returns a new set containing all elements in either set.
     *
     * @param intSetb the second IntegerSet
     * @return union of the two sets
     */
    public IntegerSet union(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(this.set);

        for (int item : intSetb.set) {
            if (!result.set.contains(item)) {
                result.set.add(item);
            }
        }

        return result;
    }

    /**
     * Returns a new set containing only elements common to both sets.
     *
     * @param intSetb the second IntegerSet
     * @return intersection of the two sets
     */
    public IntegerSet intersect(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int item : this.set) {
            if (intSetb.set.contains(item)) {
                result.set.add(item);
            }
        }
        return result;
    }

    /**
     * Returns a new set containing elements in this set but not in intSetb.
     *
     * @param intSetb the second IntegerSet
     * @return difference of the two sets
     */
    public IntegerSet diff(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int item : this.set) {
            if (!intSetb.set.contains(item)) {
                result.set.add(item);
            }
        }
        return result;
    }

    /**
     * Returns a new set containing elements in intSetb but not in this set.
     *
     * @param intSetb the second IntegerSet
     * @return complement of this set relative to intSetb
     */
    public IntegerSet complement(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int item : intSetb.set) {
            if (!this.set.contains(item)) {
                result.set.add(item);
            }
        }
        return result;
    }

    /**
     * Checks if the set is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of the set in ascending order.
     *
     * Format: [1, 2, 3]
     *
     * @return string representation of the set
     */
    @Override
    public String toString() {
        ArrayList<Integer> sorted = new ArrayList<>(set);
        Collections.sort(sorted);
        return sorted.toString();
    }
}