package Organisms.Plants;

import Organisms.Enums.*;
import Organisms.Organism;
import Organisms.Plant;

import java.util.Vector;

public class Belladonna extends Plant
{
    static int BELLADONNA_COUNTER = 0;

    public Belladonna(int row, int column)
    {
        super(99, 0, "Belladonna", 'B', row, column, "belladonna.png", OrganismType.BELLADONNA);
        BELLADONNA_COUNTER += 1;
    }

    @Override
    public int get_organism_counter()
    {
        return BELLADONNA_COUNTER;
    }

    @Override
    public ActionResult action(char[][] grid_board)
    {
        return this.default_plant_action();
    }

    @Override
    public CollisionResult collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {
        int index = 0;
        for (Organism o : organisms)
        {
            if (o.get_row() == this.row && o.get_column() == this.column && index != current_index)
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
        BELLADONNA_COUNTER -= 1;
    }
}