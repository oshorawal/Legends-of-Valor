/**
 * @ClassName Cell
 * @Description It is the superclass for all kinds of cells in this game.
 */
public class Cell {
    /**
     * the cell's type
     */
    protected String type;

    public Cell() {}

    public Cell(String type){
        this.type = type;
    }

    public String getType() {return type; }

    public void setType(String type) {
        this.type = type;
    }
}

