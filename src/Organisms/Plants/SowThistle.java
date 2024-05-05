package Organisms.Plants;

import Organisms.Enums.*;
import Organisms.Organism;
import Organisms.Plant;

import javax.swing.*;
import java.util.Vector;

public class SowThistle extends Plant
{
    static int SOW_THISTLE_COUNTER = 0;
    static int SOW_THISTLE_ATTEMPTS = 3;

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
    public ActionResult action(char[][] grid_board)
    {
        ActionResult r = new ActionResult(ActionType.STAY);
        for (int i = 0; i < SOW_THISTLE_ATTEMPTS; i++)
        {
            r = this.default_plant_action();
            if (r.get_type() == ActionType.SOW)
                break;
        }
        return r;
    }

    @Override
    public CollisionResult collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {
        return this.default_plant_collision();
    }
    @Override
    public void decrease_static_counter()
    {
        SOW_THISTLE_COUNTER -= 1;
    }
}