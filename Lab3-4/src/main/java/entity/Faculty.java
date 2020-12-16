package entity;

public class Faculty {
    private int id;
    private String name;
    private int budgetPlacesAmount;
    private int placesAmount;

    public Faculty(int id, String name, int budgetPlacesAmount, int placesAmount) {
        this.id = id;
        this.name = name;
        this.budgetPlacesAmount = budgetPlacesAmount;
        this.placesAmount = placesAmount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBudgetPlacesAmount() {
        return budgetPlacesAmount;
    }

    public int getPlacesAmount() {
        return placesAmount;
    }
}
