package Organisms.Plants;

import Organisms.Enums.OrganismType;
import Organisms.Organism;
import Organisms.Plant;

import java.util.Vector;

public class SosnowskyHogweed extends Plant
{
    public SosnowskyHogweed(int row, int column)
    {
        super(10, 0, "Sosnowsky_hogweed", 'O', row, column, "sosnowsky_hogweed.png", OrganismType.SOSNOWSKY_HOGWEED);
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