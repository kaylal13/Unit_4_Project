import java.util.Objects;
import java.io.File;
import java.util.Scanner;

public class Sorting {
    private String handType;
    private int count;
    private String input;

    public Sorting (){
        this.input = input;
    }


    //converts input into an array of integers
    public void toInt () {

        try {
            Scanner file = new Scanner(new File("src/data"));
            while (file.hasNextLine()) {
                String line = file.nextLine();
                String[] cards = line.split(" ");
                int[] values = new int[cards.length];

                for (int i = 0; i < cards.length; i++) {
                    if (cards[i].equals("Ace")) {
                        values[i] = 1;
                    }
                    if (cards[i].equals("Jack")) {
                        values[i] = 11;
                    }
                    if (cards[i].equals("Queen")) {
                        values[i] = 12;
                    }
                    if (cards[i].equals("King")) {
                        values[i] = 13;
                    }
                    values[i] = Integer.parseInt(cards[i]);
                }
                System.out.println(values);
            }
        } catch (Exception e) {
            System.out.println("File not found");

        }

    }

//    public String getHandType (int count){
//        if (count ==3){
//            handType = "four of a kind";
//        } else if (count == 2 &&){
//            handType = "two pair";
//        } else if (count ==2 &&){
//            handType = "full house";
//        } else if (count==2){
//            handType = "three of a kind";
//        } else handType = "high card";
//    }
//
//    public void display () {
//        System.out.println("Number of five of a kind hands: ");
//        System.out.println("Number of full house hands: ");
//        System.out.println("Number of four of a kind hands: ");
//        System.out.println("Number of three of a kind hands: ");
//        System.out.println("Number of two pair hands: ");
//        System.out.println("Number of one pair hands: ");
//        System.out.println("Number of high card hands: ");
//    }


}
