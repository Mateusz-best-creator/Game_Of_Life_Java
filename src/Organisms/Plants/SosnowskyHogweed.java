package Organisms.Plants;

import Organisms.Organism;
import Organisms.Plant;

import java.util.Vector;

public class SosnowskyHogweed extends Plant
{
    public SosnowskyHogweed(int row, int column)
    {
        super(10, 0, "Sosnowsky_hogweed", 'U', row, column, "sosnowsky_hogweed.png");
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