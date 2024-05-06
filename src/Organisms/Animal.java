package Organisms;

import Organisms.Enums.*;

import java.util.Objects;
import java.util.Vector;

public abstract class Animal extends Organism
{
    static final int MIN_STRENGTH_TURTLE_REFLECT = 4;

    public Animal(int strength, int initiative, String name, char character, int row, int column, String image_name, OrganismType type)
    {
        super(strength, initiative, name, character, row, column, image_name, type);
    }

    @Override
    public abstract ActionResult action(char[][] grid_board);
    @Override
    public abstract CollisionResult collision(char[][] grid_board, Vector<Organism> organisms, int current_index);
    @Override
    public abstract int get_organism_counter();
    @Override
    public abstract void decrease_static_counter();

    protected ActionResult default_action_animal(char[][] grid_board)
    {
        this.previous_row = this.row;
        this.previous_column = this.column;

        grid_board[this.row][this.column] = 'e';
        int board_height = grid_board.length;
        int board_width = grid_board[0].length;

        Direction direction = Direction.fromInteger(randomGenerator.nextInt(Direction.values().length) + 1);
        while (true)
        {
            if ((this.row == 0 && direction == Direction.TOP)
            || (this.row == board_height - 1 && direction == Direction.BOTTOM)
            || (this.column == 0 && direction == Direction.LEFT)
            || (this.column == board_width - 1 && direction == Direction.RIGHT))
            {
                direction = Direction.fromInteger(randomGenerator.nextInt(Direction.values().length) + 1);
            }
            else
            {
                break;
            }
        }
        switch (direction)
        {
            case LEFT:
                this.organism_move_left();
                break;
            case TOP:
                this.organism_move_top();
                break;
            case RIGHT:
                this.organism_move_right();
                break;
            case BOTTOM:
                this.organism_move_bottom();
                break;
            default:
                System.out.println("Invalid direction at default_animal_action(...)\n");
                break;
        }
        assert(this.row > 0 && this.row < board_height && this.column > 0 && this.column < board_width);
        grid_board[this.row][this.column] = this.get_character();
        return new ActionResult(ActionType.MOVE);
    }

    protected CollisionResult default_animal_collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {
        int index = 0;
        for (Organism organism : organisms)
        {
            if (organism.row == this.row && organism.column == this.column && index != current_index)
            {
                // Multiplication case
                if (this.get_type() == organism.get_type())
                {
                    System.out.println(this.get_name() + " multiplication at: (" + this.get_row() + ", " + this.get_column() + ")");
                    int current_row = this.row;
                    int current_col = this.column;
                    this.row = this.previous_row;
                    this.column = this.previous_column;
                    System.out.println(this.get_type() + " is going back to: (" + this.row + ", " + this.column + ")");
                    grid_board[this.row][this.column] = this.get_character();

                    if (this.get_organism_counter() > MAX_ANIMAL_AMOUNT)
                    {
                        System.out.println("Cannot create more "+ this.get_name() + " we already have " + this.get_organism_counter() + " of it...");
                        return new CollisionResult(CollisionType.NONE);
                    }
                    return new CollisionResult(CollisionType.Multiplication, current_row, current_col);
                }
                // Turtle reflection case
                else if (Objects.equals(organism.get_name(), "turtle") && this.get_strength() <= MIN_STRENGTH_TURTLE_REFLECT)
                {
                    System.out.println("Turtle reflects the attack from " + this.get_name() + " at: (" + this.get_row() + ", " + this.get_column() + ")");
                    this.row = this.previous_row;
                    this.column = this.previous_column;
                    grid_board[this.row][this.column] = this.get_character();
                    return new CollisionResult(CollisionType.NONE);
                }
                // Fight case
                else
                {
                    if (this.get_strength() >= organism.get_strength())
                    {
                        System.out.println(this.get_name()  + " vs + " + organism.get_name() + " at: (" + this.get_row() + ", " +
                                this.get_column() + ") -> " + this.get_name() + " wins...");
                        grid_board[this.row][this.column] = this.get_character();
                        return new CollisionResult(CollisionType.FIGHT, this.row, this.column, index);
                    }
                    else
                    {
                        System.out.println(this.get_name()  + " vs " + organism.get_name() + " at: (" + this.get_row() + ", " +
                                this.get_column() + ") -> " + organism.get_name() + " wins...");
                        grid_board[this.row][this.column] = organism.get_character();
                        return new CollisionResult(CollisionType.FIGHT, this.row, this.column, current_index);
                    }
                }
            }
            index += 1;
        }
        return new CollisionResult(CollisionType.NONE);
    }
}