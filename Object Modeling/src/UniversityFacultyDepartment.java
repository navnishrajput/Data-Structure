import java.util.*;

class Faculty {
    private String name;
    private String specialization;

    public Faculty(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void displayInfo() {
        System.out.println("Faculty: " + name + ", Specialization: " + specialization);
    }
}

class Department {
    private String departmentName;
    private List<Faculty> faculties;

    public Department(String departmentName) {
        this.departmentName = departmentName;
        this.faculties = new ArrayList<>();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void addFaculty(Faculty faculty) {
        faculties.add(faculty);
    }

    public void displayDepartmentInfo() {
        System.out.println("Department: " + departmentName);
        System.out.println("Faculties:");
        for (Faculty faculty : faculties) {
            System.out.println("- " + faculty.getName());
        }
    }
}

class University {
    private String universityName;
    private List<Department> departments;
    private List<Faculty> universityFaculties;

    public University(String universityName) {
        this.universityName = universityName;
        this.departments = new ArrayList<>();
        this.universityFaculties = new ArrayList<>();
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void addFaculty(Faculty faculty) {
        universityFaculties.add(faculty);
    }

    public void displayUniversityInfo() {
        System.out.println("University: " + universityName);
        System.out.println("Departments:");
        for (Department department : departments) {
            System.out.println("- " + department.getDepartmentName());
        }
        System.out.println("All Faculties:");
        for (Faculty faculty : universityFaculties) {
            faculty.displayInfo();
        }
    }

    public void closeUniversity() {
        System.out.println("Closing " + universityName + "...");
        departments.clear();
        System.out.println("All departments have been deleted.");
    }
}

public class UniversityFacultyDepartment {
    public static void main(String[] args) {
        University university = new University("Tech University");

        Faculty faculty1 = new Faculty("Dr. Smith", "Computer Science");
        Faculty faculty2 = new Faculty("Dr. Johnson", "Mathematics");
        Faculty faculty3 = new Faculty("Dr. Brown", "Physics");

        Department csDept = new Department("Computer Science");
        Department mathDept = new Department("Mathematics");

        csDept.addFaculty(faculty1);
        mathDept.addFaculty(faculty2);

        university.addDepartment(csDept);
        university.addDepartment(mathDept);
        university.addFaculty(faculty1);
        university.addFaculty(faculty2);
        university.addFaculty(faculty3);

        university.displayUniversityInfo();

        university.closeUniversity();

        faculty3.displayInfo();
    }
}