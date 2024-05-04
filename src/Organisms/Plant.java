package Organisms;

import Organisms.Enums.OrganismType;

import java.util.Vector;

public abstract class Plant extends Organism
{
    protected static double probability = 0.1;

    public Plant(int strength, int initiative, String name, char character, int row, int column, String image_name, OrganismType type)
    {
        super(strength, initiative, name, character, row, column, image_name, type);
    }

    @Override
    public abstract void action(char[][] grid_board);
    @Override
    public abstract void collision(char[][] grid_board, Vector<Organism> organisms, int current_index);

    public void default_plant_action()
    {
        int rand = randomGenerator.nextInt(10) + 1;
        if (rand < probability * 10)
        {

        }
        else
        {

        }
    }
}
