import java.util.*;

public class VotingSystem {
    private Map<String, Integer> votes;
    private Map<String, Integer> sortedVotes;
    private Map<String, Integer> insertionOrderVotes;

    public VotingSystem() {
        votes = new HashMap<>();
        sortedVotes = new TreeMap<>();
        insertionOrderVotes = new LinkedHashMap<>();
    }

    public void vote(String candidate) {
        votes.put(candidate, votes.getOrDefault(candidate, 0) + 1);
        sortedVotes.put(candidate, sortedVotes.getOrDefault(candidate, 0) + 1);
        insertionOrderVotes.put(candidate, insertionOrderVotes.getOrDefault(candidate, 0) + 1);
    }

    public void displayResults() {
        System.out.println("Votes (HashMap - no order): " + votes);
        System.out.println("Votes (TreeMap - sorted): " + sortedVotes);
        System.out.println("Votes (LinkedHashMap - insertion order): " + insertionOrderVotes);

        System.out.println("\nWinner: " + getWinner());
    }

    public String getWinner() {
        return votes.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No votes");
    }

    public static void main(String[] args) {
        VotingSystem system = new VotingSystem();

        system.vote("Alice");
        system.vote("Bob");
        system.vote("Alice");
        system.vote("Charlie");
        system.vote("Bob");
        system.vote("Alice");
        system.vote("David");

        system.displayResults();
    }
}