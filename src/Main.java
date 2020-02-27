import java.io.FileNotFoundException;

public class Main {
    static Company[] COMPANIES;
    static Department[] DEPARTMENTS;
    static Internship[] INTERNSHIPS;

    public static void main(String[] args) throws FileNotFoundException {
        Control ctl = new Control();
        ctl.openFile();
    }    
}