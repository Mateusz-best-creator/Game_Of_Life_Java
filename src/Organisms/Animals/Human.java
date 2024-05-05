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

    public Human(int row, int column)
    {
        super(50, 4, "Human", 'H', row, column, "human.png", OrganismType.HUMAN);
        HUMAN_COUNTER += 1;
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
        board_height = grid_board.length;
        board_width = grid_board[0].length;
        return new ActionResult(ActionType.MOVE);
    }

    @Override
    public CollisionResult collision(char[][] grid_board, Vector<Organism> organisms, int current_index)
    {
        return this.default_animal_collision(grid_board, organisms, current_index);
    }

    private boolean human_go(Direction dir, char[][] board)
    {
        assert(board_width != -1 && board_height != -1);
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
            board[this.row][this.column] = this.get_character();
        return moved;
    }

    @Override
    public void handleKeyboardInput(JFrame frame, char[][] board)
    {
        Object lock = new Object();
        frame.addKeyListener(new KeyListener() {
            boolean keyPressed = false;

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e)
            {
                int keyCode = e.getKeyCode();
                Direction direction = null;
                if (!keyPressed) {
                    if (keyCode == KeyEvent.VK_LEFT) {
                        direction = Direction.LEFT;
                    } else if (keyCode == KeyEvent.VK_UP) {
                        direction = Direction.TOP;
                    } else if (keyCode == KeyEvent.VK_RIGHT) {
                        direction = Direction.RIGHT;
                    } else if (keyCode == KeyEvent.VK_DOWN) {
                        direction = Direction.BOTTOM;
                    }
                    if (direction != null)
                    {
                        keyPressed = human_go(direction, board);
                    }
                    if (keyPressed) {
                        synchronized (lock) {
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

        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}