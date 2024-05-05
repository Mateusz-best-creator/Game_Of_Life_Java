package Organisms.Animals;

import Organisms.Animal;
import Organisms.Enums.ActionResult;
import Organisms.Enums.CollisionResult;
import Organisms.Enums.CollisionType;
import Organisms.Enums.OrganismType;
import Organisms.Organism;

import java.util.Vector;

public class Wolf extends Animal
{
    static int WOLF_COUNTER = 0;

    public Wolf(int row, int column)
    {
        super(9, 5, "wolf", 'w', row, column, "wolf.png", OrganismType.WOLF);
        WOLF_COUNTER += 1;
    }

    @Override
    public int get_organism_counter()
    {
        return WOLF_COUNTER;
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
        WOLF_COUNTER -= 1;
    }
}