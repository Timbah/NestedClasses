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
        burger = new Burger("regular");
        drink = new Item("coke", "drink", 1.5);
        System.out.println(drink.name);
        side = new Item("fries", "side", 2.0);
    }

    public void addToppings(String... toppings) {
        burger.addBurgerTopping(toppings);
    }

    public double getTotal() {

        double total = burger.getPrice()+ drink.price + side.price;
        return Item.getPrice(total, conversionRate);
    }

    @Override
    public String toString() {
        return "%s%n%s%n%s%n%26sR%.2f".formatted(burger, drink, side, "Total Due:", getTotal());
    }

    private class Burger extends Item {

        private enum Extra {

            AVOCADO, BACON, CHEESE, KETCHUP, MAYO, MUSTARD, PICKLES;

            private double getPrice() {
                return switch (this) {
                    case AVOCADO -> 1.0;
                    case BACON, CHEESE -> 1.5;
                    default -> 0;
                };
            }
        }

        private List<Item> BurgertoppingsList = new ArrayList<>();

        Burger(String name) {
            super(name, "burger", 5);
        }

        private void addBurgerTopping(String... toppings) {

            for (String topping : toppings) {
                try {
                    Extra extra = Extra.valueOf(topping.toUpperCase());
                    BurgertoppingsList.add(new Item(extra.name(), "Topping", extra.getPrice()));
                } catch (IllegalArgumentException e) {
                    System.out.println("No topping found for "+topping);
                }
            }
        }

        public double getPrice() {

            double total = super.price;
            for (Item topping : BurgertoppingsList) {
                total += topping.price;
            }
            return total;
        }


        @Override
        public String toString() {

            System.out.printf("%10s %.2f%n", this.getClass().getSimpleName(), getPrice());
            StringBuilder sb = new StringBuilder();

            for (Item topping : BurgertoppingsList) {
                sb.append("\n");
                sb.append(topping);
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
