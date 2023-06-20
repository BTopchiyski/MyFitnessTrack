package com.myfitnesstrack.myfitnesstrackapi.macronutrient;

public class MacronutrientSplit {
    private double proteins;
    private double carbohydrates;
    private double fats;
    private double proteinPercentage;
    private double carbohydratePercentage;
    private double fatPercentage;
    private double calories;

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
        this.proteins = (this.getProteinPercentage() * this.getCalories()) / 4;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates() {
        this.carbohydrates = (this.getProteinPercentage() * this.getCalories()) / 4;
    }

    public double getFats() {
        return fats;
    }

    public void setFats() {
        this.fats = (this.getProteinPercentage() * this.getCalories()) / 9;
    }

    public double getProteinPercentage() {
        return proteinPercentage;
    }

    public void setProteinPercentage(double proteinPercentage) {
        this.proteinPercentage = proteinPercentage;
    }

    public double getCarbohydratePercentage() {
        return carbohydratePercentage;
    }

    public void setCarbohydratePercentage(double carbohydratePercentage) {
        this.carbohydratePercentage = carbohydratePercentage;
    }

    public double getFatPercentage() {
        return fatPercentage;
    }

    public void setFatPercentage(double fatPercentage) {
        this.fatPercentage = fatPercentage;
    }
}
