package com.myfitnesstrack.myfitnesstrackapi.macronutrient;

public class MacronutrientSplit {
    private double proteins;
    private double carbohydrates;
    private double fats;
    private double proteinPercentage;
    private double carbohydratePercentage;
    private double fatPercentage;
    private double calories;
    private String goal;

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins() {
        this.proteins = (Math.round(this.getProteinPercentage() * this.getCalories()) / 4);
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates() {
        this.carbohydrates = Math.round((this.getCarbohydratePercentage() * this.getCalories()) / 4);
    }

    public double getFats() {
        return fats;
    }

    public void setFats() {
        this.fats = Math.round((this.getFatPercentage() * this.getCalories()) / 9);
    }

    public double getProteinPercentage() {
        return proteinPercentage/100;
    }

    public void setProteinPercentage(double proteinPercentage) {
        this.proteinPercentage = proteinPercentage;
    }

    public double getCarbohydratePercentage() {
        return carbohydratePercentage/100;
    }

    public void setCarbohydratePercentage(double carbohydratePercentage) {
        this.carbohydratePercentage = carbohydratePercentage;
    }

    public double getFatPercentage() {
        return fatPercentage/100;
    }

    public void setFatPercentage(double fatPercentage) {
        this.fatPercentage = fatPercentage;
    }
}
