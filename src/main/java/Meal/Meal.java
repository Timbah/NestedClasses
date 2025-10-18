package Meal;

public class Meal {

    private double base = 5;

    private Item burger;
    private Item drink;
    private Item side;

    public Meal() {

        burger = new Item("regular", "burger");
        drink = new Item("coke", "drink", 1.5);
        System.out.println(drink.name);
        side = new Item("fries", "side", 2.0);
    }

    @Override
    public String toString() {
        return "%s%n%s%n%s%n".formatted(burger, drink, side);
    }

    private class Item {

        private String name;
        private String type;
        private double price;

        public Item(String name, String type) {
            this(name, type, type.equals("burger") ? base : 0);
        }

        public Item(String name, String type, double price) {
            this.name = name;
            this.type = type;
            this.price = price;
        }

        @Override
        public String toString() {
            return "%10s%15s R%.2f".formatted(type, name, price);
        }
    }


}
