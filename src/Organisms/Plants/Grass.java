package Organisms.Plants;

import Organisms.Enums.OrganismType;
import Organisms.Organism;
import Organisms.Plant;

import java.util.Vector;

public class Grass extends Plant
{
    public Grass(int row, int column)
    {
        super(0, 0, "Grass", 'G', row, column, "grass.png", OrganismType.GRASS);
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