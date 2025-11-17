import java.util.*;

abstract class JobRole {
    protected String title;
    protected List<String> requiredSkills;
    protected int minExperience;

    public JobRole(String title, List<String> requiredSkills, int minExperience) {
        this.title = title;
        this.requiredSkills = new ArrayList<>(requiredSkills);
        this.minExperience = minExperience;
    }

    public abstract double calculateMatchScore(Resume<?> resume);

    public String getTitle() { return title; }
    public List<String> getRequiredSkills() { return new ArrayList<>(requiredSkills); }
    public int getMinExperience() { return minExperience; }

    @Override
    public String toString() {
        return String.format("%s (Min Exp: %d years)", title, minExperience);
    }
}

class SoftwareEngineer extends JobRole {
    private List<String> programmingLanguages;

    public SoftwareEngineer(List<String> programmingLanguages, int minExperience) {
        super("Software Engineer",
                Arrays.asList("Programming", "Problem Solving", "Algorithms"),
                minExperience);
        this.programmingLanguages = new ArrayList<>(programmingLanguages);
    }

    @Override
    public double calculateMatchScore(Resume<?> resume) {
        double score = 0.0;
        Resume<?> r = resume;

        if (r.getExperienceYears() >= minExperience) {
            score += 40.0;
        }

        long matchingSkills = r.getSkills().stream()
                .filter(skill -> requiredSkills.contains(skill) ||
                        programmingLanguages.contains(skill))
                .count();
        score += (matchingSkills * 20.0);

        return Math.min(score, 100.0);
    }

    @Override
    public String toString() {
        return super.toString() + " [Languages: " + programmingLanguages + "]";
    }
}

class DataScientist extends JobRole {
    private List<String> dataTools;

    public DataScientist(List<String> dataTools, int minExperience) {
        super("Data Scientist",
                Arrays.asList("Statistics", "Machine Learning", "Data Analysis"),
                minExperience);
        this.dataTools = new ArrayList<>(dataTools);
    }

    @Override
    public double calculateMatchScore(Resume<?> resume) {
        double score = 0.0;
        Resume<?> r = resume;

        if (r.getExperienceYears() >= minExperience) {
            score += 35.0;
        }

        long matchingSkills = r.getSkills().stream()
                .filter(skill -> requiredSkills.contains(skill) ||
                        dataTools.contains(skill))
                .count();
        score += (matchingSkills * 22.0);

        if (r.getEducation().toLowerCase().contains("data") ||
                r.getEducation().toLowerCase().contains("statistics")) {
            score += 15.0;
        }

        return Math.min(score, 100.0);
    }

    @Override
    public String toString() {
        return super.toString() + " [Tools: " + dataTools + "]";
    }
}

class ProductManager extends JobRole {
    public ProductManager(int minExperience) {
        super("Product Manager",
                Arrays.asList("Product Strategy", "Market Research", "Agile", "Stakeholder Management"),
                minExperience);
    }

    @Override
    public double calculateMatchScore(Resume<?> resume) {
        double score = 0.0;
        Resume<?> r = resume;

        if (r.getExperienceYears() >= minExperience) {
            score += 30.0;
        }

        long matchingSkills = r.getSkills().stream()
                .filter(requiredSkills::contains)
                .count();
        score += (matchingSkills * 25.0);

        if (r.getEducation().toLowerCase().contains("mba") ||
                r.getEducation().toLowerCase().contains("business")) {
            score += 20.0;
        }

        return Math.min(score, 100.0);
    }
}

class Resume<T extends JobRole> {
    private String candidateName;
    private String education;
    private int experienceYears;
    private List<String> skills;
    private T targetRole;

    public Resume(String candidateName, String education, int experienceYears,
                  List<String> skills, T targetRole) {
        this.candidateName = candidateName;
        this.education = education;
        this.experienceYears = experienceYears;
        this.skills = new ArrayList<>(skills);
        this.targetRole = targetRole;
    }

    public double getMatchScore() {
        return targetRole.calculateMatchScore(this);
    }

    public String getCandidateName() { return candidateName; }
    public String getEducation() { return education; }
    public int getExperienceYears() { return experienceYears; }
    public List<String> getSkills() { return new ArrayList<>(skills); }
    public T getTargetRole() { return targetRole; }

    @Override
    public String toString() {
        return String.format("Resume[%s: %s, Exp: %d years, Match: %.1f%%]",
                candidateName, targetRole.getTitle(), experienceYears, getMatchScore());
    }
}

class AIScreeningSystem {
    public static void screenResumes(List<? extends Resume<?>> resumes) {
        System.out.println("AI Resume Screening Results:");
        System.out.println("============================");

        resumes.stream()
                .sorted((r1, r2) -> Double.compare(r2.getMatchScore(), r1.getMatchScore()))
                .forEach(resume -> {
                    String status = resume.getMatchScore() >= 70 ? "✓ RECOMMENDED" :
                            resume.getMatchScore() >= 50 ? "~ MAYBE" : "✗ REJECT";
                    System.out.printf("%s - %s (%.1f%%)\n", status, resume, resume.getMatchScore());
                });
    }

    public static void analyzeJobRoles(List<? extends JobRole> jobRoles) {
        System.out.println("\nAvailable Job Roles:");
        System.out.println("====================");
        for (JobRole role : jobRoles) {
            System.out.println("  - " + role);
        }
    }
}

public class ResumeScreeningSystem {
    public static void main(String[] args) {
        SoftwareEngineer seRole = new SoftwareEngineer(Arrays.asList("Java", "Python", "JavaScript"), 3);
        DataScientist dsRole = new DataScientist(Arrays.asList("Python", "R", "SQL", "TensorFlow"), 2);
        ProductManager pmRole = new ProductManager(4);

        Resume<SoftwareEngineer> resume1 = new Resume<>(
                "Alice Johnson", "BS Computer Science", 5,
                Arrays.asList("Java", "Python", "Algorithms", "System Design", "Agile"),
                seRole
        );

        Resume<DataScientist> resume2 = new Resume<>(
                "Bob Smith", "MS Data Science", 3,
                Arrays.asList("Python", "Machine Learning", "Statistics", "SQL", "Tableau"),
                dsRole
        );

        Resume<ProductManager> resume3 = new Resume<>(
                "Carol Davis", "MBA", 6,
                Arrays.asList("Product Strategy", "Market Research", "Agile", "Leadership"),
                pmRole
        );

        Resume<SoftwareEngineer> resume4 = new Resume<>(
                "David Wilson", "BS Engineering", 1,
                Arrays.asList("JavaScript", "HTML", "CSS"),
                seRole
        );

        List<Resume<?>> allResumes = Arrays.asList(resume1, resume2, resume3, resume4);
        List<JobRole> allJobRoles = Arrays.asList(seRole, dsRole, pmRole);

        AIScreeningSystem.analyzeJobRoles(allJobRoles);
        AIScreeningSystem.screenResumes(allResumes);
    }
}