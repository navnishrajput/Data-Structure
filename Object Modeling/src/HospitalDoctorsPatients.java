import java.util.*;

class Patient {
    private String name;
    private int age;
    private List<Doctor> consultingDoctors;

    public Patient(String name, int age) {
        this.name = name;
        this.age = age;
        this.consultingDoctors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void addConsultingDoctor(Doctor doctor) {
        consultingDoctors.add(doctor);
    }

    public void displayConsultingDoctors() {
        System.out.println("Doctors consulting " + name + ":");
        for (Doctor doctor : consultingDoctors) {
            System.out.println("- " + doctor.getName());
        }
    }
}

class Doctor {
    private String name;
    private String specialization;
    private List<Patient> patients;

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
        this.patients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void consult(Patient patient) {
        patients.add(patient);
        patient.addConsultingDoctor(this);
        System.out.println("Dr. " + name + " is consulting patient " + patient.getName() + " (Specialization: " + specialization + ")");
    }

    public void displayPatients() {
        System.out.println("Patients of Dr. " + name + ":");
        for (Patient patient : patients) {
            System.out.println("- " + patient.getName() + " (Age: " + patient.getAge() + ")");
        }
    }
}

class Hospital {
    private String hospitalName;
    private List<Doctor> doctors;
    private List<Patient> patients;

    public Hospital(String hospitalName) {
        this.hospitalName = hospitalName;
        this.doctors = new ArrayList<>();
        this.patients = new ArrayList<>();
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void displayHospitalInfo() {
        System.out.println("Hospital: " + hospitalName);
        System.out.println("Doctors:");
        for (Doctor doctor : doctors) {
            System.out.println("- Dr. " + doctor.getName() + " (" + doctor.getSpecialization() + ")");
        }
        System.out.println("Patients:");
        for (Patient patient : patients) {
            System.out.println("- " + patient.getName() + " (Age: " + patient.getAge() + ")");
        }
    }
}

public class HospitalDoctorsPatients {
    public static void main(String[] args) {
        Hospital hospital = new Hospital("City General Hospital");

        Doctor doctor1 = new Doctor("Smith", "Cardiology");
        Doctor doctor2 = new Doctor("Johnson", "Neurology");
        Doctor doctor3 = new Doctor("Brown", "Pediatrics");

        Patient patient1 = new Patient("Alice", 35);
        Patient patient2 = new Patient("Bob", 45);
        Patient patient3 = new Patient("Charlie", 28);

        hospital.addDoctor(doctor1);
        hospital.addDoctor(doctor2);
        hospital.addDoctor(doctor3);
        hospital.addPatient(patient1);
        hospital.addPatient(patient2);
        hospital.addPatient(patient3);

        hospital.displayHospitalInfo();

        doctor1.consult(patient1);
        doctor1.consult(patient2);
        doctor2.consult(patient1);
        doctor2.consult(patient3);
        doctor3.consult(patient2);

        System.out.println();
        doctor1.displayPatients();
        doctor2.displayPatients();
        patient1.displayConsultingDoctors();
        patient2.displayConsultingDoctors();
    }
}