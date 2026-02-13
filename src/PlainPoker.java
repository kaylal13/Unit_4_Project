import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlainPoker {
    private int fiveOfKind;
    private int fourOfKind;
    private int fullHouse;
    private int threeOfKind;
    private int twoPair;
    private int onePair;
    private int high;
    private String[] hands = new String[1000];
    private int[] bids = new int[1000];
    private int[] ranks = new int[1000];
    private int handCount = 0;

    public void readFile() throws FileNotFoundException {
        File f = new File("src/data");
        Scanner input = new Scanner(f);

        while (input.hasNextLine()) {
            String line = input.nextLine();

            int bar = line.indexOf("|");
            String hand = line.substring(0, bar);
            int bidValue = Integer.parseInt(line.substring(bar + 1));

            hands[handCount] = hand;
            bids[handCount] = bidValue;

            int type = getType(hand);
            if (type == 7) fiveOfKind++;
            else if (type == 6) fourOfKind++;
            else if (type == 5) fullHouse++;
            else if (type == 4) threeOfKind++;
            else if (type == 3) twoPair++;
            else if (type == 2) onePair++;
            else high++;
            handCount++;
        }
    }

    public int cardValue(String card) {
        if (card.equals("Ace")) return 14;
        if (card.equals("King")) return 13;
        if (card.equals("Queen")) return 12;
        if (card.equals("Jack")) return 11;
        return Integer.parseInt(card);
    }

    public int cardValueWild(String card) {
        if (card.equals("Ace")) return 14;
        if (card.equals("King")) return 13;
        if (card.equals("Queen")) return 12;
        if (card.equals("Jack")) return 1;
        return Integer.parseInt(card);
    }

    public int getType(String hand) {
        String[] cards = hand.split(",");
        int[] counts = new int[5];
        boolean[] counted = new boolean[5];

        for (int i = 0; i < 5; i++) {
            if (!counted[i]) {
                int count = 1;
                for (int j = i + 1; j < 5; j++) {
                    if (cards[i].equals(cards[j])) {
                        count++;
                        counted[j] = true;
                    }
                }
                counts[i] = count;
            }
        }

        int pairs = 0;
        boolean hasThree = false;
        boolean hasFour = false;
        boolean hasFive = false;

        for (int c : counts) {
            if (c == 5) hasFive = true;
            if (c == 4) hasFour = true;
            if (c == 3) hasThree = true;
            if (c == 2) pairs++;
        }

        if (hasFive) return 7;
        if (hasFour) return 6;
        if (hasThree && pairs == 1) return 5;
        if (hasThree) return 4;
        if (pairs == 2) return 3;
        if (pairs == 1) return 2;
        return 1;
    }

    private int cardIndex(String card) {
        if (card.equals("2")) return 0;
        if (card.equals("3")) return 1;
        if (card.equals("4")) return 2;
        if (card.equals("5")) return 3;
        if (card.equals("6")) return 4;
        if (card.equals("7")) return 5;
        if (card.equals("8")) return 6;
        if (card.equals("9")) return 7;
        if (card.equals("10")) return 8;
        if (card.equals("Queen")) return 9;
        if (card.equals("King")) return 10;
        if (card.equals("Ace")) return 11;
        return -1;
    }

    public int getTypeWild(String hand) {
        String[] cards = hand.split(",");
        int numJacks = 0;
        int[] count = new int[12];
        for (int i = 0; i < 5; i++) {
            if (cards[i].equals("Jack")) numJacks++;
            else count[cardIndex(cards[i])]++;
        }

        int maxCount = 0;
        int secondMax = 0;
        for (int i = 0; i < 12; i++) {
            if (count[i] > maxCount) {
                secondMax = maxCount;
                maxCount = count[i];
            } else if (count[i] > secondMax) {
                secondMax = count[i];
            }
        }

        if (maxCount + numJacks >= 5) return 7;
        if (maxCount + numJacks >= 4) return 6;
        if (maxCount + numJacks >= 3 && secondMax >= 2) return 5;
        if (maxCount + numJacks >= 3) return 4;
        if (maxCount + numJacks >= 2 && secondMax + Math.max(0, numJacks - (3 - maxCount)) >= 2) return 3;
        if (maxCount + numJacks >= 2) return 2;
        return 1;
    }

    public int compareHands(String hand1, String hand2) {
        int type1 = getType(hand1);
        int type2 = getType(hand2);
        if (type1 != type2) return type1 - type2;

        String[] c1 = hand1.split(",");
        String[] c2 = hand2.split(",");
        for (int i = 0; i < 5; i++) {
            int v1 = cardValue(c1[i]);
            int v2 = cardValue(c2[i]);
            if (v1 != v2) return v1 - v2;
        }
        return 0;
    }

    public int compareHandsWild(String hand1, String hand2) {
        int type1 = getTypeWild(hand1);
        int type2 = getTypeWild(hand2);
        if (type1 != type2) return type1 - type2;

        String[] c1 = hand1.split(",");
        String[] c2 = hand2.split(",");
        for (int i = 0; i < 5; i++) {
            int v1 = cardValueWild(c1[i]);
            int v2 = cardValueWild(c2[i]);
            if (v1 != v2) return v1 - v2;
        }
        return 0;
    }

    public void getRanks() {
        for (int i = 0; i < handCount; i++) {
            int strongerCount = 0;
            for (int j = 0; j < handCount; j++) {
                if (i != j && compareHands(hands[i], hands[j]) > 0) {
                    strongerCount++;
                }
            }
            ranks[i] = strongerCount + 1;
        }
    }




    public void getRanksWild() {
        int[] ranksWild = new int[handCount];
        for (int i = 0; i < handCount; i++) {
            int strongerCount = 0;
            for (int j = 0; j < handCount; j++) {
                if (i != j && compareHandsWild(hands[i], hands[j]) > 0) {
                    strongerCount++;
                }
            }
            ranksWild[i] = strongerCount + 1;
        }

        int totalBidWild = 0;
        for (int i = 0; i < handCount; i++) {
            totalBidWild += ranksWild[i] * bids[i];
        }
        System.out.println("Total bid value with jacks wild: " + totalBidWild);
    }


    public void displayHand() {
        System.out.println("Number of five of a kind hands: " + fiveOfKind);
        System.out.println("Number of full house hands: " + fullHouse);
        System.out.println("Number of four of a kind hands: " + fourOfKind);
        System.out.println("Number of three of a kind hands: " + threeOfKind);
        System.out.println("Number of two pair hands: " + twoPair);
        System.out.println("Number of one pair hands: " + onePair);
        System.out.println("Number of high card hands: " + high);
    }


    public void getTotalBid() {
        int totalBid = 0;
        for (int i = 0; i < handCount; i++) {
            totalBid += ranks[i] * bids[i];
        }
        System.out.println("Total bid value: " + totalBid);
    }
}
