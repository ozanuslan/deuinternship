public class Internship {
    private String trainingType;
    private String startDate;
    private String endDate;
    private Company company;
    private Student student;

    public Internship(String trainingType, String startDate, String endDate, Company company, Student student) {
        this.trainingType = trainingType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.company = company;
        this.student = student;
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

    public Company getCompany() {
        return this.company;
    }

    public Student getStudent() {
        return this.student;
    }

    public class Student {
        private int num;
        private String name;
        private String birthdate;
        private String gender;
        private int year;
        private String phone;
        
        public int getNum() {
            return this.num;
        }
    
        public String getName() {
            return this.name;
        }
    
        public String getBirthdate() {
            return this.birthdate;
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
    }
}