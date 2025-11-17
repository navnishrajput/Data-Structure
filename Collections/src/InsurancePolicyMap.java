import java.util.*;
import java.time.LocalDate;

class Policy {
    String policyNumber;
    String policyholderName;
    LocalDate expiryDate;
    String coverageType;
    double premiumAmount;

    public Policy(String policyNumber, String policyholderName, LocalDate expiryDate,
                  String coverageType, double premiumAmount) {
        this.policyNumber = policyNumber;
        this.policyholderName = policyholderName;
        this.expiryDate = expiryDate;
        this.coverageType = coverageType;
        this.premiumAmount = premiumAmount;
    }

    @Override
    public String toString() {
        return String.format("%s: %s (%s, $%.2f)", policyNumber, policyholderName, coverageType, premiumAmount);
    }
}

public class InsurancePolicyMap {
    private Map<String, Policy> hashMap;
    private Map<String, Policy> linkedHashMap;
    private Map<String, Policy> treeMap;

    public InsurancePolicyMap() {
        hashMap = new HashMap<>();
        linkedHashMap = new LinkedHashMap<>();
        treeMap = new TreeMap<>();
    }

    public void addPolicy(Policy policy) {
        hashMap.put(policy.policyNumber, policy);
        linkedHashMap.put(policy.policyNumber, policy);
        treeMap.put(policy.policyNumber, policy);
    }

    public Policy getPolicy(String policyNumber) {
        return hashMap.get(policyNumber);
    }

    public List<Policy> getPoliciesExpiringSoon() {
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plusDays(30);

        List<Policy> result = new ArrayList<>();
        for (Policy policy : hashMap.values()) {
            if (!policy.expiryDate.isBefore(today) && !policy.expiryDate.isAfter(thirtyDaysLater)) {
                result.add(policy);
            }
        }
        return result;
    }

    public List<Policy> getPoliciesByHolder(String holderName) {
        List<Policy> result = new ArrayList<>();
        for (Policy policy : hashMap.values()) {
            if (policy.policyholderName.equalsIgnoreCase(holderName)) {
                result.add(policy);
            }
        }
        return result;
    }

    public void removeExpiredPolicies() {
        LocalDate today = LocalDate.now();
        hashMap.entrySet().removeIf(entry -> entry.getValue().expiryDate.isBefore(today));
        linkedHashMap.entrySet().removeIf(entry -> entry.getValue().expiryDate.isBefore(today));
        treeMap.entrySet().removeIf(entry -> entry.getValue().expiryDate.isBefore(today));
    }

    public void displayAllPolicies() {
        System.out.println("HashMap: " + hashMap.values());
        System.out.println("LinkedHashMap: " + linkedHashMap.values());
        System.out.println("TreeMap: " + treeMap.values());
    }

    public static void main(String[] args) {
        InsurancePolicyMap manager = new InsurancePolicyMap();

        manager.addPolicy(new Policy("P001", "John Doe", LocalDate.of(2024, 12, 31), "Health", 500.0));
        manager.addPolicy(new Policy("P002", "Jane Smith", LocalDate.of(2024, 6, 15), "Auto", 300.0));
        manager.addPolicy(new Policy("P003", "John Doe", LocalDate.of(2023, 12, 31), "Home", 700.0));

        System.out.println("Policy P001: " + manager.getPolicy("P001"));
        System.out.println("Policies for John Doe: " + manager.getPoliciesByHolder("John Doe"));
        System.out.println("Policies expiring soon: " + manager.getPoliciesExpiringSoon());

        System.out.println("\nBefore removing expired:");
        manager.displayAllPolicies();

        manager.removeExpiredPolicies();
        System.out.println("\nAfter removing expired:");
        manager.displayAllPolicies();
    }
}