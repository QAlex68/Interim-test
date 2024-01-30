package hw02;

class Toy {
    private int id;
    private String name;
    private int quantity;
    private double weight; // Частота выпадения в %

    public Toy(int id, String name, int quantity, int weight) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) { this.weight = weight;  }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
