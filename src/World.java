import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName World
 * @Description It is the class for the game world.
 */
public class World {
    /**
     * 2-dimensional cell array
     */
    protected Cell[][] map;

    /**
     * cell factory to generate new cells
     */
    private static Cell_factory cell_factory = new Cell_factory();

    /**
     * the number of rows of the map
     */
    private int row;

    /**
     * the number of columns of the map
     */
    private int column;

    /**
     * the hero's team
     */
    private HeroTeam heroes;

    /**
     * the monster's team
     */
    private MonsterTeam monsters;

    World(int width, int height){
        this.map = new Cell[width][height];
        this.row = width;
        this.column = height;
    }

    World(){
        this.row = 8;
        this.column = 8;
        this.map = new Cell[8][8];
    }

    private static World world = new World();

    public static World getInstance(){
        return world;
    }

    public void setHeroes(HeroTeam heroes){
        this.heroes = heroes;
    }

    public void setMonsters(MonsterTeam monsters){
        this.monsters = monsters;
    }

    public Cell[][] getMap() {
        return map;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    // get the type of the given cell
    public String getTile(int x, int y){
        return map[x][y].getType();
    }


    // set the type of the given cell
    public void setTile(int x, int y, Cell tile){
        this.map[x][y]= tile;
    }

    // initialize the map
    public void init(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                map[i][j] = cell_factory.getCell(" ");
            }
        }
        // total cells in one lane
        int totalTile = 12;
        // the number of cells with buff
        int numBCK = (int)Math.round(totalTile * 0.2);

        // initialize the first lane
        this.setLane(Utils.getRandomNumber(totalTile, numBCK,numBCK,numBCK),0);
        // initialize the second lane
        this.setLane(Utils.getRandomNumber(totalTile, numBCK,numBCK,numBCK),3);
        // initialize the third lane
        this.setLane(Utils.getRandomNumber(totalTile, numBCK,numBCK,numBCK),6);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // monster nexus cells at the top
                if(i == 0){
                    this.map[i][j] = cell_factory.getCell("NexusCell");
                }
                // hero nexus cells at the bottom
                if(i == 7){
                    this.map[i][j] = cell_factory.getCell("NexusCell");
                }
                // inaccessible cells between each lane
                if(j == 2 || j == 5){
                    this.map[i][j] = cell_factory.getCell("InaccessCell");
                }
                // plain cells for blank spots
                if(map[i][j].getType().equals(" ")){
                    this.map[i][j] = cell_factory.getCell("PlainCell");
                }
            }
        }
    }

    public void setLane(ArrayList<ArrayList<Integer>> randomNumber, int startColumn ){
        ArrayList<Integer> numBush = randomNumber.get(0);
        ArrayList<Integer> numCave = randomNumber.get(1);
        ArrayList<Integer> numKoulou = randomNumber.get(2);

        // randomly generate bush cells for one lane
        for (Integer i :
                numBush) {
            int x = i / 2;
            int y = i % 2;
            this.map[x + 1][y + startColumn] = cell_factory.getCell("BushCell");
        }

        // randomly generate cave cells for one lane
        for (Integer i :
                numCave) {
            int x = i / 2;
            int y = i % 2;
            this.map[x + 1][y + startColumn] = cell_factory.getCell("CaveCell");
        }

        // randomly generate koulou cells for one lane
        for (Integer i :
                numKoulou) {
            int x = i / 2;
            int y = i % 2;
            this.map[x + 1][y + startColumn] = cell_factory.getCell("KoulouCell");
        }

    }

    // print the map
    public void printMap(){
        int size = 8;
        List<StringBuilder> printableMap = new ArrayList<StringBuilder>();

        for (int row = 0; row < size * 3; row++) {
            printableMap.add(new StringBuilder());
            if ((row / 3) % 2 == 0){
                for (int col = 0; col < size; col++) {
                    if (row % 2 == 0){
                        createOutterCell(map, printableMap, row, col);
                    }else{
                        createInnerCell(map, printableMap, row, col);
                    }
                    if (col == size - 1)
                        printableMap.get(row).append("\n");
                }
            }else{
                for (int col = 0; col < size; col++) {
                    if (row % 2 == 1){
                        createOutterCell(map, printableMap, row, col);
                    }else{
                        createInnerCell(map, printableMap, row, col);
                    }
                    if (col == size - 1)
                        printableMap.get(row).append("\n");
                }
            }

            if (row % 3 == 2)
                printableMap.get(row).append("\n");
        }

        for (int i = 0; i < size * 3; i++) {
            System.out.print(printableMap.get(i));
        }
    }

    public String getOuterCellStr(String c){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            str.append(c).append(" - ");
        }
        str.append(c).append("   ");
        return str.toString();
    }

    public String getInnerCellStr(String component){
        return "| " + component + " |   ";
    }

    public String getCellComponent(int row, int col){
        for (int i = 0; i < 3; i++){
            if(row == heroes.getMember(i).getPositionX() && col == heroes.getMember(i).getPositionY()) {
                return ColorUtils.YELLOW_BOLD_BRIGHT +  "H" + (i + 1) + "   " + ColorUtils.RESET;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (row == monsters.getMember(i).getPositionX() && col == monsters.getMember(i).getPositionY()) {
                return ColorUtils.YELLOW_BOLD_BRIGHT + "   " + "M" + (i + 1) + ColorUtils.RESET;
            }
        }
        return "     ";
    }

    public void createInnerCell(Cell[][] map, List<StringBuilder> printableMap, int row, int col) {
        String component = getCellComponent(row/3, col);
        if (map[row/3][col].getType().equals("X"))
            component = "X X X";
        printableMap.get(row).append(getInnerCellStr(component));
    }

    public void createOutterCell(Cell[][] map, List<StringBuilder> printableMap, int row, int col) {
        switch (map[row/3][col].getType()){
            case "N":
                printableMap.get(row).append(getOuterCellStr(ColorUtils.GREEN_BOLD_BRIGHT+ "N" +ColorUtils.RESET));
                break;
            case "P":
                printableMap.get(row).append(getOuterCellStr(ColorUtils.CYAN+ "P" + ColorUtils.RESET));
                break;
            case "K":
                printableMap.get(row).append(getOuterCellStr(ColorUtils.RED_BOLD_BRIGHT+ "K" + ColorUtils.RESET));
                break;
            case "C":
                printableMap.get(row).append(getOuterCellStr(ColorUtils.PURPLE_BOLD_BRIGHT+ "C" + ColorUtils.RESET));
                break;
            case "B":
                printableMap.get(row).append(getOuterCellStr(ColorUtils.BLUE_BOLD_BRIGHT + "B" + ColorUtils.RESET));
                break;
            case "X":
                printableMap.get(row).append(getOuterCellStr("I"));
                break;
        }
    }
}
