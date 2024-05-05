package Organisms.Animals;

import Organisms.Animal;
import Organisms.Enums.CollisionResult;
import Organisms.Enums.CollisionType;
import Organisms.Enums.OrganismType;
import Organisms.Organism;

import java.util.Vector;

public class Turtle extends Animal
{
    static int TURTLE_COUNTER = 0;

    public Turtle(int row, int column)
    {
        super(2, 1, "turtle", 't', row, column, "turtle.png", OrganismType.TURTLE);
        TURTLE_COUNTER += 1;
    }

    @Override
    public int get_organism_counter()
    {
        return TURTLE_COUNTER;
    }

    @Override
    public void action(char[][] grid_board)
    {
        int value = randomGenerator.nextInt(4) + 1;
        // Turtle has 75% chance to stay at current position
        if (value == 4)
        {
            this.previous_row = this.row;
            this.previous_column = this.column;
            this.default_action_animal(grid_board);
        }
        else
        {
            System.out.println(this.get_name() + " stays at current position: (" + this.row + ", " + this.column + ")");
        }
    }

    @Override
    public CollisionResult collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {
        return new CollisionResult(CollisionType.NONE, -1, -1);
    }
}