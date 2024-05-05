package Organisms.Animals;

import Organisms.Animal;
import Organisms.Enums.*;
import Organisms.Organism;

import java.util.Vector;

public class Antelope extends Animal
{
    static int ANTELOPE_COUNTER = 0;
    static double ANTELOPE_ESCAPE_PROBABILITY = 0.5;
    private char previous_char = 'e';

    public Antelope(int row, int column)
    {
        super(4, 4, "antelope", 'a', row, column, "antelope.png", OrganismType.ANTELOPE);
        ANTELOPE_COUNTER += 1;
    }

    @Override
    public int get_organism_counter()
    {
        return ANTELOPE_COUNTER;
    }
    @Override
    public void decrease_static_counter()
    {
        ANTELOPE_COUNTER -= 1;
    }

    @Override
    public ActionResult action(char[][] grid_board)
    {
        this.previous_row = this.row;
        this.previous_column = this.column;

        grid_board[this.row][this.column] = 'e';
        int height = grid_board.length;
        int width = grid_board[0].length;
        Direction direction = Direction.fromInteger(randomGenerator.nextInt(4) + 1);

        System.out.print(this.get_name() + " moves from (" + this.row + ", " + this.column + ") to: ");

        // Find some good direction where we can go
        while (true)
        {
            if (this.row > 0 && direction == Direction.TOP)
                break;
            else if (this.column < width - 1 && direction == Direction.RIGHT)
                break;
            else if (this.column > 0 && direction == Direction.LEFT)
                break;
            else if (this.row < height - 1 && direction == Direction.BOTTOM)
                break;
            direction = Direction.fromInteger(randomGenerator.nextInt(4) + 1);
        }
        int multiplier = randomGenerator.nextInt(2) + 1;

        // Update position based on direction
        switch (direction)
        {
            case TOP:
                this.row = Math.max(0, this.row - multiplier);
                break;
            case BOTTOM:
                this.row = Math.min(height - 1, this.row + multiplier);
                break;
            case LEFT:
                this.column = Math.max(0, this.column - multiplier);
                break;
            case RIGHT:
                this.column = Math.min(width - 1, this.column + multiplier);
                break;
        }
        System.out.println("("+ this.row + ", " + this.column + ")");
        this.previous_char = grid_board[this.row][this.column];
        grid_board[this.row][this.column] = this.get_character();
        return new ActionResult(ActionType.MOVE);
    }

    @Override
    public CollisionResult collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {
        CollisionResult result = this.default_animal_collision(grid_board, organisms, current_index);
        if (result.getType() == CollisionType.FIGHT)
        {
            double rand = Math.random();
            if (rand < ANTELOPE_ESCAPE_PROBABILITY)
            {
                grid_board[this.row][this.column] = this.previous_char;
                this.row = this.previous_row;
                this.column = this.previous_column;
                System.out.println(this.get_name() + " escapes from a fight, it goes back to (" + this.row + ", " + this.column + ")");
                grid_board[this.row][this.column] = this.get_character();
                return new CollisionResult(CollisionType.NONE);
            }
        }
        return result;
    }

}