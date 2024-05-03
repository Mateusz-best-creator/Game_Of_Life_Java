package Organisms.Plants;

import Organisms.Organism;
import Organisms.Plant;

import java.util.Vector;

public class Guarana extends Plant
{
    public Guarana(int row, int column)
    {
        super(0, 0, "Guarana", 'U', row, column, "guarana.png");
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