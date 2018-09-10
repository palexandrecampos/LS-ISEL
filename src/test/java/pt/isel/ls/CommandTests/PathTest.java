package pt.isel.ls.CommandTests;

import pt.isel.ls.DB;
import pt.isel.ls.Path;
import pt.isel.ls.Request;
import pt.isel.ls.commands.Exit;
import pt.isel.ls.commands.Option;
import pt.isel.ls.commands.Response;
import pt.isel.ls.commands.classManagement.GetClassesFromCourse;
import pt.isel.ls.commands.classManagement.GetClassesFromCourseSemester;
import pt.isel.ls.commands.classManagement.GetClassesFromCourseSemesterNumber;
import pt.isel.ls.commands.classManagement.PostClass;
import pt.isel.ls.commands.course.GetCourse;
import pt.isel.ls.commands.course.GetCourseWithAcronym;
import pt.isel.ls.commands.course.PostCourse;
import pt.isel.ls.commands.programme.*;
import pt.isel.ls.commands.student.GetAllStudentsOfClassOrderByNum;
import pt.isel.ls.commands.student.GetStudentsFromClass;
import pt.isel.ls.commands.student.PostStudentToAClass;
import pt.isel.ls.commands.student.RemoveStudentFromClass;
import pt.isel.ls.commands.teacher.GetClassesForATeacher;
import pt.isel.ls.commands.teacher.GetTeachersForAClass;
import pt.isel.ls.commands.teacher.PostTeacherToAClass;
import pt.isel.ls.commands.user.*;

/**
 * Created by artur on 01/04/2017.
 */
public class PathTest {

    public static final String FILENAME_ARG = "file-name:";
    public static final String ACCEPT_ARG = "accept:";
    public static final String JSON_ARG = "application/json";
    public static final String PLAIN_ARG = "text/plain";
    public static final String HTML_ARG = "text/html";
    public static final String TOP_ARG = "top=";
    public static final String SKIP_ARG = "skip=";

    public static Path createPath() {
        Path path = new Path();
        path.addPath("GET", "/users", new GetUsers());
        path.addPath("GET","/courses/{acr}/classes", new GetClassesFromCourse());
        path.addPath("GET","/courses/{acr}/classes/{sem}", new GetClassesFromCourseSemester());
        path.addPath("GET","/courses/{acr}/classes/{sem}/{num}", new GetClassesFromCourseSemesterNumber());
        path.addPath("GET","/courses/{acr}/classes/{sem}/{num}/students", new GetStudentsFromClass());//
        path.addPath("GET","/courses/{acr}/classes/{sem}/{num}/students/sorted", new GetAllStudentsOfClassOrderByNum());
        path.addPath("GET","/courses", new GetCourse());
        path.addPath("GET","/courses/{acr}", new GetCourseWithAcronym());
        path.addPath("GET","/programmes", new GetProgramme());
        path.addPath("GET","/programmes/{pid}/courses", new GetCourseofProgramme());
        path.addPath("GET","/programmes/{pid}", new GetProgrammeWithAcronym());
        path.addPath("GET","/teachers/{num}/classes", new GetClassesForATeacher());
        path.addPath("GET","/courses/{acr}/classes/{sem}/{num}/teachers", new GetTeachersForAClass());
        path.addPath("GET","/students", new GetStudents());
        path.addPath("GET","/teachers", new GetTeachers());
        path.addPath("GET","/students/{num}", new GetStudentsWithNum());
        path.addPath("GET","/teachers/{num}", new GetTeachersWithNum());
        path.addPath("POST","/programmes/{pid}/courses", new PostCourseToTheProgramme());
        path.addPath("POST","/programmes", new PostProgramme());
        path.addPath("POST","/courses", new PostCourse());
        path.addPath("POST","/courses/{acr}/classes/{sem}/{num}/teachers", new PostTeacherToAClass());
        path.addPath("POST","/courses/{acr}/classes/{sem}/{num}/students", new PostStudentToAClass());
        path.addPath("POST","/courses/{acr}/classes", new PostClass());
        path.addPath("POST","/students", new PostStudent());
        path.addPath("PUT","/students/{num}", new UpdateStudent());
        path.addPath("POST","/teachers", new PostTeacher());
        path.addPath("PUT","/teachers/{num}", new UpdateTeacher());
        path.addPath("DELETE","/courses/{acr}/classes/{sem}/{num}/students/{numStu}", new RemoveStudentFromClass());
        path.addPath("OPTION","/", new Option());
        path.addPath("EXIT","/", new Exit());
        return path;
    }

    public static void runRequest(String[] args) throws Exception {
        Path path = createPath();
        Request request = Request.create(args);
        Response response = Response.createResponse(request.getHeader("file-name"));
        path.getCommand(request).execute(DB.getDatasrcTest(),request, response);
        }
}
