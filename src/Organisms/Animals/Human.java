package Organisms.Animals;

import Organisms.Animal;
import Organisms.Enums.CollisionResult;
import Organisms.Enums.CollisionType;
import Organisms.Enums.OrganismType;
import Organisms.Organism;

import java.util.Vector;

public class Human extends Animal
{
    static int HUMAN_COUNTER = 0;

    public Human(int row, int column)
    {
        super(5, 4, "Human", 'H', row, column, "human.png", OrganismType.HUMAN);
        HUMAN_COUNTER += 1;
    }

    @Override
    public int get_organism_counter()
    {
        return HUMAN_COUNTER;
    }

    @Override
    public void action(char[][] grid_board)
    {
        this.previous_row = this.row;
        this.previous_column = this.column;
    }

    @Override
    public CollisionResult collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {
        return this.default_animal_collision(grid_board, organisms, current_index);
    }
}