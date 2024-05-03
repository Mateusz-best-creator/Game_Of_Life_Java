package Organisms.Animals;

import Organisms.Animal;
import Organisms.Organism;

import java.util.Vector;

public class Wolf extends Animal
{
    public Wolf(int row, int column)
    {
        super(9, 5, "wolf", 'w', row, column, "wolf.png");
    }

    @Override
    public void action(char[][] grid_board)
    {
        this.default_action_animal(grid_board);
    }

    @Override
    public void collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {

    }
}