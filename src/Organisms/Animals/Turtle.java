package Organisms.Animals;

import Organisms.Animal;
import Organisms.Enums.OrganismType;
import Organisms.Organism;

import java.util.Vector;

public class Turtle extends Animal
{
    public Turtle(int row, int column)
    {
        super(2, 1, "turtle", 't', row, column, "turtle.png", OrganismType.TURTLE);
    }

    @Override
    public void action(char[][] grid_board)
    {
        int value = randomGenerator.nextInt(4) + 1;
        // Turtle has 75% chance to stay at current position
        if (value == 4)
        {
            this.default_action_animal(grid_board);
        }
        else
        {
            System.out.println(this.get_name() + " stays at current position: (" + this.row + ", " + this.column + ")");
        }
    }

    @Override
    public void collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {

    }
}