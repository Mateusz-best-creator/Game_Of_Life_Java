package Organisms;

import Organisms.Enums.*;

import javax.swing.*;
import java.util.Vector;

public abstract class Plant extends Organism
{
    protected static double probability = 0.1;

    public Plant(int strength, int initiative, String name, char character, int row, int column, String image_name, OrganismType type)
    {
        super(strength, initiative, name, character, row, column, image_name, type);
    }

    @Override
    public abstract ActionResult action(char[][] grid_board);
    @Override
    public abstract CollisionResult collision(char[][] grid_board, Vector<Organism> organisms, int current_index);
    @Override
    public abstract int get_organism_counter();
    @Override
    public abstract void decrease_static_counter();

    public ActionResult default_plant_action()
    {
        double rand = Math.random();
        if (rand >= probability || this.get_organism_counter() > MAX_ANIMAL_AMOUNT)
        {
            return new ActionResult(ActionType.STAY);
        }
        else
        {
            return new ActionResult(ActionType.SOW, this.row, this.column);
        }
    }
}
