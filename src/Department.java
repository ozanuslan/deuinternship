public class Department {
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

    public class Instructor {
        private String title;
        private String name;

        public Instructor(String title, String name){
            this.title = title;
            this.name = name;
        }

        public String getTitle(){
            return title;
        }

        public String getName(){
            return name;
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