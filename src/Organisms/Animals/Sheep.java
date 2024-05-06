package Organisms.Animals;

import Organisms.Animal;
import Organisms.Enums.ActionResult;
import Organisms.Enums.CollisionResult;
import Organisms.Enums.OrganismType;
import Organisms.Organism;

import java.util.Vector;

public class Sheep extends Animal
{
    static int SHEEP_COUNTER = 0;

    public Sheep(int row, int column)
    {
        super(4, 4, "sheep", 's', row, column, "sheep.png", OrganismType.SHEEP);
        SHEEP_COUNTER += 1;
    }

    @Override
    public int get_organism_counter()
    {
        return SHEEP_COUNTER;
    }

    @Override
    public ActionResult action(char[][] grid_board)
    {
        return this.default_action_animal(grid_board);
    }

    @Override
    public CollisionResult collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {
        return this.default_animal_collision(grid_board, organisms, current_index);
    }
    @Override
    public void decrease_static_counter()
    {
        SHEEP_COUNTER -= 1;
    }
}