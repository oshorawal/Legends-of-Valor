/**
 * @ClassName Cell_factory
 * @Description It is the factory pattern to generate new cells
 */
public class Cell_factory {
    public Cell getCell(String cellType){
        if(cellType == " "){
            return new Cell(" ");
        }
        else if(cellType.equalsIgnoreCase("PlainCell")){
            return new PlainCell();

        } else if(cellType.equalsIgnoreCase("NexusCell")){
            return new NexusCell();

        } else if(cellType.equalsIgnoreCase("KoulouCell")){
            return new KoulouCell();
        }
        else if(cellType.equalsIgnoreCase("InaccessCell")){
            return new InaccessCell();
        }
        else if(cellType.equalsIgnoreCase("CaveCell")){
            return new CaveCell();
        }else if(cellType.equalsIgnoreCase("BushCell")){
            return new BushCell();
        }

        return null;
    }
}
