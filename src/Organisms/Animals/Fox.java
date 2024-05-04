package Organisms.Animals;

import Organisms.Animal;
import Organisms.Enums.Direction;
import Organisms.Enums.OrganismType;
import Organisms.Organism;

import java.util.Vector;

public class Fox extends Animal
{
    public Fox(int row, int column)
    {
        super(3, 7, "fox", 'f', row, column, "fox.png", OrganismType.FOX);
    }

    @Override
    public void action(char[][] grid_board)
    {
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
    public void collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {

    }

    private boolean fox_can_go(char character)
    {
        return character == 't' || character == 'G' || character == 'U' || character == 'S' || character == 'e' || character == 'f';
    }
}

