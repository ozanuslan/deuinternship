import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Control {
    String[][] VALID_DEPTS = { { "CME", "Computer Engineering" }, { "CEV", "Environmental Engineering" },
            { "EEE", "Electrical-Electronic Engineering" }, { "END", "Industrial Engineering" },
            { "INS", "Civil Engineering" }, { "JEF", "Geophysics Engineering" }, { "JEO", "Geology Engineering" },
            { "MDN", "Mining Engineering" }, { "MAK", "Mechanical Engineering" },
            { "MMM", "Metallurgy and Material Engineering" }, { "TKS", "Textile Engineering" } };

    int[] VALID_DATES = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    String[] METADATA;

    public void openFile() throws FileNotFoundException {
        File f = new File("input.txt");
        Scanner r = new Scanner(f);
        int lineCount = 0;
        while (r.hasNextLine()) {
            r.nextLine();
            lineCount++;
        }
        r.close();

        Scanner in = new Scanner(f);
        METADATA = new String[lineCount];
        int index = 0;
        while (in.hasNextLine()) {
            METADATA[index] = in.nextLine();
        }
        in.close();
    }

}