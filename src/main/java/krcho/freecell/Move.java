package krcho.freecell;

/**
 * Remember old and new position of card move, for undo and wrong put place.
 *
 * @author Jozef Krcho
 */
public class Move implements java.io.Serializable{

    private int oldPosition;
    private int newPosition;

    public Move(int oldPosition, int newPosition) {
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
    }

    public int getOldPosition() {
        return oldPosition;
    }

    public int getNewPosition() {
        return newPosition;
    }

    public void setOldPosition(int odlPosition) {
        this.oldPosition = odlPosition;
    }

    public void setNewPosition(int newPosition) {
        this.newPosition = newPosition;
    }
}
