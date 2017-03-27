package krcho.stack;

/**
 * One element of the stack, with reference to the next element.
 * 
 * @author Jozef Krcho
 * @param <E> element
 */
public class Element<E> implements java.io.Serializable {

    private Element nextElement;
    private final E data;

    /**
     * 
     * @param element data to save
     */
    public Element(E element) {
        this.nextElement = null;
        this.data = element;
    }

    /**
     * Set up reference to next Element
     * @param element next element
     */
    public void setNextElement(Element element) {
        nextElement = element;
    }

    /**
     * Return saved data of the element
     * @return element
     */
    public E getData() {
        return data;
    }

    /**
     * @return next element in the stack
     */
    public Element getNextElement() {
        return nextElement;
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
