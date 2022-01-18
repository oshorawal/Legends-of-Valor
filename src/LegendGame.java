import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @ClassName LegendGame
 * @Description It is the class for the main process of this game.
 */
public class LegendGame extends RpgGame {

    /**
     * warrior hero list
     */
    private ArrayList<Warrior> warriors;

    /**
     * sorcerer hero list
     */
    private ArrayList<Sorcerer> sorcerers;

    /**
     * paladin hero list
     */
    private ArrayList<Paladin> paladins;

    /**
     * dragon monster list
     */
    private ArrayList<Dragon> dragons;

    /**
     * exoskeleton monster list
     */
    private ArrayList<Exoskeleton> exoskeletons;

    /**
     * spirit monster list
     */
    private ArrayList<Spirit> spirits;

    /**
     * the market
     */
    private Market market;

    /**
     * team of heroes
     */
    private HeroTeam heroTeam;

    /**
     * team of monsters
     */
    private MonsterTeam monsterTeam;

    /**
     * lanes in the map
     */
    private ArrayList<Lane> lanes;


    /**
     * text for ascii in terminal
     */
    private String text;

    /**
     * ascii in terminal
     */
    private ascii_art asciiArt;


    public LegendGame(ArrayList<Warrior> warriors, ArrayList<Sorcerer> sorcerers, ArrayList<Paladin> paladins, ArrayList<Dragon> dragons, ArrayList<Exoskeleton> exoskeletons, ArrayList<Spirit> spirits, Market market) {
        super();
        this.text = "WELCOME";
        this.asciiArt = new ascii_art(this.text.length() * 30,30);
        this.warriors = warriors;
        this.sorcerers = sorcerers;
        this.paladins = paladins;
        this.dragons = dragons;
        this.exoskeletons = exoskeletons;
        this.spirits = spirits;
        this.heroTeam = new HeroTeam();
        this.monsterTeam = new MonsterTeam();
        this.market = market;
        this.lanes = new ArrayList<>();
        lanes.add(new Lane());
        lanes.add(new Lane());
        lanes.add(new Lane());
    }


    @Override
    void welcome() {
        asciiArt.drawString(text, "$");
        System.out.println();
        System.out.println(ColorUtils.GREEN + "***        W/w - move up     " + ColorUtils.RESET);
        System.out.println(ColorUtils.GREEN + "***        A/a - move left     "+ ColorUtils.RESET);
        System.out.println(ColorUtils.GREEN + "***        S/s - move down     "+ ColorUtils.RESET);
        System.out.println(ColorUtils.GREEN + "***        D/d - move right     "+ ColorUtils.RESET);
        System.out.println(ColorUtils.GREEN + "***        Q/q - quit game     "+ ColorUtils.RESET);
        System.out.println(ColorUtils.GREEN + "***        I/i - show information     "+ ColorUtils.RESET);
        System.out.println(ColorUtils.GREEN + "***        N - Nexus     "+ ColorUtils.RESET);
        System.out.println(ColorUtils.GREEN + "***        P - Plain Cell     "+ ColorUtils.RESET);
        System.out.println(ColorUtils.GREEN + "***        C - Cave Cell     "+ ColorUtils.RESET);
        System.out.println(ColorUtils.GREEN + "***        K - Koulou Cell     "+ ColorUtils.RESET);
        System.out.println(ColorUtils.GREEN + "***        B - Bush Cell    "+ ColorUtils.RESET);
        this.init();
    }

    @Override
    void init() {
        System.out.println();
        System.out.println( ColorUtils.GREEN + "Please choose three heroes for your team" + ColorUtils.RESET);
        System.out.println();
        System.out.println( ColorUtils.GREEN + "There are three types of heroes: Warriors, Sorcerers and Paladins, each with different favored skills." + ColorUtils.RESET);
        System.out.println();

        //initialize hero team
        for (int i = 0; i < 3; i++) {
            this.heroTeam.addMember(warriors,sorcerers,paladins,lanes.get(i));
        }

        //initialize monster team
        this.monsterTeam.addMember(heroTeam,dragons,spirits,exoskeletons,lanes);

        //initialize the position for heroes and monsters
        this.initPosition(monsterTeam,heroTeam);
        this.world.setHeroes(heroTeam);
        this.world.setMonsters(monsterTeam);

        // display the information of heroes
        heroTeam.displayMembers();
    }

