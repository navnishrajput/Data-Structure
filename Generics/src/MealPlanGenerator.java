import java.util.*;

interface MealPlan {
    String getPlanName();
    List<String> getAllowedFoods();
    List<String> getRestrictedFoods();
    boolean isValidMeal(String meal);
}

class VegetarianMeal implements MealPlan {
    @Override
    public String getPlanName() {
        return "Vegetarian";
    }

    @Override
    public List<String> getAllowedFoods() {
        return Arrays.asList("Vegetables", "Fruits", "Grains", "Dairy", "Eggs");
    }

    @Override
    public List<String> getRestrictedFoods() {
        return Arrays.asList("Meat", "Poultry", "Fish", "Seafood");
    }

    @Override
    public boolean isValidMeal(String meal) {
        List<String> restricted = getRestrictedFoods();
        return restricted.stream().noneMatch(meal::contains);
    }
}

class VeganMeal implements MealPlan {
    @Override
    public String getPlanName() {
        return "Vegan";
    }

    @Override
    public List<String> getAllowedFoods() {
        return Arrays.asList("Vegetables", "Fruits", "Grains", "Legumes", "Nuts");
    }

    @Override
    public List<String> getRestrictedFoods() {
        return Arrays.asList("Meat", "Dairy", "Eggs", "Honey", "Animal Products");
    }

    @Override
    public boolean isValidMeal(String meal) {
        List<String> restricted = getRestrictedFoods();
        return restricted.stream().noneMatch(meal::contains);
    }
}

class KetoMeal implements MealPlan {
    @Override
    public String getPlanName() {
        return "Keto";
    }

    @Override
    public List<String> getAllowedFoods() {
        return Arrays.asList("Meat", "Fish", "Eggs", "Low-carb Vegetables", "Healthy Fats");
    }

    @Override
    public List<String> getRestrictedFoods() {
        return Arrays.asList("Grains", "Sugars", "Fruits", "Legumes", "Starchy Vegetables");
    }

    @Override
    public boolean isValidMeal(String meal) {
        List<String> restricted = getRestrictedFoods();
        return restricted.stream().noneMatch(meal::contains);
    }
}

class HighProteinMeal implements MealPlan {
    @Override
    public String getPlanName() {
        return "High-Protein";
    }

    @Override
    public List<String> getAllowedFoods() {
        return Arrays.asList("Lean Meat", "Fish", "Eggs", "Dairy", "Legumes", "Protein Supplements");
    }

    @Override
    public List<String> getRestrictedFoods() {
        return Arrays.asList("High-sugar Foods", "Refined Carbs", "Processed Foods");
    }

    @Override
    public boolean isValidMeal(String meal) {
        return meal.toLowerCase().contains("protein") ||
                Arrays.asList("chicken", "fish", "eggs", "tofu").stream().anyMatch(meal::toLowerCase::contains);
    }
}

class Meal<T extends MealPlan> {
    private String name;
    private String description;
    private T mealPlan;
    private List<String> ingredients;

    public Meal(String name, String description, T mealPlan, List<String> ingredients) {
        this.name = name;
        this.description = description;
        this.mealPlan = mealPlan;
        this.ingredients = new ArrayList<>(ingredients);

        if (!isValidMeal()) {
            throw new IllegalArgumentException("Meal '" + name + "' is not valid for " + mealPlan.getPlanName() + " plan");
        }
    }

    private boolean isValidMeal() {
        String mealDescription = name + " " + description + " " + String.join(" ", ingredients);
        return mealPlan.isValidMeal(mealDescription);
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public T getMealPlan() { return mealPlan; }
    public List<String> getIngredients() { return new ArrayList<>(ingredients); }

    @Override
    public String toString() {
        return String.format("Meal[%s: %s (%s Plan)]", name, description, mealPlan.getPlanName());
    }
}

class MealPlanGenerator {
    public static <T extends MealPlan> Meal<T> generatePersonalizedMeal(String name, String description, T mealPlan, List<String> ingredients) {
        try {
            Meal<T> meal = new Meal<>(name, description, mealPlan, ingredients);
            System.out.println("Successfully generated meal: " + meal);
            return meal;
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to generate meal: " + e.getMessage());
            return null;
        }
    }

    public static <T extends MealPlan> void validateMealPlan(List<Meal<T>> meals) {
        System.out.println("\nValidating meal plan:");
        for (Meal<T> meal : meals) {
            System.out.println("✓ " + meal.getName() + " is valid for " + meal.getMealPlan().getPlanName());
        }
    }
}

public class PersonalizedMealPlanGenerator {
    public static void main(String[] args) {
        VegetarianMeal vegetarian = new VegetarianMeal();
        VeganMeal vegan = new VeganMeal();
        KetoMeal keto = new KetoMeal();
        HighProteinMeal highProtein = new HighProteinMeal();

        System.out.println("Generating personalized meals:");

        Meal<VegetarianMeal> vegMeal = MealPlanGenerator.generatePersonalizedMeal(
                "Vegetable Stir Fry", "Fresh vegetables with tofu", vegetarian,
                Arrays.asList("Tofu", "Broccoli", "Carrots", "Bell Peppers", "Soy Sauce")
        );

        Meal<VeganMeal> veganMeal = MealPlanGenerator.generatePersonalizedMeal(
                "Vegan Buddha Bowl", "Nutritious plant-based bowl", vegan,
                Arrays.asList("Quinoa", "Chickpeas", "Avocado", "Kale", "Tahini")
        );

        Meal<KetoMeal> ketoMeal = MealPlanGenerator.generatePersonalizedMeal(
                "Keto Chicken", "Low-carb chicken dish", keto,
                Arrays.asList("Chicken Breast", "Butter", "Spinach", "Mushrooms", "Heavy Cream")
        );

        Meal<HighProteinMeal> proteinMeal = MealPlanGenerator.generatePersonalizedMeal(
                "Protein Shake", "High-protein recovery drink", highProtein,
                Arrays.asList("Whey Protein", "Almond Milk", "Banana", "Peanut Butter")
        );

        List<Meal<?>> validMeals = new ArrayList<>();
        if (vegMeal != null) validMeals.add(vegMeal);
        if (veganMeal != null) validMeals.add(vevanMeal);
        if (ketoMeal != null) validMeals.add(ketoMeal);
        if (proteinMeal != null) validMeals.add(proteinMeal);

        System.out.println("\nGenerated " + validMeals.size() + " valid meals");
    }
}