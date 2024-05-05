package Organisms.Plants;

import Organisms.Enums.CollisionResult;
import Organisms.Enums.CollisionType;
import Organisms.Enums.OrganismType;
import Organisms.Organism;
import Organisms.Plant;

import java.util.Vector;

public class SowThistle extends Plant
{
    static int SOW_THISTLE_COUNTER = 0;

    public SowThistle(int row, int column)
    {
        super(0, 0, "Sow_thistle", 'S', row, column, "sow_thistle.png", OrganismType.SOW_THISTLE);
        SOW_THISTLE_COUNTER += 1;
    }

    @Override
    public int get_organism_counter()
    {
        return SOW_THISTLE_COUNTER;
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
}