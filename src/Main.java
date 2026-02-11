import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        PlainPoker hand = new PlainPoker();
        hand.getHandType();
        hand.displayHand();
        hand.getRank();
        hand.getTotalBid();
        hand.displayBid();
    }
}