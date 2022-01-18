import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName FileUtils
 * @Description It is the class for utility functions on loading files.
 */
public class FileUtils {
    static ArrayList<Warrior> loadWarriors(String filePath){
        String file = System.getProperty("user.dir") + "/ConfigFiles/" +filePath;
                List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(ColorUtils.RED+ "Please enter the correct warrior filepath" + ColorUtils.RESET);
            e.printStackTrace();
        }
        ArrayList<Warrior> warList = new ArrayList<Warrior>();
        String line;
        String[] attributes;
        for (int i = 1; i < lines.size(); i++) {
            line = lines.get(i);
            attributes = line.split("\\s+");
            warList.add( new Warrior(attributes[0],Integer.parseInt(attributes[6]),Double.parseDouble(attributes[1]),Double.parseDouble(attributes[2]),Double.parseDouble(attributes[3]),Double.parseDouble(attributes[4]),Double.parseDouble(attributes[5])));
        }
        return warList;
    }

    static ArrayList<Sorcerer> loadSorcerers(String filePath){
        String file = System.getProperty("user.dir") + "/ConfigFiles/" +filePath;
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(ColorUtils.RED + "Please enter the correct sorcerer filepath" + ColorUtils.RESET);
            e.printStackTrace();
        }
        ArrayList<Sorcerer> sorcerers = new ArrayList<Sorcerer>();
        String line;
        String[] attributes;
        for (int i = 1; i < lines.size(); i++) {
            line = lines.get(i);
            attributes = line.split("\\s+");
            sorcerers.add( new Sorcerer(attributes[0],Integer.parseInt(attributes[6]),Double.parseDouble(attributes[1]),Double.parseDouble(attributes[2]),Double.parseDouble(attributes[3]),Double.parseDouble(attributes[4]),Double.parseDouble(attributes[5])));
        }
        return sorcerers;
    }

    static ArrayList<Paladin> loadPaladins(String filePath){
        String file = System.getProperty("user.dir") + "/ConfigFiles/" +filePath;
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(ColorUtils.RED + "Please enter the correct paladin filepath" + ColorUtils.RESET);
            e.printStackTrace();
        }

        ArrayList<Paladin> paladins = new ArrayList<Paladin>();
        String line;
        String[] attributes;
        for (int i = 1; i < lines.size(); i++) {
            line = lines.get(i);
            attributes = line.split("\\s+");
            paladins.add( new Paladin(attributes[0],Integer.parseInt(attributes[6]),Double.parseDouble(attributes[1]),Double.parseDouble(attributes[2]),Double.parseDouble(attributes[3]),Double.parseDouble(attributes[4]),Double.parseDouble(attributes[5])));
        }
        return paladins;
    }

    static ArrayList<Weapon> loadWeapons(String filePath){
        String file = System.getProperty("user.dir") + "/ConfigFiles/" +filePath;
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(ColorUtils.RED + "Please enter the correct weapon filepath" + ColorUtils.RESET);
            e.printStackTrace();
        }

        ArrayList<Weapon> weapons = new ArrayList<Weapon>();
        String line;
        String[] attributes;
        for (int i = 1; i < lines.size(); i++) {
            line = lines.get(i);
            attributes = line.split("\\s+");
            weapons.add(new Weapon(attributes[0],Double.parseDouble(attributes[1]),Integer.parseInt(attributes[2]),Double.parseDouble(attributes[3])));
        }
        return weapons;
    }

    static ArrayList<Armor> loadArmors(String filePath){
        String file = System.getProperty("user.dir") + "/ConfigFiles/" +filePath;
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(ColorUtils.RED + "Please enter the correct armor filepath" + ColorUtils.RESET);
            e.printStackTrace();
        }
        ArrayList<Armor> armors = new ArrayList<Armor>();
        String line;
        String[] attributes;
        for (int i = 1; i < lines.size(); i++) {
            line = lines.get(i);
            attributes = line.split("\\s+");
            armors.add(new Armor(attributes[0],Double.parseDouble(attributes[1]),Integer.parseInt(attributes[2]),Double.parseDouble(attributes[3])));
        }
        return armors;
    }

    static ArrayList<Potion> loadPotions(String filePath){
        String file = System.getProperty("user.dir") + "/ConfigFiles/" +filePath;
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(ColorUtils.RED + "Please enter the correct potion filepath" + ColorUtils.RESET);
            e.printStackTrace();
        }

        ArrayList<Potion> potions = new ArrayList<Potion>();
        String line;
        String[] attr;
        for (int i = 1; i < lines.size(); i++) {
            line = lines.get(i);
            attr = line.split("\\s+");
            String[] attribList = attr[4].split("/ ");
            potions.add(new Potion(attr[0], Double.parseDouble(attr[1]), Integer.parseInt(attr[2]), Double.parseDouble(attr[3]), attribList));
        }
        return potions;
    }

    static ArrayList<Spell> loadFireSpells(String filePath){
        String file = System.getProperty("user.dir") + "/ConfigFiles/" +filePath;
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(ColorUtils.RED + "Please enter the correct fireSpell filepath" + ColorUtils.RESET);
            e.printStackTrace();
        }

        ArrayList<Spell> spells = new ArrayList<Spell>();
        String line;
        String[] attributes;
        for (int i = 1; i < lines.size(); i++) {
            line = lines.get(i);
            attributes = line.split("\\s+");
            spells.add(new FireSpell(attributes[0], Double.parseDouble(attributes[1]),Integer.parseInt(attributes[2]),Double.parseDouble(attributes[3]),Double.parseDouble(attributes[4])));
        }
        return spells;
    }

    static ArrayList<Spell> loadIceSpells(String filePath){
        String file = System.getProperty("user.dir") + "/ConfigFiles/" +filePath;
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(ColorUtils.RED + "Please enter the correct iceSpell filepath" + ColorUtils.RESET);
            e.printStackTrace();
        }

        ArrayList<Spell> spells = new ArrayList<Spell>();
        String line;
        String[] attributes;
        for (int i = 1; i < lines.size(); i++) {
            line = lines.get(i);
            attributes = line.split("\\s+");
            spells.add(new IceSpell(attributes[0], Double.parseDouble(attributes[1]),Integer.parseInt(attributes[2]),Double.parseDouble(attributes[3]),Double.parseDouble(attributes[4])));
        }
        return spells;
    }

    static ArrayList<Spell> loadLightningSpells(String filePath){
        String file = System.getProperty("user.dir") + "/ConfigFiles/" +filePath;
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(ColorUtils.RED + "Please enter the correct lightningSpell filepath" + ColorUtils.RESET);
            e.printStackTrace();
        }

        ArrayList<Spell> spells = new ArrayList<Spell>();
        String line;
        String[] attributes;
        for (int i = 1; i < lines.size(); i++) {
            line = lines.get(i);
            attributes = line.split("\\s+");
            spells.add(new LightningSpell(attributes[0], Double.parseDouble(attributes[1]),Integer.parseInt(attributes[2]),Double.parseDouble(attributes[3]),Double.parseDouble(attributes[4])));
        }
        return spells;
    }

    static ArrayList<Dragon> loadDragons(String filePath){
        String file = System.getProperty("user.dir") + "/ConfigFiles/" +filePath;
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(ColorUtils.GREEN + "Please enter the correct dragon filepath" + ColorUtils.RESET);
            e.printStackTrace();
        }

        ArrayList<Dragon> dragons = new ArrayList<Dragon>();
        String line;
        String[] attributes;
        for (int i = 1; i < lines.size(); i++) {
            line = lines.get(i);
            attributes = line.split("\\s+");
            dragons.add(new Dragon(attributes[0], Integer.parseInt(attributes[1]),Double.parseDouble(attributes[2]),Double.parseDouble(attributes[3]),Double.parseDouble(attributes[4])));
        }
        return dragons;
    }

    static ArrayList<Exoskeleton> loadExoskeletons(String filePath){
        String file = System.getProperty("user.dir") + "/ConfigFiles/" +filePath;
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(ColorUtils.RED + "Please enter the correct exoskeletons filepath" + ColorUtils.RESET);
            e.printStackTrace();
        }

        ArrayList<Exoskeleton> exoskeletons = new ArrayList<Exoskeleton>();
        String line;
        String[] attributes;
        for (int i = 1; i < lines.size(); i++) {
            line = lines.get(i);
            attributes = line.split("\\s+");
            exoskeletons.add(new Exoskeleton(attributes[0], Integer.parseInt(attributes[1]),Double.parseDouble(attributes[2]),Double.parseDouble(attributes[3]),Double.parseDouble(attributes[4])));
        }
        return exoskeletons;
    }

    static ArrayList<Spirit> loadSpirits(String filePath){
        String file = System.getProperty("user.dir") + "/ConfigFiles/" +filePath;
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(ColorUtils.RED + "Please enter the correct spirits filepath" + ColorUtils.RESET);
            e.printStackTrace();
        }

        ArrayList<Spirit> spirits = new ArrayList<Spirit>();
        String line;
        String[] attributes;
        for (int i = 1; i < lines.size(); i++) {
            line = lines.get(i);
            attributes = line.split("\\s+");
            spirits.add(new Spirit(attributes[0], Integer.parseInt(attributes[1]),Double.parseDouble(attributes[2]),Double.parseDouble(attributes[3]),Double.parseDouble(attributes[4])));
        }
        return spirits;
    }
}
