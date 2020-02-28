
import java.io.FileNotFoundException;

public class Main {
    static Company[] COMPANIES;
    static Department[] DEPARTMENTS;
    static Internship[] INTERNSHIPS;

    public static void main(String[] args) throws FileNotFoundException {
        Control ctl = new Control();
        ctl.openFile();
        COMPANIES = ctl.companies;
        DEPARTMENTS = ctl.departments;
        INTERNSHIPS = ctl.internships;

        System.out.println("1- DEPARTMENTS");
        for (int i = 0; i < DEPARTMENTS.length; i++) {
            DEPARTMENTS[i].displayDepartment();
        }
        System.out.println();

        System.out.println("2- INSTRUCTORS");
        for (int i = 0; i < DEPARTMENTS.length; i++) {
            for(int j = 0; j < DEPARTMENTS[i].getInstructors().length; j++){
                DEPARTMENTS[i].displayInstructor(j);
            }
        }
        System.out.println();

        System.out.println("3- STUDENTS");
        for (int i = 0; i < INTERNSHIPS.length; i++) {
            INTERNSHIPS[i].displayStudent();
        }
        System.out.println();

        System.out.println("4 - COMPANIES");
        for (int i = 0; i < COMPANIES.length; i++) {
            COMPANIES[i].displayCompany();
        }
        System.out.println();

        System.out.println("5- INTERNSHIPS");
        for (int i = 0; i < INTERNSHIPS.length; i++) {
            INTERNSHIPS[i].displayInternship();
        }
        System.out.println();

        System.out.println("6- TOP COMPANY");
        int i = COMPANIES.length-1;
        int key = COMPANIES[i].getInternCount();
        while(i>=0 && COMPANIES[i].getInternCount()>=key){
            System.out.println(COMPANIES[i].getName());
            i--;
        }
    }
}