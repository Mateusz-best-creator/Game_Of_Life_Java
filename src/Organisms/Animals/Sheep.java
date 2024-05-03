package Organisms.Animals;

import Organisms.Animal;
import Organisms.Organism;

import java.util.Vector;

public class Sheep extends Animal
{
    public Sheep(int row, int column)
    {
        super(4, 4, "sheep", 's', row, column, "sheep.png");
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