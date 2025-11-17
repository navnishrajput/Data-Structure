import java.util.*;

abstract class CourseType {
    protected String code;
    protected String name;
    protected int credits;

    public CourseType(String code, String name, int credits) {
        this.code = code;
        this.name = name;
        this.credits = credits;
    }

    public abstract String getEvaluationMethod();

    @Override
    public String toString() {
        return String.format("%s: %s (%d credits) - %s", code, name, credits, getEvaluationMethod());
    }
}

class ExamCourse extends CourseType {
    private int examDuration;

    public ExamCourse(String code, String name, int credits, int examDuration) {
        super(code, name, credits);
        this.examDuration = examDuration;
    }

    @Override
    public String getEvaluationMethod() {
        return "Exam-based (" + examDuration + " minutes)";
    }
}

class AssignmentCourse extends CourseType {
    private int numberOfAssignments;

    public AssignmentCourse(String code, String name, int credits, int numberOfAssignments) {
        super(code, name, credits);
        this.numberOfAssignments = numberOfAssignments;
    }

    @Override
    public String getEvaluationMethod() {
        return "Assignment-based (" + numberOfAssignments + " assignments)";
    }
}

class ResearchCourse extends CourseType {
    private String researchTopic;

    public ResearchCourse(String code, String name, int credits, String researchTopic) {
        super(code, name, credits);
        this.researchTopic = researchTopic;
    }

    @Override
    public String getEvaluationMethod() {
        return "Research-based (" + researchTopic + ")";
    }
}

class Course<T extends CourseType> {
    private T courseType;
    private String instructor;
    private int maxStudents;
    private List<String> enrolledStudents;

    public Course(T courseType, String instructor, int maxStudents) {
        this.courseType = courseType;
        this.instructor = instructor;
        this.maxStudents = maxStudents;
        this.enrolledStudents = new ArrayList<>();
    }

    public boolean enrollStudent(String studentId) {
        if (enrolledStudents.size() < maxStudents) {
            enrolledStudents.add(studentId);
            return true;
        }
        return false;
    }

    public T getCourseType() { return courseType; }
    public String getInstructor() { return instructor; }
    public List<String> getEnrolledStudents() { return new ArrayList<>(enrolledStudents); }

    @Override
    public String toString() {
        return String.format("Course[%s, Instructor: %s, Enrolled: %d/%d]",
                courseType, instructor, enrolledStudents.size(), maxStudents);
    }
}

class Department {
    private String name;
    private List<? extends CourseType> courses;

    public Department(String name, List<? extends CourseType> courses) {
        this.name = name;
        this.courses = new ArrayList<>(courses);
    }

    public static void displayAllCourses(List<? extends CourseType> courses) {
        System.out.println("All courses:");
        for (CourseType course : courses) {
            System.out.println("  - " + course);
        }
    }

    public void displayDepartmentCourses() {
        System.out.println("Department: " + name);
        displayAllCourses(courses);
    }
}

public class UniversityCourseManagement {
    public static void main(String[] args) {
        ExamCourse mathCourse = new ExamCourse("MATH101", "Calculus I", 4, 180);
        AssignmentCourse csCourse = new AssignmentCourse("CS201", "Data Structures", 3, 5);
        ResearchCourse physicsCourse = new ResearchCourse("PHY301", "Quantum Mechanics", 4, "Quantum Entanglement");

        Course<ExamCourse> math = new Course<>(mathCourse, "Dr. Smith", 30);
        Course<AssignmentCourse> cs = new Course<>(csCourse, "Dr. Johnson", 25);
        Course<ResearchCourse> physics = new Course<>(physicsCourse, "Dr. Brown", 15);

        math.enrollStudent("S001");
        math.enrollStudent("S002");
        cs.enrollStudent("S001");
        cs.enrollStudent("S003");
        physics.enrollStudent("S004");

        List<CourseType> allCourseTypes = Arrays.asList(mathCourse, csCourse, physicsCourse);
        Department.displayAllCourses(allCourseTypes);

        System.out.println("\nCourse instances:");
        System.out.println("Math: " + math);
        System.out.println("CS: " + cs);
        System.out.println("Physics: " + physics);
    }
}