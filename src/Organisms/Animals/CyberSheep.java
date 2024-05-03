package Organisms.Animals;

import Organisms.Animal;
import Organisms.Organism;

import java.util.Vector;

public class CyberSheep extends Animal
{
    public CyberSheep(int row, int column)
    {
        super(11, 4, "cyber_sheep", 'c', row, column, "cyber_sheep.png");
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