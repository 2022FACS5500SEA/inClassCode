// Builder Pattern
// credit to Joshua Bloch, "Effective Java", Chapter 2
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        // Required parameters
        private final int servingSize;
        private final int servings;

        // Optional parameters - initialized to default values
        private int calories      = 0;
        private int fat           = 0;
        private int carbohydrate  = 0;
        private int sodium        = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings    = servings;
        }
        public Builder calories(int val) {
            calories = val;
            return this;
        }
        public Builder fat(int val) {
            fat = val;
            return this;
        }
        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }
        public Builder sodium(int val) {
            sodium = val;
            return this;
        }
        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        this.servingSize  = builder.servingSize;
        this.servings     = builder.servings;
        this.calories     = builder.calories;
        this.fat          = builder.fat;
        this.sodium       = builder.sodium;
        this.carbohydrate = builder.carbohydrate;
    }

    public static void main(String[] args) {
        NutritionFacts cocaCola = new Builder(240, 8).calories(100).sodium(35).carbohydrate(27).build();

        System.out.println("CocaCola:" + cocaCola);

        // Allows to build multiple objects
        Builder builder = new Builder(240, 8).calories(100).sodium(35).carbohydrate(27);
        NutritionFacts food1 = builder.build();
        NutritionFacts food2 = builder.build();

        System.out.println("Food1: " + food1);
        System.out.println("Food1Calories: " + food1.calories);
        System.out.println("Food2: " + food2);
        System.out.println("Food2Calories: " + food2.calories);
    }
}