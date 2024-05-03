package Organisms.Animals;

import Organisms.Animal;
import Organisms.Organism;

import java.util.Vector;

public class Fox extends Animal
{
    public Fox(int row, int column)
    {
        super(3, 7, "fox", 'f', row, column, "fox.png");
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