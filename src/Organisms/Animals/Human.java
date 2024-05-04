package Organisms.Animals;

import Organisms.Animal;
import Organisms.Enums.OrganismType;
import Organisms.Organism;

import java.util.Vector;

public class Human extends Animal
{
    public Human(int row, int column)
    {
        super(5, 4, "Human", 'H', row, column, "human.png", OrganismType.HUMAN);
    }

    @Override
    public void action(char[][] grid_board)
    {

    }

    @Override
    public void collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {

    }
}