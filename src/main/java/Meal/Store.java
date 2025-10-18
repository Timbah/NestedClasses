package Meal;

public class Store {

    public static void main(String[] args) {
        Meal regularMeal = new Meal();
        System.out.println(regularMeal);

        Meal USRegularMeal = new Meal(17.56);
        System.out.println(USRegularMeal);
    }
}
