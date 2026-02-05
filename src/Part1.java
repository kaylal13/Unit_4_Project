import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Part1 {
    private int fiveOfKind;
    private int fourOfKind;
    private int fullHouse;
    private int threeOfKind;
    private int onePair;
    private int twoPair;
    private int high;
    private String[] cards;

    public void getHandType () throws FileNotFoundException {
       //splits the bid and the card
        File f = new File("src/data");
        Scanner input = new Scanner(f);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            int bar = line.indexOf("|");
            String hand = line.substring(0, bar);
            String bid = line.substring(bar + 1);
            for (int i =0;i<cards.length;i++) {
                if (cards[i].equals("Ace")) {
                    cards[i] = "1";
                }
                if (cards[i].equals("Jack")) {
                    cards[i] = "11";
                }
                if (cards[i].equals("Queen")) {
                    cards[i] = "12";
                }
                if (cards[i].equals("King")) {
                    cards[i] = "13";
                }
            }
            String[] cards = hand.split("");
            System.out.println(Arrays.toString(cards));
        }

//counts the amount of each card in a new list
        int[] handCount = new int[13];
        for (int i = 0; i < cards.length; i++) {
            if (i!=0){
                handCount[i]++;
            }
        }

        for (int number:handCount){
            if (number ==5){
                fiveOfKind++;
            }
            if (number==4){
                fourOfKind++;
            }
            if (number==3 || number==2){
                fullHouse++;
            }
            if (number==3){
               threeOfKind++;
            }

            if (number==2){
                onePair++;
            }
            if (number==1){
                high++;
            }
        }
    }

    public void determineHand (){
        System.out.println("Number of five of a kind hands: "+fiveOfKind);
        System.out.println("Number of full house hands: "+fourOfKind);
        System.out.println("Number of four of a kind hands: "+fullHouse);
        System.out.println("Number of three of a kind hands: "+threeOfKind);
//        System.out.println("Number of two pair hands: "+twoPair);
        System.out.println("Number of one pair hands: "+onePair);
        System.out.println("Number of high card hands: "+high);
    }
}

