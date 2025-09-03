public class TaskThree {
    /*
     * Given the code below: how would you improve it
     * */

    public static class Student {
        private boolean loggedIn;
        private String teacherName;
        private boolean enrolledInMaths;
        private boolean distinction;

        public Student(boolean loggedIn, String teacherName,
                       boolean enrolledInMaths, boolean distinction) {
            this.loggedIn = loggedIn;
            this.teacherName = teacherName;
            this.enrolledInMaths = enrolledInMaths;
            this.distinction = distinction;
        }

        private final String LEE = "Lee";

        public boolean isLoggedIn() {return this.loggedIn;}
        public boolean isEnrolledInMaths() {return this.enrolledInMaths;}
        public boolean hasDistinction() {return this.distinction;}
        public String getTeacherName() {return this.teacherName;}

        /*
         * MY FEEDBACK #3: 1. If it is assumed that the parameter student
         * will never be null, to improve further, I would advise putting
         * the checkStudy method inside the Student class as a public method
         * instead, so you have direct access to attributes. Also, make “Lee”
         * literal as a final constant in the Student object. This would
         * make the code cleaner and more readable.
         * */
        public boolean checkStudy() {
            return loggedIn && enrolledInMaths && distinction
                    && LEE.equals(teacherName);
        }
    }

    public static boolean checkStudy(Student student) {
        if(student.isLoggedIn()) {
            if(student.getTeacherName() == "Lee") {
                if(student.isEnrolledInMaths()) {
                    if(student.hasDistinction()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /*
     * MY FEEDBACK #1: There can be an underlying issue with regard
     * to using == on string comparison. It is not null-safe.
     * Try to use String.equals() method instead.
     * It is by convention the more correct way of comparing string
     * values. You make sure to check for null possibilities.
     */

    public static boolean checkStudy1(Student student) {
        if(student.isLoggedIn()) {
            if("Lee".equals(student.getTeacherName())) {
                if(student.isEnrolledInMaths()) {
                    if(student.hasDistinction()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /*
     * MY FEEDBACK #2: While it works, based on your purpose
     * that everything should be true to return true,
     * we can clean the nested if blocks and convert them into a
     * singular return using the && operator; and, omit the return false.
     */

    public static boolean checkStudy2(Student student) {
        return student.isLoggedIn()
                && "Lee".equals(student.getTeacherName())
                && student.isEnrolledInMaths()
                && student.hasDistinction();
    }

    /*
     * MY FEEDBACK #4: If not assumed that the parameter student will
     * never be null, then put a null checker if block before calling
     * the object methods for code safety.
     */

    public static boolean checkStudy4(Student student) {
        if(student == null) return false;

        return student.isLoggedIn()
                && "Lee".equals(student.getTeacherName())
                && student.isEnrolledInMaths()
                && student.hasDistinction();
    }


    // Testing that all have the same results
    public static void main(String[] args) {
        Student stud1 = new Student(true, "Lee", true, true);
        Student stud2 = new Student(false, "Lee", true, true);
        Student stud3 = new Student(true, "Lala", true, true);
        Student stud4 = new Student(true, "Lee", false, true);
        Student stud5 = new Student(true, "Lee", true, false);

        System.out.println(checkStudy(stud1) + " " +  checkStudy(stud2)
                + " " +  checkStudy(stud3) + " " +  checkStudy(stud4)
                + " " + checkStudy(stud5));

        System.out.println(checkStudy1(stud1) + " " +  checkStudy1(stud2)
                + " " +  checkStudy1(stud3) + " " +  checkStudy1(stud4)
                + " " + checkStudy1(stud5));

        System.out.println(checkStudy2(stud1) + " " +  checkStudy2(stud2)
                + " " +  checkStudy2(stud3) + " " +  checkStudy2(stud4)
                + " " + checkStudy2(stud5));

        System.out.println(stud1.checkStudy() + " " + stud2.checkStudy()
                + " " +  stud3.checkStudy() + " " +  stud4.checkStudy()
                + " " + stud5.checkStudy());

        System.out.println(checkStudy4(stud1) + " " +  checkStudy4(stud2)
                + " " +  checkStudy4(stud3) + " " +  checkStudy4(stud4)
                + " " + checkStudy4(stud5));
    }
}
