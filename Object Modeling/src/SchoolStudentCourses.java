import java.util.*;

class Course {
    private String courseName;
    private Set<Student> enrolledStudents;

    public Course(String courseName) {
        this.courseName = courseName;
        this.enrolledStudents = new HashSet<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    public void displayEnrolledStudents() {
        System.out.println("Students enrolled in " + courseName + ":");
        for (Student student : enrolledStudents) {
            System.out.println("- " + student.getName());
        }
    }
}

class Student {
    private String name;
    private Set<Course> enrolledCourses;

    public Student(String name) {
        this.name = name;
        this.enrolledCourses = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
        course.enrollStudent(this);
    }

    public void displayEnrolledCourses() {
        System.out.println(name + "'s enrolled courses:");
        for (Course course : enrolledCourses) {
            System.out.println("- " + course.getCourseName());
        }
    }
}

class School {
    private String schoolName;
    private List<Student> students;

    public School(String schoolName) {
        this.schoolName = schoolName;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void displayAllStudents() {
        System.out.println("Students in " + schoolName + ":");
        for (Student student : students) {
            System.out.println("- " + student.getName());
        }
    }
}

public class SchoolStudentCourses {
    public static void main(String[] args) {
        School school = new School("Greenwood High");

        Student student1 = new Student("Alice");
        Student student2 = new Student("Bob");

        Course math = new Course("Mathematics");
        Course science = new Course("Science");
        Course history = new Course("History");

        student1.enrollInCourse(math);
        student1.enrollInCourse(science);
        student2.enrollInCourse(math);
        student2.enrollInCourse(history);

        school.addStudent(student1);
        school.addStudent(student2);

        school.displayAllStudents();
        student1.displayEnrolledCourses();
        student2.displayEnrolledCourses();
        math.displayEnrolledStudents();
        science.displayEnrolledStudents();
    }
}