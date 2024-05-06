package Organisms.Animals;

import Organisms.Animal;
import Organisms.Enums.*;
import Organisms.Organism;

import java.util.Vector;

public class CyberSheep extends Animal
{
    static int CYBER_SHEEP_COUNTER = 0;

    public CyberSheep(int row, int column)
    {
        super(11, 4, "cyber_sheep", 'c', row, column, "cyber_sheep.png", OrganismType.CYBER_SHEEP);
        CYBER_SHEEP_COUNTER += 1;
    }

    @Override
    public int get_organism_counter()
    {
        return CYBER_SHEEP_COUNTER;
    }
    @Override
    public void decrease_static_counter()
    {
        CYBER_SHEEP_COUNTER -= 1;
    }

    @Override
    public ActionResult action(char[][] grid_board)
    {
        this.previous_row = this.row;
        this.previous_column = this.column;

        double min_distance = -1;
        int sosnowsky_row = -1, sosnowsky_col = -1;

        for (int i = 0; i < grid_board.length; i++)
        {
            for (int j = 0; j < grid_board[0].length; j++)
            {
                if (grid_board[i][j] == 'O')
                {
                    int row_distance = this.row - i;
                    int column_distance = this.column - j;
                    double distance = Math.pow(row_distance, 2) + Math.pow(column_distance, 2);
                    if (distance < min_distance || min_distance == -1)
                    {
                        min_distance = distance;
                        sosnowsky_row = i;
                        sosnowsky_col = j;
                    }
                }
            }
        }

        if (sosnowsky_row == -1)
        {
            System.out.println("No sosnowsky hogweed on the board, cyber_sheep behaves like normal sheep...");
            this.default_action_animal(grid_board);
            return new ActionResult(ActionType.MOVE);
        }

        if (sosnowsky_row < this.row)
        {
            this.organism_move_top();
        }
        else if (sosnowsky_row > this.row && this.row < grid_board.length - 1)
        {
            this.organism_move_bottom();
        }
        else if (sosnowsky_col < this.column)
        {
            this.organism_move_left();
        }
        else if (sosnowsky_col > this.column && this.column < grid_board[0].length - 1)
        {
            this.organism_move_right();
        }
//        double distance_to_sosnowsky = Math.pow(Math.pow(this.row - sosnowsky_row, 2) + Math.pow(this.column - sosnowsky_col, 2), 0.5);
        grid_board[this.row][this.column] = this.get_character();
        return new ActionResult(ActionType.MOVE);
    }

    @Override
    public CollisionResult collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {
        return this.default_animal_collision(grid_board, organisms, current_index);
    }
}