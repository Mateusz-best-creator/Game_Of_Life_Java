package Organisms.Plants;

import Organisms.Enums.OrganismType;
import Organisms.Organism;
import Organisms.Plant;

import java.util.Vector;

public class Belladonna extends Plant
{
    public Belladonna(int row, int column)
    {
        super(99, 0, "Belladonna", 'B', row, column, "belladonna.png", OrganismType.BELLADONNA);
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