
public class Internship {
    static int globalInternshipID;
    private String trainingType;
    private String startDate;
    private String endDate;
    private int internshipDuration;
    private int id;
    private Company company;
    private Student student;

    public Internship(String trainingType, String startDate, String endDate, int internshipDuration) {
        this.trainingType = trainingType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.internshipDuration = internshipDuration;
        this.id = globalInternshipID;
        globalInternshipID++;
    }

    public String getTrainingType() {
        return this.trainingType;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public int getInternshipDuration() {
        return this.internshipDuration;
    }

    public Company getCompany() {
        return this.company;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setCompany(Company company){
        this.company = company;
    }

    public void setStudent(String num, String name, String birthDay, String birthMonth, String birthYear, String gender,
            int year, String phoneCountryCode, String phoneAreaCode, String phoneNumber) {
        student = new Student(num, name, birthDay, birthMonth, birthYear, gender, year, phoneCountryCode, phoneAreaCode, phoneNumber);
    }

    public void displayInternship(){
        System.out.println(student.getNum()+" "+company.getName()+" "+trainingType+" "+internshipDuration);
    }

    public void displayStudent(){
        student.displayStudent();
    }

    public class Student {
        private String num;
        private String name;
        private String birthDate;
        private String gender;
        private int year;
        private String phone;

        public Student(String num, String name, String birthDay, String birthMonth, String birthYear, String gender,
                int year, String phoneCountryCode, String phoneAreaCode, String phoneNumber) {
            this.num = num;
            this.name = name;
            this.birthDate = birthDay + "." + birthMonth + "." + birthYear;
            this.gender = gender;
            this.year = year;
            this.phone = phoneCountryCode + phoneAreaCode + phoneNumber;
        }

        public String getNum() {
            return this.num;
        }

        public String getName() {
            return this.name;
        }

        public String getBirthdate() {
            return this.birthDate;
        }

        public String getGender() {
            return this.gender;
        }

        public int getYear() {
            return this.year;
        }

        public String getPhone() {
            return this.phone;
        }

        public void displayStudent(){
            System.out.println(num+" "+name+" "+gender+" "+birthDate+" Year:"+year+" "+phone);
        }
    }
}