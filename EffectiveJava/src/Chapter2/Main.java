package Chapter2;

import static Chapter2.NyPizza.Size.*;
import static Chapter2.Pizza.Topping.*;

public class Main {
    public static void main(String[] args) {
        item2();
    }

    public static void item2() {
        NutritionFacts1 nutritionFacts1 = new NutritionFacts1(240, 8, 100, 0, 35, 27);

        NutritionFacts2 nutritionFacts2 = new NutritionFacts2();
        nutritionFacts2.setServingSize(240);
        nutritionFacts2.setServings(8);
        nutritionFacts2.setCalories(100);
        nutritionFacts2.setSodium(35);
        nutritionFacts2.setCarbohydrate(27);

        NutritionFacts3 nutritionFacts3 = new NutritionFacts3.Builder(240, 8)
                .calories(100)
                .sodium(35)
                .carbohydrate(27).build();

        NyPizza pizza = new NyPizza.Builder(SMALL).addTopping(SAUSAGE).addTopping(ONION).build();
        Calzone calzone = new Calzone.Builder().addTopping(HAM).sauceInside().build();
    }
}