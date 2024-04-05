package ca.bcit.comp2522.termproject.javagame;

public interface SlimeBehaviour {

    void split(PetriDish petriDish, Slime slime);

    Slime mutate(double x, double y, PetriDish petriDish);
}
