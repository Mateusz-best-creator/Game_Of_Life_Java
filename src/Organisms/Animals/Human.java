package Organisms.Animals;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Organisms.Animal;
import Organisms.Enums.*;
import Organisms.Organism;

import java.util.Vector;

public class Human extends Animal implements KeyboardPress
{
    static int HUMAN_COUNTER = 0;
    int board_height = -1, board_width = -1;
    final int ABILITY_STRENGTH_BOOST = 10;
    static int ability_local_counter = 0;
    boolean ability_activated;

    public Human(int row, int column)
    {
        super(5, 4, "Human", 'H', row, column, "human.png", OrganismType.HUMAN);
        HUMAN_COUNTER += 1;
        this.ability_activated = false;
        human_normal_strength = 5;
    }

    public Human(Organism o)
    {
        super(o.get_strength(), o.get_initiative(), o.get_name(), o.get_character(), o.get_row(), o.get_column(), "human.png", OrganismType.HUMAN);
        if (human_normal_strength < o.get_strength())
        {
            this.ability_activated = true;
        }
        ability_local_counter = Human.get_ability_local_counter();
    }

    public Human(int row, int column, int age, boolean ability_activated, int ability_counter_from_file)
    {
        super(5, 4, "Human", 'H', row, column, "human.png", OrganismType.HUMAN);
        HUMAN_COUNTER += 1;
        this.ability_activated = ability_activated;
        ability_local_counter = ability_counter_from_file;
        this.age = age;
    }

    @Override
    public int get_organism_counter()
    {
        return HUMAN_COUNTER;
    }
    @Override
    public void decrease_static_counter()
    {
        HUMAN_COUNTER -= 1;
    }

    @Override
    public ActionResult action(char[][] grid_board)
    {
        this.previous_row = this.row;
        this.previous_column = this.column;
        this.board_height = grid_board.length;
        this.board_width = grid_board[0].length;
        return new ActionResult(ActionType.MOVE);
    }

    @Override
    public CollisionResult collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {
        int index = 0;
        for (Organism o : organisms)
        {
            if (o.get_row() == this.row && o.get_column() == this.column && index != current_index)
            {
                if (o.get_type() == OrganismType.SOSNOWSKY_HOGWEED || o.get_type() == OrganismType.BELLADONNA)
                {
                    int[] indexes = {current_index, index};
                    System.out.println(this.get_name() + " eat " + o.get_name() + " and " + o.get_name()
                            + " eat " + this.get_name() + " at (" + this.row + ", " + this.column + ")");
                    return new CollisionResult(CollisionType.POISON_PLANT, indexes);
                }
            }
            index += 1;
        }
        return this.default_animal_collision(grid_board, organisms, current_index);
    }

    private boolean human_go(Direction dir, char[][] board)
    {
        assert(this.board_width != -1 && this.board_height != -1);
        this.previous_row = this.row;
        this.previous_column = this.column;
        boolean moved = false;
        board[this.row][this.column] = 'e';

        if (dir == Direction.LEFT && this.column > 0)
        {
            this.organism_move_left();
            moved = true;
        }
        else if (dir == Direction.TOP && this.row > 0)
        {
            this.organism_move_top();
            moved = true;
        }
        else if (dir == Direction.RIGHT && this.column < board_width - 1)
        {
            this.organism_move_right();
            moved = true;
        }
        else if (dir == Direction.BOTTOM && this.row < board_height - 1)
        {
            this.organism_move_bottom();
            moved = true;
        }
        if (moved)
        {
            board[this.row][this.column] = this.get_character();
            if (this.ability_activated)
                this.set_strength(this.get_strength() - 1);
            if (this.get_strength() == human_normal_strength && this.ability_activated) // default human strength
            {
                this.ability_activated = false;
                ability_local_counter = 5;
            }
            if (!this.ability_activated && ability_local_counter != 0)
                ability_local_counter -= 1;
        }
        return moved;
    }

    @Override
    public void handleKeyboardInput(JFrame frame, char[][] board)
    {
        Object lock = new Object();
        frame.addKeyListener(new KeyListener() {
            boolean pressedKey = false;

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e)
            {
                int keyCode = e.getKeyCode();
                Direction direction = null;
                if (!pressedKey)
                {
                    if (keyCode == KeyEvent.VK_LEFT)
                    {
                        direction = Direction.LEFT;
                    } else if (keyCode == KeyEvent.VK_UP)
                    {
                        direction = Direction.TOP;
                    } else if (keyCode == KeyEvent.VK_RIGHT)
                    {
                        direction = Direction.RIGHT;
                    } else if (keyCode == KeyEvent.VK_DOWN)
                    {
                        direction = Direction.BOTTOM;
                    }
                    else if (keyCode == KeyEvent.VK_S)
                    {
                        activate_special_ability();
                    }
                    if (direction != null)
                    {
                        pressedKey = human_go(direction, board);
                    }
                    if (pressedKey)
                    {
                        synchronized (lock)
                        {
                            lock.notify();
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        synchronized (lock)
        {
            try
            {
                lock.wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void activate_special_ability()
    {
        if (!ability_activated && ability_local_counter == 0)
        {
            this.set_strength(this.get_strength() + ABILITY_STRENGTH_BOOST);
            System.out.println("Activating human special ability: MAGICAL POTION, new strength = " + this.get_strength());
            ability_activated = true;
        }
        else if (ability_activated)
        {
            System.out.println("Human special ability already activated...");
        }
        else
        {
            System.out.println("Cant activate human special ability now, you have to wait " + ability_local_counter + " turns...");
        }
    }

    public String string_check_ability()
    {
        if (ability_activated)
            return "YES";
        return "NO";
    }

    public static int get_ability_local_counter()
    {
        return ability_local_counter;
    }
}