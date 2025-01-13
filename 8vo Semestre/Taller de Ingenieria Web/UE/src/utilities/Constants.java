package utilities;

import models.Enrollment;
import models.Subject;
import models.User;
import models.UserType;

import java.util.ArrayList;
import java.util.Arrays;

public class Constants {
    private static final User[] usersArray = {
            new User(1, "admin", "1", "admin", "admin", UserType.ADMIN),
            new User(2, "teacher", "1","teacher", "teacher", UserType.TEACHER),
            new User(3, "student", "1","student", "student", UserType.STUDENT),
    };

    private static final Subject [] subjectsArray = {
            new Subject(1, "Matemáticas", 2),
            new Subject(2, "Español", 2),
            new Subject(3, "Inglés", 3),
    };

    private static final Enrollment [] enrollmentsArray = {
            new Enrollment(3, 1, 100),
            new Enrollment(3, 2, 90),
            new Enrollment(4, 3, 80),
    };

    public static final ArrayList<User> users = new ArrayList<>(Arrays.asList(usersArray));
    public static final ArrayList<Subject> subjects = new ArrayList<>(Arrays.asList(subjectsArray));
    public static final ArrayList<Enrollment> enrollments = new ArrayList<>(Arrays.asList(enrollmentsArray));
}
