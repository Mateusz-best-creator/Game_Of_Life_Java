package Organisms;

import Organisms.Enums.Direction;

import java.util.Vector;

public abstract class Animal extends Organism
{
    public Animal(int strength, int initiative, String name, char character, int row, int column, String image_name)
    {
        super(strength, initiative, name, character, row, column, image_name);
    }

    @Override
    public abstract void action(char[][] grid_board);
    @Override
    public abstract void collision(char[][] grid_board, Vector<Organism> organisms, int current_index);

    protected void default_action_animal(char[][] grid_board)
    {
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
                this.organisms_move_bottom();
                break;
            default:
                System.out.println("Invalid direction at default_animal_action(...)\n");
                break;
        }
        assert(this.row > 0 && this.row < board_height && this.column > 0 && this.column < board_width);
        grid_board[this.row][this.column] = this.get_character();
    }

    protected void default_animal_collision()
    {

    }
}
