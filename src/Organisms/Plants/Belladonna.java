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
        return new CollisionResult(CollisionType.NONE, -1, -1);
    }
    @Override
    public void decrease_static_counter()
    {
        BELLADONNA_COUNTER -= 1;
    }
}