import java.util.ArrayList;
import java.util.Random;

/**
 * @ClassName Utils
 * @Description It is the class for some utility functions for the game.
 */
public class Utils {

    public static void printLine(){
        System.out.println(ColorUtils.PURPLE + "---------------------------------------------------------------------------------------------------------" + ColorUtils.RESET);
    }

    public static boolean judgeInputInteger(String s, int min, int max ){
        int input;
        try {
            input = Integer.valueOf(s);
        }catch (Exception e){
            System.out.println("What you entered is not a number! Please enter a number between " + min + " and " + max);
            return  false;
        }
        if(input > max || input < min){
            System.out.println("What you entered is out of bound! Please enter a number between " + min + " and " + max);
            return false;
        }
        return true;
    }

    static ArrayList<ArrayList<Integer>> getRandomNumber(int total, int num1, int num2,int num3){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> list3 = new ArrayList<>();

        int count = 0;
        while(count < num1){
            int random = new Random().nextInt(total);
            if(list1.contains(random)){
                continue;
            }
            list1.add(random);
            count++;
        }

        count = 0;
        while(count < num2){
            int random = new Random().nextInt(total);
            if(list1.contains(random) || list2.contains(random)) {
                continue;
            }
            list2.add(random);
            count++;

        }

        count = 0;
        while(count < num3){
            int random = new Random().nextInt(total);
            if(list1.contains(random) || list2.contains(random) || list3.contains(random)) {
                continue;
            }
            list3.add(random);
            count++;
        }

        result.add(list1);
        result.add(list2);
        result.add(list3);
        return result;
    }
}