    // initialize the position of heroes and monsters
    private void initPosition(MonsterTeam monsterTeam, HeroTeam heroTeam) {
        for (int i = 0; i < 3; i++) {
            // heroes in the hero nexus
            monsterTeam.getMember(i).setPositionX(0);
            monsterTeam.getMember(i).setPositionY(3 * i);
            // monsters in the monster nexus
            heroTeam.getMember(i).setPositionX(7);
            heroTeam.getMember(i).setPositionY(3 * i);
        }
    }

    // the game start
    @Override
    void run() {
        int count = 0;
        // loop for each round
        do {
            count ++;
            System.out.println(count + " round start");
            this.world.printMap();
            // hero's turn
            for (int i = 0; i < 3; i++) {
                Hero hero = (Hero) heroTeam.getCharacters().get(i);
                System.out.println(hero.getName() + " turn");
                // hero can move when no monster in range
                if(!this.action(hero)){
                    this.move(hero);
                }
            }

            // monster's turn

            for (int i = 0; i < monsterTeam.getCharacters().size(); i++) {
                Monster monster = (Monster) monsterTeam.getCharacters().get(i);
                Hero hero = this.heroInrange(monster);
                // hero in range, starting fighting
                if(hero != null){
                    this.battle(monster,hero);
                }
                // no hero in range, moving forward
                else
                    monster.moverForward();
            }

            // every four rounds three new monsters spawn in the monsters’ Nexus
            if(count % 4 == 0){
                newMonsterSpawn();
            }

            // print the map before each round
            this.world.printMap();
            System.out.println();
            Utils.printLine();
            System.out.println();
        }while(!this.end());
    }

    // new monster generated in the monster nexus
    private void newMonsterSpawn() {
        this.monsterTeam.addMember(heroTeam,dragons,spirits,exoskeletons,lanes);
    }

    // return the hero which is in the range of attack
    private Hero heroInrange(Monster monster) {
        int laneID = monster.getPositionY() / 3;
        Lane lane = lanes.get(laneID);
        ArrayList<Hero> heroes = lane.getHeroes();
        for (Hero hero :
                heroes) {
            if (Math.abs(hero.getPositionX() - monster.getPositionX()) == 1) {
                return hero;
            }
        }
        return null;
    }

    // judge if the game is over
    public boolean end() {
        // all the heroes are faint
        if(heroTeam.allFaint()){
            System.out.println("hero loses");
            return true;
        }
        // one of hero/monster reaches the opposite nexus
        if(heroTeam.reachNexus() || monsterTeam.reachNexus()){
            return true;
        }

        // the faint hero will revive in the hero nexus if there is someone else alive
        for (int i = 0; i < heroTeam.getSize(); i++) {
            Hero hero = (Hero) heroTeam.getCharacters().get(i);
            if(!hero.isAlive()){
                hero.backToNexus();
            }
        }
        return false;
    }

