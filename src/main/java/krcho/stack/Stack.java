package krcho.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Own implementation of the Stack, represents a last-in-first-out (LIFO) stack
 * of objects. This class was created for purpose of subject "Údajové štruktúry
 * 1" in Faculty of management science and informatics, University of Žilina.
 *
 * @author Jozef Krcho
 * @param <E> element
 */
public class Stack<E> implements Iterator<E>, Iterable<E>, java.io.Serializable {

    private Element lastElement;
    private int size;
    private int selectedElement;

    public Stack() {
        lastElement = null;
        size = 0;
        selectedElement = 0;
    }

    /**
     * Push an item onto the top of this stack
     *
     * @param e element to be pushed
     */
    public void push(E e) {
        Element newElement = new Element<>(e);
        size++;
        if (lastElement == null) {
            lastElement = newElement;
        } else {
            newElement.setNextElement(lastElement);
            lastElement = newElement;
        }
    }

    /**
     * Removes the object at the top of this stack and returns
     *
     * @return object at the top of the stack, if empty then null
     */
    public E pull() {
        if (lastElement != null) {
            Element tmp = lastElement;
            lastElement = lastElement.getNextElement();
            size--;
            return (E) tmp.getData();
        }
        return null;
    }

    /**
     * Returns the object at the top of this stack.
     *
     * @return object at the top of the stack, if empty then null
     */
    public E getLast() {
        if (lastElement == null) {
            return null;
        }
        return (E) lastElement.getData();
    }

    /**
     * Test if stack is empty
     *
     * @return true if stack is empty, false if is not empty
     */
    public boolean isEmpty() {
        return lastElement == null;
    }

    /**
     * Return iterator for the stack
     *
     * @return Iterator
     */
    @Override
    public Iterator<E> iterator() {
        return this;
    }

    /**
     * Test if iteration has more elements
     *
     * @return true if iteration has more elements, otherwise false
     */
    @Override
    public boolean hasNext() {
        if (selectedElement < size) {
            return true;
        }
        selectedElement = 0;
        return false;
    }

    /**
     * Return next element in the iteration
     *
     * @return next iteration element
     */
    @Override
    public E next() {
        if (selectedElement == size) {
            throw new NoSuchElementException();
        }
        Element s = lastElement;
        for (int i = 0; i < size - selectedElement - 1; i++) {
            s = s.getNextElement();
        }
        selectedElement++;
        return (E) s.getData();
    }
}
