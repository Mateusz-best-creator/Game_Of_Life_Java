package Organisms.Plants;

import Organisms.Enums.CollisionResult;
import Organisms.Enums.CollisionType;
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
    public void action(char[][] grid_board)
    {

    }

    @Override
    public CollisionResult collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {

        return new CollisionResult(CollisionType.NONE, -1, -1);
    }
    @Override
    public void decrease_static_counter()
    {
        GRASS_COUNTER -= 1;
    }
}