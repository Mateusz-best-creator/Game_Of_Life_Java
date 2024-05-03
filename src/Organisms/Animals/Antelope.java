package Organisms.Animals;

import Organisms.Animal;
import Organisms.Organism;

import java.util.Vector;

public class Antelope extends Animal
{
    public Antelope(int row, int column)
    {
        super(4, 4, "antelope", 'a', row, column, "antelope.png");
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