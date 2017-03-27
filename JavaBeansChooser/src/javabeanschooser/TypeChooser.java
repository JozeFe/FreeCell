package javabeanschooser;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Trieda TypeChooser je graficky panel, urceny na vyber z urcitych predefinovanych udajov predstavenych obrazkami, na krajsi efektnejsi vyber.
 *
 * @author Jozef Krcho
 */
public class TypeChooser extends JPanel {

    private int VERTICALGAP = 15;
    private int HORIZONTALGAP = 30;
    private int IMGXSIZE = 80;
    private int IMGYSIZE = 80;

    private final ArrayList<Unit> Types;
    private int choosed;

    public TypeChooser() {
        Types = new ArrayList<>();
        choosed = 0;
        this.setBackground(Color.white);
        this.addMouseListener(new ChooserMouseListener());

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int tmp = 0;
        for (Unit u : Types) {
            u.toDraw(g, HORIZONTALGAP + tmp * (HORIZONTALGAP + IMGXSIZE), VERTICALGAP, IMGXSIZE, IMGYSIZE, tmp == choosed);
            tmp++;
        }
    }

    /**
     * Nastav vybrane data.
     *
     * @param value cislo prvku
     */
    public void setChoosed(int value) {
        if (value >= 0 && value < Types.size()) {
            choosed = value;
        }
    }

    /**
     * Prida prvok do TypeChoosera. Musi byt predstavovany 2mi obrazkami, nazvom a hodnotou. Obrazok pre pripad ze je oznaceny a pre pripad ze nieje.
     *
     * @param image obrazok ked nieje vybrany
     * @param imageSelected obrazok ak je prvok vybrany
     * @param name nazov
     * @param data hodnota
     */
    public void addUnit(String image, String imageSelected, String name, Object data) {
        Types.add(new Unit(image, imageSelected, name, data));
    }

    /**
     * Vrati hodnotu aktualne vybraneho prvku. Ak je TypeChooser prazdny vrati null.
     *
     * @return vrat hodnotu
     */
    public Object getChoosedData() {
        if (Types.isEmpty()) {
            return null;
        }
        return Types.get(choosed).getData();
    }

    /**
     * Vyber prvku mysou ak je niektory vybraty oznaci ho.
     *
     * @param x x
     * @param y y
     */
    private void mouseSelect(int x, int y) {
        for (int i = 0; i < Types.size(); i++) {
            if (x > HORIZONTALGAP + i * (HORIZONTALGAP + IMGXSIZE) && x < HORIZONTALGAP + i * (HORIZONTALGAP + IMGXSIZE) + IMGXSIZE
                    && y > VERTICALGAP && y < VERTICALGAP + IMGYSIZE) {
                setChoosed(i);
                repaint();
            }
        }
    }

    /**
     * Nastav vertikalnu medzeru na hodnotu.
     *
     * @param size velkost
     */
    public void setVerticalGap(int size) {
        this.VERTICALGAP = size;
    }

    /**
     * Nastav horizontalnu medzeru na hodnotu
     *
     * @param size velkost
     */
    public void setHorizontalGap(int size) {
        this.HORIZONTALGAP = size;
    }

    /**
     * Nastavy X-ovu velkost obrazkov v Choosery.
     *
     * @param size velkost
     */
    public void setImgXSize(int size) {
        this.IMGXSIZE = size;
    }

    /**
     * Nastavy Y-ovu velkost obrazkov v Choosery.
     *
     * @param size velkost
     */
    public void setImgYSize(int size) {
        this.IMGYSIZE = size;
    }

    /**
     * Spravca ovladania mysou TypeChoosera
     */
    private class ChooserMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            mouseSelect(e.getX(), e.getY());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    /**
     * Jeden prvok v TypeChooserovi, predstavovany, nazvom, pametajucim udajom, a dvoma obrazkami, jedne pre pripad ze nieje oznaceny a druhy ak je oznaceny.
     *
     * @author Jozef Krcho
     */
    private class Unit {

        private final Image image;
        private final Image imageSelected;
        private final String name;
        private final Object data;

        public Unit(String image, String imageSelected, String name, Object data) {
            this.image = new ImageIcon(this.getClass().getResource(image)).getImage();
            this.imageSelected = new ImageIcon(this.getClass().getResource(imageSelected)).getImage();
            this.name = name;
            this.data = data;
        }

        /**
         * Vykresli obrazok predstavujuci hodnotu. Podla toho ci je vyrata.
         *
         * @param g graphics
         * @param x x 
         * @param y y
         * @param sizex velkost x
         * @param sizey velkost y
         * @param selected true, vykrasly obrazok pre oznaceny, false vykresly obrazok pre neoznaceny prvok
         */
        public void toDraw(Graphics g, int x, int y, int sizex, int sizey, boolean selected) {
            if (selected) {
                g.drawImage(imageSelected, x, y, x + sizex, y + sizey, 0, 0, sizex, sizey, null);
            } else {
                g.drawImage(image, x, y, x + sizex, y + sizey, 0, 0, sizex, sizey, null);
            }
            g.setColor(Color.BLACK);
            g.drawString(name, x + (sizex - g.getFontMetrics().stringWidth(name)) / 2, y + sizey + 20);
        }

        /**
         * Vrat ulozeny object.
         *
         * @return
         */
        public Object getData() {
            return data;
        }
    }
}
