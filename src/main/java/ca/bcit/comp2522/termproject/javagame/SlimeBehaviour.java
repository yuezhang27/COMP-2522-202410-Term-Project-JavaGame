package ca.bcit.comp2522.termproject.javagame;

/**
 * Interface representing behaviors of a slime.
 *
 * @author Caroline Su GitHub:Juntingg
 * @author Kim Zhang GitHub:yuezhang27
 * @version 2024
 */
public interface SlimeBehaviour {

    /**
     * Splits the given slime in the specified petri dish.
     *
     * @param petriDish the petri dish in which the slime exists
     * @param slime the slime to split
     */
    void split(PetriDish petriDish, Slime slime);

    /**
     * Mutates the slime with the specified coordinates in the given petri dish.
     *
     * @param x the x-coordinate for the mutation
     * @param y the y-coordinate for the mutation
     * @param petriDish the petri dish in which the slime exists
     * @return the mutated slime
     */
    Slime mutate(double x, double y, PetriDish petriDish);
}
