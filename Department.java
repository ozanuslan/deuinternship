public class Department {

    private String code;
    private String name;

    public String getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    public class Instructor {
        private String title;
        private String name;

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

        public String getType(){
            return type;
        }

        public int getDuration(){
            return duration;
        }
    }
}