    // the move function in each round
    public void move(Hero hero){
        System.out.println(ColorUtils.GREEN + "Your turn to move" + ColorUtils.RESET ) ;
        System.out.println(ColorUtils.GREEN + "W-up S-down A-left D-right T-teleport" + ColorUtils.RESET ) ;

        // Get current position of the hero
        int x = hero.getPositionX();
        int y = hero.getPositionY();

        //current lane
        Lane lane = this.lanes.get(y / 3);

        String input;
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        do{
            input = scanner.next();

            // choose to move up
            if(input.equals("W") || input.equals("w")){
                if(x <= 0){
                    System.out.println(ColorUtils.RED + "Reach top edge of the world, please try a different move:" + ColorUtils.RESET);
                }else if(this.world.getTile(x-1,y).equals("X")){
                    System.out.println(ColorUtils.RED + "Inaccessible tile, please try a different move:" + ColorUtils.RESET);
                }else{
                    removeAttr(hero);
                    hero.moveUp();
                    updateAttr(hero);
                    flag = false;
                }
            }
            // choose to move left
            else if(input.equals("A") || input.equals("a")){
                if(y <= 0){
                    System.out.println(ColorUtils.RED + "Reach left edge of the world, please try a different move:" + ColorUtils.RESET);
                }else if(this.world.getTile(x,y-1).equals("X")){
                    System.out.println(ColorUtils.RED + "Inaccessible tile, please try a different move:" + ColorUtils.RESET);
                }else{
                    removeAttr(hero);
                    hero.moveLeft();
                    updateAttr(hero);
                    flag = false;
                }
            }
            // choose to move down
            else if(input.equals("S") || input.equals("s")){
                if( x >= this.world.getRow() -1){
                    System.out.println(ColorUtils.RED + "Reach bottom edge of the world, please try a different move:" + ColorUtils.RESET);
                }else if(this.world.getTile(x + 1,y).equals("X")){
                    System.out.println(ColorUtils.RED + "Inaccessible tile, please try a different move:" + ColorUtils.RESET);
                }else{
                    removeAttr(hero);
                    hero.moveDown();
                    updateAttr(hero);
                    flag = false;
                }
            }
            // choose to move right
            else if(input.equals("D") || input.equals("d")){
                if(y >= this.world.getColumn() - 1){
                    System.out.println(ColorUtils.RED + "Reach right edge of the world, please try a different move:" + ColorUtils.RESET);
                }else if(this.world.getTile(x, y + 1).equals("X")){
                    System.out.println(ColorUtils.RED + "Inaccessible tile, please try a different move:" + ColorUtils.RESET);
                }else{
                    removeAttr(hero);
                    hero.moveRight();
                    updateAttr(hero);
                    flag = false;
                }
            }else if(input.equals("Q") || input.equals("q")){
                System.exit(0);
            }
            // choose to show information
            else if(input.equals("I")||input.equals("i")){
                this.heroTeam.displayMembers();
                System.out.println(ColorUtils.GREEN + "Take move" + ColorUtils.RESET);
            }
            // choose to teleport
            else if(input.equals("T") || input.equals("t")){
                if(this.telePort(hero)){
                    updateAttr(hero);
                    flag = false;
                }
                else{
                    System.out.println("teleport failed choose again");
                }

            }else{
                System.out.println(ColorUtils.RED + "Invalid input, please use W/A/S/D/T:" + ColorUtils.RESET);
            }
        }while(flag);
    }

    // the teleport function for heroes
    private boolean telePort(Hero hero) {
        int currentLane = hero.positionY / 3;
        Lane curLane = this.lanes.get(currentLane);
        Scanner scanner = new Scanner(System.in);
        String input;

        int positionX;
        int positionY;

        do{
            // get the row index of the target position
            System.out.println("Please enter the row you want to teleport or R/r to take another action");
            input = scanner.next();
            if(input.equals("R") || input.equals("r")){
                return false;
            }
            while (!Utils.judgeInputInteger(input,0,7)){
                input = scanner.next();
            }
            positionX = Integer.parseInt(input);

            // get the column index of the target position
            System.out.println("Please enter the column you want to teleport or R/r to take another action");
            input = scanner.next();
            if(input.equals("R") || input.equals("r")){
                return false;
            }
            while (!Utils.judgeInputInteger(input,0,7)){
                input = scanner.next();
            }
            positionY = Integer.parseInt(input);

            int goLane = positionY / 3;
            Lane gLane = this.lanes.get(goLane);

            // cannot teleport to the same lane
            if(positionY == 2 || positionY == 5){
                System.out.println("Can not go to NonAccessCell");
            }
            else if(currentLane == goLane){
                System.out.println(" Can not telepot in the same lane");
            }
            // cannot teleport to the inaccessible cell

            // cannot teleport to the unexplored area
            else if(positionX < gLane.highestHero()){
                System.out.println("Can not go to unexplored place");
            }
            // teleport success
            else {
                hero.setPositionX(positionX);
                hero.setPositionY(positionY);
                removeAttr(hero);
                curLane.removeHero(hero);
                gLane.addHero(hero);
                return true;
            }
        }while (true);
    }

    // hero choose which action to take
    public boolean action(Hero hero){
        int positionX = hero.getPositionX();
        int positionY = hero.getPositionY();
        String tile = this.world.getTile(positionX,positionY);

        // hero can have a trade if in market
        if(tile.equals("N")){
            System.out.println();
            System.out.println(ColorUtils.GREEN + "This is a market!" + ColorUtils.RESET);
            System.out.println();
            this.trade(hero);
            return false;
        }else if(this.inRange(hero)){
            System.out.println();
            System.out.println(ColorUtils.RED + "You meet monsters" + ColorUtils.RESET);
            System.out.println();
            this.battle(hero);
            return true;
        }
        else return false;
    }

