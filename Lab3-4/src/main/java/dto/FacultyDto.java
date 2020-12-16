package dto;

public class FacultyDto {
    private String name;
    private int budgetPlacesAmount;
    private int placesAmount;


    public FacultyDto(String name, int budgetPlacesAmount, int placesAmount){
        this.name = name;
        this.budgetPlacesAmount = budgetPlacesAmount;
        this.placesAmount = placesAmount;
    }

    public FacultyDto(String name) {
        this.name = name;
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
