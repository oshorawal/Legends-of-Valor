import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * @ClassName GameLoader
 * @Description It is the class for loading the game.
 */
public class GameLoader {
    private Scanner sc;
    public GameLoader(){
        sc = new Scanner(System.in);
    }

    public void run(){
        // loading the config files of equipment
        ArrayList<Armor> armors = FileUtils.loadArmors("Armory.txt");
        ArrayList<Weapon> weapons = FileUtils.loadWeapons("Weaponry.txt");
        ArrayList<Potion> potions = FileUtils.loadPotions("Potions.txt");
        ArrayList<Spell> fireSpells = FileUtils.loadFireSpells("FireSpells.txt");
        ArrayList<Spell> iceSpells = FileUtils.loadIceSpells("IceSpells.txt");
        ArrayList<Spell> lightningSpells = FileUtils.loadLightningSpells("LightningSpells.txt");
        ArrayList<Spell> spells = new ArrayList<>();
        spells.addAll(fireSpells);
        spells.addAll(iceSpells);
        spells.addAll(lightningSpells);

        // loading the config files of monsters
        ArrayList<Dragon> dragons = FileUtils.loadDragons("Dragons.txt");
        ArrayList<Spirit> spirits = FileUtils.loadSpirits("Spirits.txt");
        ArrayList<Exoskeleton> exoskeletons = FileUtils.loadExoskeletons("Exoskeletons.txt");

        // loading the config files of heroes
        ArrayList<Warrior> warriors = FileUtils.loadWarriors("Warriors.txt");
        ArrayList<Paladin> paladins = FileUtils.loadPaladins("Paladins.txt");
        ArrayList<Sorcerer> sorcerers = FileUtils.loadSorcerers("Sorcerers.txt");

        Market market = new Market(armors,weapons,spells,potions);

        Game game = new LegendGame(warriors,sorcerers,paladins,dragons,exoskeletons,spirits,market);

        // loading the background music
        try {
            AudioInputStream audio_inputStream = AudioSystem.getAudioInputStream(new File("backGroundMusic.wav"));
            Clip audio_clip = AudioSystem.getClip();
            audio_clip.open(audio_inputStream);
            audio_clip.loop(Clip.LOOP_CONTINUOUSLY);

            boolean loop = true;
            do {
                loop = false;
                game.welcome();
                game.run();

                do {
                    System.out.println(ColorUtils.GREEN + "Do you want to play the game again?\n 1.> Press Y/y for yes. \n 2.> Press N/n for no" + ColorUtils.RESET);
                    String choice = sc.nextLine();
                    if (choice.equalsIgnoreCase("n")) {
                        break;
                    } else if (choice.equalsIgnoreCase("y")) {
                        Game game2 = new LegendGame(warriors,sorcerers,paladins,dragons,exoskeletons,spirits,market);
                        game2.welcome();
                        game2.run();

                    } else {
                        System.out.println(ColorUtils.RED + "Please enter a valid choice \n 1.> Press Y/y for yes. \n 2.> Press N/n for no" + ColorUtils.RESET);
                    }
                }while(true);
            }while(loop);

            System.exit(0);
            // looping as long as this thread is alive
            Thread.sleep(1000000000);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
