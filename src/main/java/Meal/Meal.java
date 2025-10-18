package Meal;

import java.util.ArrayList;
import java.util.List;

public class Meal {

    private double price = 5;

    private Burger burger;
    private Item drink;
    private Item side;

    private double conversionRate = 0.68;

    public Meal() {
        this(1);
    }

    public Meal(double conversionRate) {

        this.conversionRate = conversionRate;
        burger = new Burger("regular", "burger");
        drink = new Item("coke", "drink", 1.5);
//        System.out.println(drink.name);
        side = new Item("fries", "side", 2.0);
    }

    public void addTopping(String... toppings) {
        burger.addBurgerTopping(toppings);
    }

    public double getTotal() {

        double total = burger.getBurgerPrice() + drink.price + side.price;
        return Item.getPrice(total, conversionRate);
    }

    @Override
    public String toString() {
        return "%s%n%s%n%s%n%26sR%.2f".formatted(burger, drink, side, "Total Due:", getTotal());
    }

    private class Burger extends Item {

        private List<Item> BurgertoppingsList;

        public Burger(String name, String type) {
            this(name, type, 8);
        }

        public Burger(String name, String type, double price) {
            super(name, type, price);
            BurgertoppingsList = new ArrayList<>();
        }

        private void addBurgerTopping(String... toppings) {
            String[] toppingsList = toppings;

            for (String topping : toppingsList) {
                double toppingPrice = switch (topping.toLowerCase()) {
                    case "cheese" -> 1.0;
                    case "bacon" -> 1.5;
                    case "lettuce" -> 0.5;
                    default -> 0.0;
                };
                BurgertoppingsList.add(new Item(topping, "Topping", toppingPrice));
            }
        }

        private double getBurgerPrice() {

            double total = this.getPrice();
            for (Item topping : BurgertoppingsList) {
                total += topping.getPrice();
            }
            return total;
        }

        @Override
        public String toString() {

            System.out.printf("%10s %.2f%n", this.getClass().getSimpleName(), getBurgerPrice());
            StringBuilder sb = new StringBuilder();
            for (Item topping : BurgertoppingsList) {
                sb.append(topping.toString());
            }

            return sb.toString();
        }
    }

    private class Item {

        private String name;
        private String type;
        private double price;

        public Item(String name, String type) {
            this(name, type, type.equals("burger") ? Meal.this.price : 0);
        }

        public Item(String name, String type, double price) {
            this.name = name;
            this.type = type;
            this.price = price;
        }

        @Override
        public String toString() {
            return "%10s%15s R%.2f".formatted(type, name, getPrice(price, conversionRate));
        }

        private static double getPrice(double price, double rate) {
            return price * rate;
        }

        double getPrice() {
            return this.price;
        }
    }


}
