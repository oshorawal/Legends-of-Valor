import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * @ClassName MonsterTeam
 * @Description It is the class for monsters' team in the game.
 */
public class MonsterTeam extends Team {

    @Override
    public void displayMembers() {
        System.out.println(ColorUtils.RED + "       MONSTERS" + ColorUtils.RESET);
        System.out.print(ColorUtils.GREEN);
        System.out.printf("%-10s","ID");
        System.out.printf("%-10s","Level");
        System.out.printf("%-20s","Name");
        System.out.printf("%-20s","HP");
        System.out.printf("%-20s","Defense");
        System.out.printf("%-20s","Damage");
        System.out.print("\n");
        Utils.printLine();
        for(int i = 0; i<this.characters.size(); i++){
            Monster m = (Monster)this.characters.get(i);
            System.out.print(ColorUtils.GREEN);
            System.out.printf("%-10s",i);
            m.showInfo();
        }
        System.out.print(ColorUtils.RESET);
    }

    @Override
    public boolean reachNexus() {
        for (int i = 0; i < 3; i++) {
            if(this.characters.get(i).positionX == 7){
                return true;
            }
        }
        return false;
    }

    // randomly generate new monsters with the same level of the corresponding heroes
    public void addMember(HeroTeam heroTeam, ArrayList<Dragon> dragons, ArrayList<Spirit> spirits, ArrayList<Exoskeleton> exoskeletons, ArrayList<Lane> lanes) {
        ArrayList<Monster> levelMonster1 = getLevelMonster(heroTeam.getMember(0).getLevel(), dragons, spirits, exoskeletons);
        Monster monster1 = levelMonster1.get(new Random().nextInt(levelMonster1.size()));
        Monster newMonster1 = monster1.newMonster(monster1);
        newMonster1.setPositionX(0);
        newMonster1.setPositionY(0);
        lanes.get(0).addMonster(newMonster1);
        super.addMember(newMonster1);

        ArrayList<Monster> levelMonster2 = getLevelMonster(heroTeam.getMember(0).getLevel(), dragons, spirits, exoskeletons);
        Monster monster2 = levelMonster2.get(new Random().nextInt(levelMonster2.size()));
        Monster newMonster2 = monster2.newMonster(monster2);
        newMonster2.setPositionX(0);
        newMonster2.setPositionY(3);
        lanes.get(1).addMonster(newMonster2);
        super.addMember(newMonster2);

        ArrayList<Monster> levelMonster3 = getLevelMonster(heroTeam.getMember(0).getLevel(), dragons, spirits, exoskeletons);
        Monster monster3 = levelMonster3.get(new Random().nextInt(levelMonster3.size()));
        Monster newMonster3 = monster3.newMonster(monster3);
        newMonster3.setPositionX(0);
        newMonster3.setPositionY(6);
        lanes.get(2).addMonster(newMonster3);
        super.addMember(newMonster3);

    }

    // generate the monster list
    private ArrayList<Monster> getLevelMonster(int level, ArrayList<Dragon> dragons, ArrayList<Spirit> spirits, ArrayList<Exoskeleton> exoskeletons){
        ArrayList<Monster> levelMonster = new ArrayList<>();
        for (int i = 0; i < dragons.size(); i++) {
            if(dragons.get(i).getLevel() == level){
                levelMonster.add(dragons.get(i));
            }
        }
        for (int i = 0; i < spirits.size(); i++) {
            if(spirits.get(i).getLevel() == level){
                levelMonster.add(spirits.get(i));
            }
        }
        for (int i = 0; i < exoskeletons.size(); i++) {
            if(exoskeletons.get(i).getLevel() == level){
                levelMonster.add(exoskeletons.get(i));
            }
        }
        return levelMonster;
    }
}
