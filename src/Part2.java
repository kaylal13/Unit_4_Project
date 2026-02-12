import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {
    private int rank;
    private int bid;

    public void getRank () throws FileNotFoundException {
        File f = new File("src/data");
        Scanner input = new Scanner(f);

        while (input.hasNextLine()) {
            String line = input.nextLine();


            int bar = line.indexOf("|");
            String hand = line.substring(0, bar);
            String stringBid = line.substring(bar + 1);
            int bid = Integer.parseInt(stringBid);


            String[] cards = hand.split(",");

            for (int i=0;i<cards.length;i++){
                if (cards[i] )
            }
        }

    }

}
//how to add up all ranks/update it (needs a loop?)