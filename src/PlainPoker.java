import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class PlainPoker {
    private int fiveOfKind;
    private int fourOfKind;
    private int fullHouse;
    private int threeOfKind;
    private int twoPair;
    private int onePair;
    private int high;
    private int rank;
    private int bid;
    private int totalBid;


    public void getHandType() throws FileNotFoundException {
        File f = new File("src/data");
        Scanner input = new Scanner(f);


        while (input.hasNextLine()) {
            String line = input.nextLine();


            int bar = line.indexOf("|");
            String hand = line.substring(0, bar);
            String stringBid = line.substring(bar+1);
            int bid = Integer.parseInt(stringBid);



            String[] cards = hand.split(",");

            int[] counts = new int[cards.length];
            boolean[] counted = new boolean[cards.length];


            for (int i = 0; i < cards.length; i++) {
                if (!counted[i]) {
                    int count = 1;
                    for (int j = i + 1; j < cards.length; j++) {
                        if (cards[i].equals(cards[j])) {
                            count++;
                            counted[j] = true;
                        }
                    }
                    counts[i] = count;
                }
            }
            //convert ace, king, queen to numbers (?)


            int pairs = 0;
            boolean hasThree = false;
            boolean hasFour = false;
            boolean hasFive = false;

            for (int c : counts) {
                if (c == 5) {
                    hasFive = true;
                }
                if (c == 4) {
                    hasFour = true;
                }
                if (c == 3) {
                    hasThree = true;
                }
                if (c == 2) {
                    pairs++;
                }
            }

            if (hasFive) {
                fiveOfKind++;
            } else if (hasFour) {
                fourOfKind++;
            } else if (hasThree && pairs == 1) {
                fullHouse++;
            } else if (hasThree) {
                threeOfKind++;
            } else if (pairs == 2) {
                twoPair++;
            } else if (pairs == 1) {
                onePair++;
            } else {
                high++;
            }
        }
    }

    public void getRank() {
        for (int i =0; i<cards.length; i++){
            if (i)
        }
    }

    public void getTotalBid (){

        totalBid = rank * bid;
    }
    //need to make sure each rank and bid is multiplied for each hand

    public void displayHand() {
        System.out.println("Number of five of a kind hands: " + fiveOfKind);
        System.out.println("Number of full house hands: " + fullHouse);
        System.out.println("Number of four of a kind hands: " + fourOfKind);
        System.out.println("Number of three of a kind hands: " + threeOfKind);
        System.out.println("Number of two pair hands: " + twoPair);
        System.out.println("Number of one pair hands: " + onePair);
        System.out.println("Number of high card hands: " + high);
    }

    public void displayBid(){
        System.out.println("Total Bid Value: "+totalBid);
    }
}



