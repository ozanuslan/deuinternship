
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Control {
    String[][] VALID_DEPTS = { { "CME", "Computer Engineering" }, { "CEV", "Environmental Engineering" },
            { "EEE", "Electrical and Electronic Engineering" }, { "END", "Industrial Engineering" },
            { "INS", "Civil Engineering" }, { "JEF", "Geophysics Engineering" }, { "JEO", "Geology Engineering" },
            { "MDN", "Mining Engineering" }, { "MAK", "Mechanical Engineering" },
            { "MMM", "Metallurgy and Material Engineering" }, { "TKS", "Textile Engineering" } };

    int[] VALID_DATES = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    public Department[] departments;
    public Company[] companies;
    public Internship[] internships;

    public void openFile() throws FileNotFoundException {
        File f = new File("input.txt");
        Scanner r = new Scanner(f);
        int lineCount = 0;
        String[][] correctLengthData = new String[8000][24];
        for (int i = 0; i < correctLengthData.length; i++) {
            for (int j = 0; j < correctLengthData[i].length; j++) {
                correctLengthData[i][j] = "";
            }
        }

        //Only taking correct length data lines
        while (r.hasNextLine()) {
            String[] a = r.nextLine().split(",");
            if ((a.length == 24 && a[0].equalsIgnoreCase("Internship"))
                    || (a.length == 15 && a[0].equalsIgnoreCase("Department"))) {
                correctLengthData[lineCount] = a;
                lineCount++;
            }
        }
        r.close();

        int deletedLines = 0;
        int departmentCount = 0;
        boolean doesExist;

        //Checking if the department data is correct
        for (int i = 0; i < lineCount; i++) {
            doesExist = false;
            if (correctLengthData[i][0].equalsIgnoreCase("Department")) {
                try {
                    Integer.parseInt(correctLengthData[i][10].strip());
                    Integer.parseInt(correctLengthData[i][12].strip());
                    Integer.parseInt(correctLengthData[i][14].strip());
                } catch (NumberFormatException e) {
                    correctLengthData[i][0] = "";
                    deletedLines++;
                    continue;
                }
                for (int j = 0; j < VALID_DEPTS.length; j++) {
                    if (correctLengthData[i][1].strip().equalsIgnoreCase(VALID_DEPTS[j][0])
                            && correctLengthData[i][2].strip().equalsIgnoreCase(VALID_DEPTS[j][1])) {
                        doesExist = true;
                        departmentCount++;
                    }
                }
                if (!doesExist) {
                    correctLengthData[i][0] = "";
                    deletedLines++;
                    continue;
                }
            }
        }
        if (deletedLines > 0) {
            System.out.println(deletedLines + " incorrect deparment data found.");
            System.out.println(
                    "Departments with incorrect data will not be considered in the evaluation of the remainder of the data.");
        }

        departments = new Department[departmentCount];
        int departmentIndex = 0;

        //Filling department and instructor data
        for (int i = 0; i < correctLengthData.length; i++) {
            if (correctLengthData[i][0].equalsIgnoreCase("Department")) {
                departments[departmentIndex] = new Department(correctLengthData[i][1].strip(), correctLengthData[i][2].strip());
                for (int j = 0; j < departments[departmentIndex].getInstructors().length; j++) {
                    departments[departmentIndex].setInstructor(correctLengthData[i][3 + (2 * j)].strip(),
                            correctLengthData[i][4 + (2 * j)].strip(), j);
                }
                for (int j = 0; j < departments[departmentIndex].getTrainings().length; j++) {
                    departments[departmentIndex].setTraining(correctLengthData[i][9 + (2 * j)].strip(),
                            Integer.parseInt(correctLengthData[i][10 + (2 * j)].strip()), j);
                }
                departmentIndex++;
            }
        }

        int incorrectInternDataCount = 0;
        int internCount = 0;

        int companyCount = 0;
        String[] seenCountries = new String[8000];
        //Initializing the array to avoid nullpointer while making comparisons
        for (int i = 0; i < seenCountries.length; i++) {
            seenCountries[i] = "";
        }
        boolean isSeen;
        for (int i = 0; i < correctLengthData.length; i++) {
            doesExist = false;

            //Checking if student department really exists
            if (correctLengthData[i][0].equalsIgnoreCase("Internship")) {
                for (int j = 0; j < departments.length; j++) {
                    if (correctLengthData[i][1].equalsIgnoreCase(departments[j].getCode())) {
                        for (int k = 0; k < departments[j].getTrainings().length; k++) {
                            if (correctLengthData[i][10].equalsIgnoreCase(departments[j].getTrainings()[k].getType())) {
                                doesExist = true;
                                break;
                            }
                        }
                    }
                }
                if (!doesExist) {
                    correctLengthData[i][0] = "";
                    incorrectInternDataCount++;
                    continue;
                }

                //Checking date related data
                try {
                    Integer.parseInt(correctLengthData[i][2].strip());
                    String[] date = correctLengthData[i][4].strip().split("\\.");
                    if (Integer.parseInt(date[0]) < 0 || Integer.parseInt(date[2]) > 2002
                            || Integer.parseInt(date[2]) < 1920) {
                        correctLengthData[i][0] = "";
                        incorrectInternDataCount++;
                        continue;
                    }
                    if (Integer.parseInt(date[2]) % 4 == 0 && Integer.parseInt(date[0]) == 2) {
                        if (Integer.parseInt(date[0]) > 29) {
                            correctLengthData[i][0] = "";
                            incorrectInternDataCount++;
                            continue;
                        }
                    } else {
                        if (Integer.parseInt(date[0]) > VALID_DATES[Integer.parseInt(date[1]) - 1]) {
                            correctLengthData[i][0] = "";
                            incorrectInternDataCount++;
                            continue;
                        }
                    }
                    for (int j = 0; j < 4; j++) {
                        Integer.parseInt(correctLengthData[i][6 + j].strip());
                    }
                    date = correctLengthData[i][11].strip().split("\\.");
                    if (Integer.parseInt(date[0]) < 0 || Integer.parseInt(date[2]) > 2020
                            || Integer.parseInt(date[2]) < 1990) {
                        correctLengthData[i][0] = "";
                        incorrectInternDataCount++;
                        continue;
                    }
                    if (Integer.parseInt(date[2]) % 4 == 0 && Integer.parseInt(date[0]) == 2) {
                        if (Integer.parseInt(date[0]) > 29) {
                            correctLengthData[i][0] = "";
                            incorrectInternDataCount++;
                            continue;
                        }
                    } else {
                        if (Integer.parseInt(date[0]) > VALID_DATES[Integer.parseInt(date[1]) - 1]) {
                            correctLengthData[i][0] = "";
                            incorrectInternDataCount++;
                            continue;
                        }
                    }
                    date = correctLengthData[i][12].strip().split("\\.");
                    if (Integer.parseInt(date[0]) < 0 || Integer.parseInt(date[2]) > 2020
                            || Integer.parseInt(date[2]) < 1990) {
                        correctLengthData[i][0] = "";
                        incorrectInternDataCount++;
                        continue;
                    }
                    if (Integer.parseInt(date[2]) % 4 == 0 && Integer.parseInt(date[0]) == 2) {
                        if (Integer.parseInt(date[0]) > 29) {
                            correctLengthData[i][0] = "";
                            incorrectInternDataCount++;
                            continue;
                        }
                    } else {
                        if (Integer.parseInt(date[0]) > VALID_DATES[Integer.parseInt(date[1]) - 1]) {
                            correctLengthData[i][0] = "";
                            incorrectInternDataCount++;
                            continue;
                        }
                    }
                    for (int j = 0; j < 3; j++) {
                        Integer.parseInt(correctLengthData[i][19 + j]);
                    }
                } catch (NumberFormatException e) {
                    correctLengthData[i][0] = "";
                    incorrectInternDataCount++;
                    continue;
                }
            }

            //Recording all unique companies
            if (correctLengthData[i][0].equalsIgnoreCase("Internship")) {
                isSeen = false;
                for (int j = 0; j < seenCountries.length; j++) {
                    if (seenCountries[j].equalsIgnoreCase(correctLengthData[i][13].strip())) {
                        isSeen = true;
                        break;
                    }
                }
                if (!isSeen) {
                    seenCountries[companyCount] = correctLengthData[i][13];
                    companyCount++;
                }
                internCount++;
            }
        }
        if (incorrectInternDataCount > 0) {
            System.out.println(incorrectInternDataCount + " incorrect internship inputs found.");
            System.out.println("These internships will not be evaluated.");
        }

        //Initializing company names for comparison
        companies = new Company[companyCount];
        for (int i = 0; i < companies.length; i++) {
            companies[i] = new Company();
        }

        int companyIndex = 0;
        //Filling company data
        for (int i = 0; i < correctLengthData.length; i++) {
            isSeen = false;
            if (correctLengthData[i][0].equalsIgnoreCase("Internship")) {
                for (int j = 0; j < seenCountries.length; j++) {
                    for (int k = 0; k < companies.length; k++) {
                        if (companies[k].getName().equalsIgnoreCase(correctLengthData[i][13].strip())) {
                            isSeen = true;
                            break;
                        }
                    }
                }
                if (!isSeen) {
                    companies[companyIndex] = new Company(correctLengthData[i][13].strip(),
                            correctLengthData[i][14].strip(), correctLengthData[i][15].strip(),
                            correctLengthData[i][16].strip(), correctLengthData[i][17].strip(),
                            correctLengthData[i][18].strip(), correctLengthData[i][19].strip(),
                            correctLengthData[i][20].strip(), correctLengthData[i][21].strip(),
                            correctLengthData[i][22].strip(), correctLengthData[i][23].strip());
                    companyIndex++;
                }
            }
        }

        internships = new Internship[internCount];
        int internshipIndex = 0;

        //Filling internship and student data
        for (int i = 0; i < correctLengthData.length; i++) {
            if (correctLengthData[i][0].equalsIgnoreCase("Internship")) {
                Company temp = null;
                for (int j = 0; j < companies.length; j++) {
                    if (correctLengthData[i][13].strip().equalsIgnoreCase(companies[j].getName())) {
                        temp = companies[j];
                        companies[j].incrementInternCount();
                    }
                }
                int d1, d2, m1, m2, y1, y2;
                String[] date = correctLengthData[i][11].strip().split("\\.");
                d1 = Integer.parseInt(date[0]);
                m1 = Integer.parseInt(date[1]);
                y1 = Integer.parseInt(date[2]);
                date = correctLengthData[i][12].strip().split("\\.");
                d2 = Integer.parseInt(date[0]);
                m2 = Integer.parseInt(date[1]);
                y2 = Integer.parseInt(date[2]);

                int internshipDuration = (d2 - d1) + (m2 - m1) * 30 + (y2 - y1) * 365;
                internshipDuration /= 7;

                internships[internshipIndex] = new Internship(correctLengthData[i][10].strip(),
                        correctLengthData[i][11].strip(), correctLengthData[i][12].strip(), internshipDuration);
                internships[internshipIndex].setCompany(temp);

                date = correctLengthData[i][4].strip().split("\\.");

                internships[internshipIndex].setStudent(correctLengthData[i][2].strip(),
                        correctLengthData[i][3].strip(), date[0], date[1], date[2], correctLengthData[i][5].strip(),
                        Integer.parseInt(correctLengthData[i][6].strip()), correctLengthData[i][7].strip(),
                        correctLengthData[i][8].strip(), correctLengthData[i][9].strip());

                internshipIndex++;
            }
        }
        //Insertion sort//
        //Sorts the companies by their Interncount
        int n = companies.length; 
        for (int i = 1; i < n; ++i) { 
            int key = companies[i].getInternCount(); 
            int j = i - 1; 
            Company mem = companies[i];

            while (j >= 0 && companies[j].getInternCount() > key) { 
                companies[j + 1] = companies[j]; 
                j = j - 1; 
            } 
            companies[j + 1] = mem; 
        } 
    }
}