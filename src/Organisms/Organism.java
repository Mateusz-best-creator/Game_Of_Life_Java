package Organisms;

import java.awt.*;

import java.util.Random;
import java.util.Vector;

public abstract class Organism
{
    private int strength, age;
    private final int initiative;
    private final String name;
    private final char character;
    Image organismImage;

    static protected Random randomGenerator = new Random();
    protected int row, column;

    public Organism(int strength, int initiative, String name, char character, int row, int column, String image_name)
    {
        this.name = name;
        this.strength = strength;
        this.initiative = initiative;
        this.character = character;
        this.row = row;
        this.column = column;
        this.organismImage = Toolkit.getDefaultToolkit().getImage("/home/mateusz/IdeaProjects/PLs/src/Assets/" + image_name);
        this.age = 0;
    }

    public abstract void action(char[][] grid_board);
    public abstract void collision(char[][] grid_board, Vector<Organism> organisms, int current_index);

    public Image get_image()
    {
        return this.organismImage;
    }

    public String get_name()
    {
        return this.name;
    }

    public char get_character()
    {
        return this.character;
    }

    public int get_strength()
    {
        return this.strength;
    }

    public int get_initiative()
    {
        return this.initiative;
    }

    public void increment_age()
    {
        this.age += 1;
    }

    public int get_age()
    {
        return this.age;
    }

    public int get_row()
    {
        return this.row;
    }

    public int get_column()
    {
        return this.column;
    }

    public void organism_move_left()
    {
        System.out.println(this.get_name() + " moves from (" + this.row + ", " + this.column + ") to: (" + this.row + ", " + (this.column - 1) + ") ");
        this.column -= 1;
    }

    public void organism_move_top()
    {
        System.out.println(this.get_name() + " moves from (" + this.row + ", " + this.column + ") to: (" + (this.row - 1) + ", " + this.column + ") ");
        this.row -= 1;
    }

    public void organism_move_right()
    {
        System.out.println(this.get_name() + " moves from (" + this.row + ", " + this.column + ") to: (" + this.row + ", " + (this.column + 1) + ") ");
        this.column += 1;
    }

    public void organisms_move_bottom()
    {
        System.out.println(this.get_name() + " moves from (" + this.row + ", " + this.column + ") to: (" + (this.row + 1) + ", " + this.column + ") ");
        this.row += 1;
    }
}
