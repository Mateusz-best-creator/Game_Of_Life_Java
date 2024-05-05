package Organisms.Plants;

import Organisms.Enums.ActionResult;
import Organisms.Enums.CollisionResult;
import Organisms.Enums.CollisionType;
import Organisms.Enums.OrganismType;
import Organisms.Organism;
import Organisms.Plant;

import java.util.Vector;

public class Guarana extends Plant
{
    static int GUARANA_COUNTER = 0;

    public Guarana(int row, int column)
    {
        super(0, 0, "Guarana", 'U', row, column, "guarana.png", OrganismType.GUARANA);
        GUARANA_COUNTER += 1;
    }

    @Override
    public int get_organism_counter()
    {
        return GUARANA_COUNTER;
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
        GUARANA_COUNTER -= 1;
    }
}