package Organisms;

import java.util.Vector;

public abstract class Plant extends Organism
{
    public Plant(int strength, int initiative, String name, char character, int row, int column, String image_name)
    {
        super(strength, initiative, name, character, row, column, image_name);
    }

    @Override
    public abstract void action(char[][] grid_board);
    @Override
    public abstract void collision(char[][] grid_board, Vector<Organism> organisms, int current_index);
}
