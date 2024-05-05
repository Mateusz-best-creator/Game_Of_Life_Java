package Organisms;

import Organisms.Enums.*;

import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Vector;

public abstract class Organism
{
    private int strength, age;
    private final int initiative;
    private final String name;
    private final char character;
    private final OrganismType type;
    Image organismImage;

    static protected Random randomGenerator = new Random();

    protected int row, column;
    protected int previous_row, previous_column;
    static protected final int MAX_ANIMAL_AMOUNT = 5;

    public Organism(int strength, int initiative, String name, char character, int row, int column, String image_name, OrganismType type)
    {
        this.name = name;
        this.strength = strength;
        this.initiative = initiative;
        this.character = character;
        this.row = row;
        this.column = column;
        this.organismImage = Toolkit.getDefaultToolkit().getImage("src/Assets/" + image_name);
        this.age = 0;
        this.type = type;
    }

    public abstract ActionResult action(char[][] grid_board);
    public abstract CollisionResult collision(char[][] grid_board, Vector<Organism> organisms, int current_index);
    public abstract int get_organism_counter();
    public abstract void decrease_static_counter();

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

    public int get_previous_row() { return this.previous_row; }

    public int get_previous_column() { return this.previous_column; }

    public void set_row(int r)
    {
        this.row = r;
    }

    public void set_column(int c)
    {
        this.column = c;
    }

    public OrganismType get_type()
    {
        return this.type;
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

    public void organism_move_bottom()
    {
        System.out.println(this.get_name() + " moves from (" + this.row + ", " + this.column + ") to: (" + (this.row + 1) + ", " + this.column + ") ");
        this.row += 1;
    }
}