    private boolean inRange(Hero hero) {
        int positionX = hero.getPositionX();
        int laneID = hero.getPositionY() / 3;
        Lane lane = this.lanes.get(laneID);
        Monster monster = lane.getMonsters().get(0);
        return Math.abs(positionX - monster.getPositionX()) <= 1;
    }

    // the battle function for monster
    private void battle(Monster monster, Hero hero) {
        System.out.println(ColorUtils.RED + "Fight Start!" + ColorUtils.RESET);
        hero.showInfo();
        monster.showInfo();

        // loop until one of them is defeated
        do{
            monster.attack(hero);
            hero.action(monster);
        }while (hero.isAlive() && monster.isAlive());

        // hero will have rewards winning the battle
        if(!monster.isAlive()){
            hero.winBattle(100 * monster.getLevel(), 2);
        }
    }

    // the battle function for heroß
    private void battle(Hero hero) {
        System.out.println(ColorUtils.RED + "Fight Start!" + ColorUtils.RESET);
        int laneID = hero.getPositionY() / 3;
        Lane lane = this.lanes.get(laneID);

        Monster monster = lane.getMonsters().get(0);

        System.out.println(ColorUtils.GREEN + "HERO" + ColorUtils.RESET);
        System.out.printf(ColorUtils.GREEN + "%-10s","Level");
        System.out.printf("%-20s","Name");
        System.out.printf("%-20s","HP");
        System.out.printf("%-20s","Mana");
        System.out.printf("%-20s","Strength");
        System.out.printf("%-20s","Agility");
        System.out.printf("%-20s","Dexterity");
        System.out.printf("%-20s","Money");
        System.out.printf("%-20s","Experience");
        System.out.println();
        Utils.printLine();
        System.out.printf("%-10s",hero.getLevel());
        hero.showInfo();

        System.out.println(ColorUtils.RED + "MONSTER" + ColorUtils.RESET);
        System.out.printf(ColorUtils.GREEN + "%-10s","Level");
        System.out.printf("%-20s","Name");
        System.out.printf("%-20s","HP");
        System.out.printf("%-20s","Defense");
        System.out.printf("%-20s","Damage");
        System.out.print("\n");
        Utils.printLine();
        monster.showInfo();

        do{
            hero.action(monster);
            monster.attack(hero);

        }while (hero.isAlive() && monster.isAlive());
        if(!monster.isAlive()){
            hero.winBattle(100 * monster.getLevel(), 2);
        }
    }

    // the trade for heroes in the market
    private void trade(Hero hero) {
        this.market.welcome(hero);
    }


    // the buff on attribute will disappear when hero leaves
    public void removeAttr(Hero hero){
        // hero is leaving the bush cell
        if(world.getTile(hero.getPositionX(), hero.getPositionY()).equals("B")){
            hero.setDexterity((hero.getDexterity() / 1.1));
        }
        // hero is leaving the cave cell
        if(world.getTile(hero.getPositionX(), hero.getPositionY()).equals("C")){
            hero.setAgility((hero.getAgility() / 1.1));
        }
        // hero is leaving the koulou cell
        if(world.getTile(hero.getPositionX(), hero.getPositionY()).equals("K")){
            hero.setStrength((hero.getStrength() / 1.1));
        }
    }

    // there will be a buff on attribute when hero enters a buff cell
    public void updateAttr(Hero hero){
        // hero is entering the bush cell
        if(world.getTile(hero.getPositionX(), hero.getPositionY()).equals("B")){
            hero.setDexterity((hero.getDexterity() * 1.1));
        }
        // hero is entering the cave cell
        if(world.getTile(hero.getPositionX(), hero.getPositionY()).equals("C")){
            hero.setAgility((hero.getAgility() * 1.1));
        }
        // hero is entering the koulou cell
        if(world.getTile(hero.getPositionX(), hero.getPositionY()).equals("K")){
            hero.setStrength((hero.getStrength() * 1.1));
        }
    }

    // the game is over
    public void over(){
        System.out.println(ColorUtils.RED + "***       Your team was beaten by the monsters     ***" + ColorUtils.RESET);
        System.out.println(ColorUtils.RED + " ***      GAME OVER     ***" + ColorUtils.RESET);
        System.exit(0);
    }
}
