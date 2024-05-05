package Organisms.Animals;

import Organisms.Animal;
import Organisms.Enums.CollisionResult;
import Organisms.Enums.CollisionType;
import Organisms.Enums.Direction;
import Organisms.Enums.OrganismType;
import Organisms.Organism;

import java.util.Vector;

public class Fox extends Animal
{
    static int FOX_COUNTER = 0;

    public Fox(int row, int column)
    {
        super(3, 7, "fox", 'f', row, column, "fox.png", OrganismType.FOX);
        FOX_COUNTER += 1;
    }

    @Override
    public int get_organism_counter()
    {
        return FOX_COUNTER;
    }

    @Override
    public void action(char[][] grid_board)
    {
        this.previous_row = this.row;
        this.previous_column = this.column;

        boolean moved = false;
        int[] indexes = {1, 2, 3, 4};

        for (int index : indexes)
        {
            Direction dir = Direction.fromInteger(index);

            if (dir == Direction.LEFT && this.column > 0)
            {
                if (this.fox_can_go(grid_board[this.row][this.column - 1]))
                {
                    this.organism_move_left();
                    moved = true;
                }
            }
            else if (dir == Direction.RIGHT && this.column < grid_board[0].length - 1)
            {
                if (this.fox_can_go(grid_board[this.row][this.column + 1]))
                {
                    this.organism_move_right();
                    moved = true;
                }
            }
            else if (dir == Direction.TOP && this.row > 0)
            {
                if (this.fox_can_go(grid_board[this.row - 1][this.column]))
                {
                    this.organism_move_top();
                    moved = true;
                }
            }
            else if (dir == Direction.BOTTOM && this.row < grid_board.length - 1)
            {
                if (this.fox_can_go(grid_board[this.row + 1][this.column]))
                {
                    this.organisms_move_bottom();
                    moved = true;
                }
            }
            if (moved)
            {
                return;
            }
        }
        System.out.println(this.get_name() + " stays at current position: (" + this.row + ", " + this.column + ")");
    }

    @Override
    public CollisionResult collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {
        return this.default_animal_collision(grid_board, organisms, current_index);
    }

    private boolean fox_can_go(char character)
    {
        return character == 't' || character == 'G' || character == 'U' || character == 'S' || character == 'e' || character == 'f';
    }
}

