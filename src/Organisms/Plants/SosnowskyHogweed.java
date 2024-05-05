package Organisms.Plants;

import Organisms.Enums.CollisionResult;
import Organisms.Enums.CollisionType;
import Organisms.Enums.OrganismType;
import Organisms.Organism;
import Organisms.Plant;

import java.util.Vector;

public class SosnowskyHogweed extends Plant
{
    static int SOSNOWKY_HOGWEED_COUNTER = 0;

    public SosnowskyHogweed(int row, int column)
    {
        super(10, 0, "Sosnowsky_hogweed", 'O', row, column, "sosnowsky_hogweed.png", OrganismType.SOSNOWSKY_HOGWEED);
        SOSNOWKY_HOGWEED_COUNTER += 1;
    }

    @Override
    public int get_organism_counter()
    {
        return SOSNOWKY_HOGWEED_COUNTER;
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