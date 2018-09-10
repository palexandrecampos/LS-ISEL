package pt.isel.ls;

public class ResolveURL {
    public static String getOrPostStudent(int id){
        return "/students/"+id;
    }

    public static String getOrPostTeacher(int id){
        return "/teachers/"+id;
    }

    public static String getOrPostSpecificProgramme(String acronym){
        return "/programmes/"+acronym;
    }

    public static String getOrPostProgramme(){
        return "/programmes";
    }

    public static String getOrPostCourse(String acronym){
        return "/courses/"+acronym;
    }

    public static String getOrPostClassOfCourse(String acronym){
        return "/courses/"+acronym+"/classes";
    }

    public static String getOrPostSpecificClass(String acronym, String yearSemester, String semester, String identifier){
        String s = yearSemester.concat(semester.equals("summer") ? "v" : "i");
        return "/courses/"+acronym+"/classes/"+s+"/"+identifier;
    }

    public static String getStudents(){
        return "/students";
    }

    public static String getTeachers(){
        return "/teachers";
    }

    public static String getHome() {
        return "/";
    }

    public static String getCourseOfTeacher(String acronym) {
        return "/courses/"+acronym;
    }

    public static String postCourseToProgramme(String acronym){ return "/programmes/"+acronym+"/courses"; }

    public static String postTeacherToClass(String acronym, String yearSemester, String semester, String identifier){
        String s = yearSemester.concat(semester.equals("summer") ? "v" : "i");
        return "/courses/"+acronym+"/classes/"+s+"/"+identifier+"/teachers"; }

    public static String postStudentToClass(String acronym, String yearSemester, String semester, String identifier){
        String s = yearSemester.concat(semester.equals("summer") ? "v" : "i");
        return "/courses/"+acronym+"/classes/"+s+"/"+identifier+"/students"; }

}
