package Organisms.Animals;

import Organisms.Animal;
import Organisms.Enums.OrganismType;
import Organisms.Organism;

import java.util.Vector;

public class CyberSheep extends Animal
{
    private double distance_to_sosnowsky;

    public CyberSheep(int row, int column)
    {
        super(11, 4, "cyber_sheep", 'c', row, column, "cyber_sheep.png", OrganismType.CYBER_SHEEP);
    }

    @Override
    public void action(char[][] grid_board)
    {
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
            return;
        }

        if (sosnowsky_row < this.row && this.row > 0)
        {
            this.organism_move_top();
        }
        else if (sosnowsky_row > this.row && this.row < grid_board.length - 1)
        {
            this.organisms_move_bottom();
        }
        else if (sosnowsky_col < this.column && this.column > 0)
        {
            this.organism_move_left();
        }
        else if (sosnowsky_col > this.column && this.column < grid_board[0].length - 1)
        {
            this.organism_move_right();
        }
        this.distance_to_sosnowsky = Math.pow(Math.pow(this.row - sosnowsky_row, 2) + Math.pow(this.column - sosnowsky_col, 2), 0.5);
    }

    @Override
    public void collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {

    }
}