import java.util.*;

public class SetOperations {
    public static <T> boolean areSetsEqual(Set<T> set1, Set<T> set2) {
        return set1.equals(set2);
    }

    public static <T> Set<T> union(Set<T> set1, Set<T> set2) {
        Set<T> unionSet = new HashSet<>(set1);
        unionSet.addAll(set2);
        return unionSet;
    }

    public static <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> intersectionSet = new HashSet<>(set1);
        intersectionSet.retainAll(set2);
        return intersectionSet;
    }

    public static <T> Set<T> symmetricDifference(Set<T> set1, Set<T> set2) {
        Set<T> union = union(set1, set2);
        Set<T> intersection = intersection(set1, set2);
        union.removeAll(intersection);
        return union;
    }

    public static <T> boolean isSubset(Set<T> subset, Set<T> superset) {
        return superset.containsAll(subset);
    }

    public static List<Integer> setToSortedList(Set<Integer> set) {
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        return list;
    }

    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(3, 2, 1));
        Set<Integer> set3 = new HashSet<>(Arrays.asList(3, 4, 5));
        Set<Integer> set4 = new HashSet<>(Arrays.asList(2, 3));

        System.out.println("Set1 equals Set2: " + areSetsEqual(set1, set2));
        System.out.println("Union of Set1 and Set3: " + union(set1, set3));
        System.out.println("Intersection of Set1 and Set3: " + intersection(set1, set3));
        System.out.println("Symmetric difference of Set1 and Set3: " + symmetricDifference(set1, set3));
        System.out.println("Set4 is subset of Set1: " + isSubset(set4, set1));

        Set<Integer> hashSet = new HashSet<>(Arrays.asList(5, 3, 9, 1));
        System.out.println("Sorted list from set: " + setToSortedList(hashSet));
    }
}