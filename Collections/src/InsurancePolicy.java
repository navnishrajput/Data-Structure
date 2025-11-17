import java.util.*;
import java.time.LocalDate;

class Policy {
    private String policyNumber;
    private String policyholderName;
    private LocalDate expiryDate;
    private String coverageType;
    private double premiumAmount;

    public Policy(String policyNumber, String policyholderName, LocalDate expiryDate,
                  String coverageType, double premiumAmount) {
        this.policyNumber = policyNumber;
        this.policyholderName = policyholderName;
        this.expiryDate = expiryDate;
        this.coverageType = coverageType;
        this.premiumAmount = premiumAmount;
    }

    public String getPolicyNumber() { return policyNumber; }
    public String getPolicyholderName() { return policyholderName; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public String getCoverageType() { return coverageType; }
    public double getPremiumAmount() { return premiumAmount; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Policy)) return false;
        Policy policy = (Policy) o;
        return policyNumber.equals(policy.policyNumber);
    }

    @Override
    public int hashCode() {
        return policyNumber.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Policy[%s, %s, %s, %s, $%.2f]",
                policyNumber, policyholderName, expiryDate, coverageType, premiumAmount);
    }
}

public class InsurancePolicy {
    private Set<Policy> hashSet;
    private Set<Policy> linkedHashSet;
    private Set<Policy> treeSetByExpiry;

    public InsurancePolicy() {
        hashSet = new HashSet<>();
        linkedHashSet = new LinkedHashSet<>();
        treeSetByExpiry = new TreeSet<>(Comparator.comparing(Policy::getExpiryDate));
    }

    public void addPolicy(Policy policy) {
        hashSet.add(policy);
        linkedHashSet.add(policy);
        treeSetByExpiry.add(policy);
    }

    public void displayAllPolicies() {
        System.out.println("HashSet (no order): " + hashSet);
        System.out.println("LinkedHashSet (insertion order): " + linkedHashSet);
        System.out.println("TreeSet (sorted by expiry): " + treeSetByExpiry);
    }

    public Set<Policy> getPoliciesExpiringSoon() {
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plusDays(30);

        Set<Policy> expiringSoon = new HashSet<>();
        for (Policy policy : hashSet) {
            if (!policy.getExpiryDate().isBefore(today) &&
                    !policy.getExpiryDate().isAfter(thirtyDaysLater)) {
                expiringSoon.add(policy);
            }
        }
        return expiringSoon;
    }

    public Set<Policy> getPoliciesByCoverageType(String coverageType) {
        Set<Policy> result = new HashSet<>();
        for (Policy policy : hashSet) {
            if (policy.getCoverageType().equalsIgnoreCase(coverageType)) {
                result.add(policy);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        InsurancePolicy manager = new InsurancePolicy();

        Policy p1 = new Policy("P001", "John Doe", LocalDate.of(2024, 12, 31), "Health", 500.0);
        Policy p2 = new Policy("P002", "Jane Smith", LocalDate.of(2024, 6, 15), "Auto", 300.0);
        Policy p3 = new Policy("P003", "Bob Johnson", LocalDate.of(2024, 5, 20), "Home", 700.0);
        Policy p4 = new Policy("P004", "Alice Brown", LocalDate.of(2024, 7, 10), "Health", 450.0);

        manager.addPolicy(p1);
        manager.addPolicy(p2);
        manager.addPolicy(p3);
        manager.addPolicy(p4);

        System.out.println("=== All Policies ===");
        manager.displayAllPolicies();

        System.out.println("\n=== Policies Expiring Soon ===");
        System.out.println(manager.getPoliciesExpiringSoon());

        System.out.println("\n=== Health Policies ===");
        System.out.println(manager.getPoliciesByCoverageType("Health"));
    }
}