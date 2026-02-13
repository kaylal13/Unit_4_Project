import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        PlainPoker hands = new PlainPoker();
        hands.readFile();
        hands.displayHand();
        hands.getRanks();
        hands.getTotalBid();
        hands.getRanksWild();
    }
}
