import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Part1 hand = new Part1();
        hand.getHandType();
        hand.determineHand();
    }
}