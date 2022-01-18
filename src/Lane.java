import java.util.ArrayList;

/**
 * @ClassName Lane
 * @Description It is the class for a single lane.
 */
public class Lane {
    /**
     * heroes in the lane
     */
    private ArrayList<Hero> heroes;

    /**
     * monsters in the lane
     */
    private ArrayList<Monster> monsters;

    public Lane() {
        this.heroes = new ArrayList<>();
        this.monsters = new ArrayList<>();
    }

    public boolean hasHero(){
        return this.heroes.size() > 0;
    }

    public void addHero(Hero hero){
        this.heroes.add(hero);
    }

    public void removeHero(Hero hero){
        this.heroes.remove(hero);
    }


    public void addMonster(Monster monster) {
        this.monsters.add(monster);
    }

    // record the hero's position with the highest exploration level
    public int highestHero(){
        if(this.heroes.size() == 0){
            return 6;
        }
        int min = this.heroes.get(0).positionX;
        for (int i = 0; i < heroes.size(); i++) {
            if(min > heroes.get(i).getPositionX()){
                min = heroes.get(i).getPositionX();
            }
        }
        return min;
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }
}
