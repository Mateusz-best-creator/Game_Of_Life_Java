package Organisms.Plants;

import Organisms.Enums.*;
import Organisms.Organism;
import Organisms.Plant;

import java.util.Vector;

public class SosnowskyHogweed extends Plant
{
    static int SOSNOWSKY_HOGWEED_COUNTER = 0;

    public SosnowskyHogweed(int row, int column)
    {
        super(10, 0, "Sosnowsky_hogweed", 'O', row, column, "sosnowsky_hogweed.png", OrganismType.SOSNOWSKY_HOGWEED);
        SOSNOWSKY_HOGWEED_COUNTER += 1;
    }

    @Override
    public int get_organism_counter()
    {
        return SOSNOWSKY_HOGWEED_COUNTER;
    }

    // Kills every animal except cyber_sheep in it's neighbourhood
    @Override
    public ActionResult action(char[][] grid_board)
    {
        int grid_height = grid_board.length;
        int grid_width = grid_board[0].length;

        Vector<Pair<Integer, Integer>> coords_to_remove  = new Vector<>();
        for (int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <= 1; j++)
            {
                if (i == 0 && j == 0)
                    continue;
                int new_row = this.row + i;
                int new_column = this.column + j;

                // Indexes in bound and not cyber_sheep
                if (new_row >= 0 && new_row < grid_height
                    && new_column >= 0 && new_column < grid_width
                    && this.Sosnowsky_can_kill(grid_board[new_row][new_column]))
                {
                    coords_to_remove.add(new Pair<>(new_row, new_column));
                    System.out.println(this.get_name() + " at (" + this.row + ", " + this.column + ") kills " +
                            grid_board[new_row][new_column] + " at (" + new_row + ", " + new_column + ")");
                }
            }
        }
        if (!coords_to_remove.isEmpty())
        {
            return new ActionResult(ActionType.KILLING, coords_to_remove);
        }
        System.out.println(this.get_name() + " at (" + this.row + ", " + this.column + ") will not kill...");
        return new ActionResult(ActionType.STAY);
    }

    private boolean Sosnowsky_can_kill(char character)
    {
        return character == 'a' || character == 'f' || character == 'H' || character == 's' || character == 't' || character == 'w';
    }

    @Override
    public CollisionResult collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {
        int index = 0;
        for (Organism o : organisms)
        {
            if (o.get_row() == this.row && o.get_column() == this.column && index != current_index && o.get_type() != OrganismType.CYBER_SHEEP)
            {
                System.out.println(this.get_name() + " eat " + o.get_name() + " and " + o.get_name()
                        + " eat " + this.get_name() + " at (" + this.row + ", " + this.column + ")");
                int[] indexes = {current_index, index};
                return new CollisionResult(CollisionType.POISON_PLANT, indexes);
            }
            index += 1;
        }
        return new CollisionResult(CollisionType.NONE);
    }
    @Override
    public void decrease_static_counter()
    {
        SOSNOWSKY_HOGWEED_COUNTER -= 1;
    }
}