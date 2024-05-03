package Organisms.Animals;

import Organisms.Animal;
import Organisms.Organism;

import java.util.Vector;

public class Turtle extends Animal
{
    public Turtle(int row, int column)
    {
        super(2, 1, "turtle", 't', row, column, "turtle.png");
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