
public class Department {
    static int globalInstructorID;
    private String code;
    private String name;
    private Instructor[] INSTRUCTORS;
    private Training[] TRAININGS;

    public Department(String departmentCode,String departmentName){
        this.code = departmentCode;
        this.name = departmentName;
        INSTRUCTORS = new Instructor[3];
        TRAININGS = new Training[3];
    }

    public String getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public Instructor[] getInstructors(){
        return INSTRUCTORS;
    }

    public Training[] getTrainings(){
        return TRAININGS;
    }

    public void setInstructor(String title, String name, int i){
        INSTRUCTORS[i] = new Instructor(title, name);
    }

    public void setTraining(String type, int duration, int i){
        TRAININGS[i] = new Training(type, duration);
    }

    public void displayDepartment(){
        System.out.print(code+" "+name+" (");
        for(int i = 0; i < TRAININGS.length; i++){
            if(i < TRAININGS.length-1){
                System.out.print(TRAININGS[i].getType()+",");
            }
            else{
                System.out.print(TRAININGS[i].getType());
            }
        }
        System.out.println(")");
    }

    public void displayInstructor(int i){
        INSTRUCTORS[i].displayInstructor();
    }

    public class Instructor {
        private int id;
        private String title;
        private String name;

        public Instructor(String title, String name){
            this.title = title;
            this.name = name;
            this.id = globalInstructorID;
            globalInstructorID++;
        }

        public String getTitle(){
            return title;
        }

        public String getName(){
            return name;
        }

        public int getID(){
            return id;
        }

        public void displayInstructor(){
            System.out.println(Department.this.code+" "+title+" "+name);
        }
    }

    public class Training {
        private String type;
        private int duration;

        public Training(String type, int duration){
            this.type = type;
            this.duration = duration;
        }

        public String getType(){
            return type;
        }

        public int getDuration(){
            return duration;
        }
    }
}