package Organisms.Plants;

import Organisms.Enums.OrganismType;
import Organisms.Organism;
import Organisms.Plant;

import java.util.Vector;

public class SowThistle extends Plant
{
    public SowThistle(int row, int column)
    {
        super(0, 0, "Sow_thistle", 'S', row, column, "sow_thistle.png", OrganismType.SOW_THISTLE);
    }

    @Override
    public void action(char[][] grid_board)
    {

    }

    @Override
    public void collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {

    }
}