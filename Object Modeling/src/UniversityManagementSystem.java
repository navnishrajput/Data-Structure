import java.util.*;

class Professor {
    private String professorId;
    private String name;
    private String department;
    private List<Course> coursesTeaching;

    public Professor(String professorId, String name, String department) {
        this.professorId = professorId;
        this.name = name;
        this.department = department;
        this.coursesTeaching = new ArrayList<>();
    }

    public String getProfessorId() {
        return professorId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void assignToCourse(Course course) {
        coursesTeaching.add(course);
        course.assignProfessor(this);
        System.out.println("Professor " + name + " assigned to course: " + course.getCourseName());
    }

    public void displayTeachingCourses() {
        System.out.println("Courses taught by Professor " + name + ":");
        for (Course course : coursesTeaching) {
            System.out.println("- " + course.getCourseName());
        }
    }
}

class Course {
    private String courseId;
    private String courseName;
    private Professor professor;
    private List<Student> enrolledStudents;

    public Course(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void assignProfessor(Professor professor) {
        this.professor = professor;
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
        System.out.println("Student " + student.getName() + " enrolled in course: " + courseName);
    }

    public void displayCourseInfo() {
        System.out.println("Course: " + courseName + " (ID: " + courseId + ")");
        if (professor != null) {
            System.out.println("Professor: " + professor.getName());
        } else {
            System.out.println("Professor: Not assigned");
        }
        System.out.println("Enrolled Students:");
        for (Student student : enrolledStudents) {
            System.out.println("- " + student.getName());
        }
    }
}

class Student {
    private String studentId;
    private String name;
    private List<Course> enrolledCourses;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.enrolledCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void enrollCourse(Course course) {
        enrolledCourses.add(course);
        course.enrollStudent(this);
    }

    public void displayEnrolledCourses() {
        System.out.println("Courses enrolled by " + name + ":");
        for (Course course : enrolledCourses) {
            System.out.println("- " + course.getCourseName());
        }
    }
}

public class UniversityManagementSystem {
    public static void main(String[] args) {
        Professor prof1 = new Professor("P001", "Dr. Smith", "Computer Science");
        Professor prof2 = new Professor("P002", "Dr. Johnson", "Mathematics");

        Student student1 = new Student("S001", "Alice Brown");
        Student student2 = new Student("S002", "Bob Wilson");
        Student student3 = new Student("S003", "Carol Davis");

        Course cs101 = new Course("CS101", "Introduction to Programming");
        Course math201 = new Course("MATH201", "Calculus I");
        Course cs202 = new Course("CS202", "Data Structures");

        prof1.assignToCourse(cs101);
        prof1.assignToCourse(cs202);
        prof2.assignToCourse(math201);

        student1.enrollCourse(cs101);
        student1.enrollCourse(math201);
        student2.enrollCourse(cs101);
        student2.enrollCourse(cs202);
        student3.enrollCourse(math201);
        student3.enrollCourse(cs202);

        System.out.println();
        cs101.displayCourseInfo();
        System.out.println();
        math201.displayCourseInfo();
        System.out.println();
        cs202.displayCourseInfo();
        System.out.println();

        student1.displayEnrolledCourses();
        System.out.println();
        prof1.displayTeachingCourses();
    }
}