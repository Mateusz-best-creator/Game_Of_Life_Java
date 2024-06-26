package Organisms.Plants;

import Organisms.Enums.ActionResult;
import Organisms.Enums.CollisionResult;
import Organisms.Enums.OrganismType;
import Organisms.Organism;
import Organisms.Plant;

import java.util.Vector;

public class Grass extends Plant
{
    static int GRASS_COUNTER = 0;

    public Grass(int row, int column)
    {
        super(0, 0, "Grass", 'G', row, column, "grass.png", OrganismType.GRASS);
        GRASS_COUNTER += 1;
    }

    @Override
    public int get_organism_counter()
    {
        return GRASS_COUNTER;
    }

    @Override
    public ActionResult action(char[][] grid_board)
    {
        return this.default_plant_action();
    }

    @Override
    public CollisionResult collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {
        return this.default_plant_collision();
    }
    @Override
    public void decrease_static_counter()
    {
        GRASS_COUNTER -= 1;
    }
}