
import javax.swing.*;
import java.util.Vector;
import java.util.Random;

import Organisms.Animals.*;
import Organisms.Plants.*;
import Organisms.Organism;
import Organisms.Enums.OrganismType;

public class World
{
    int board_height, board_width;
    int screen_height = 800, screen_width = 800;
    int image_height, image_width;
    private final int ORGANISM_TYPES = 12;

    Vector<Organism> organisms;
    private char[][] grid_board;
    Random rand = new Random();
    int turn_number = 1;

    JFrame frame;

    public World(int height, int width)
    {
        this.frame = new JFrame("Mateusz Wieczorek s197743");
        frame.setSize(this.screen_width, this.screen_height + 35);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.board_height = height;
        this.board_width = width;

        this.image_height = screen_height / board_height;
        this.image_width = screen_width / board_width;

        this.grid_board = new char[board_height][board_width];
        for (int i = 0; i < this.board_height; i++)
            for (int j = 0; j < this.board_width; j++)
                this.grid_board[i][j] = 'e';

        this.organisms = new Vector<>();
        this.initialize_organisms();
    }

    private void drawBoard()
    {
        this.frame.setContentPane(new Panel(this.board_height, this.board_width, this.screen_height, this.screen_width,
                this.image_height, this.image_width, this.organisms));
        frame.setVisible(true);
    }

    private void initialize_organisms()
    {
        int cells = this.board_height * this.board_width;
        int max_amount;
        if (cells < 25) max_amount = 1;
        else if (cells < 50) max_amount = 2;
        else if (cells < 120) max_amount = 3;
        else max_amount = 4;

        char[] characters = {'H', 'w', 's', 'f', 't', 'a', 'c', 'G', 'S', 'B', 'U', 'O'};

        for (int i = 0; i < this.ORGANISM_TYPES; i++)
        {
            OrganismType type = OrganismType.fromChar(characters[i]);
            int randomAmount = rand.nextInt(max_amount) + 1;

            if (type == OrganismType.HUMAN)
                this.add_organism(type, 1);
            else
                this.add_organism(type, randomAmount);
        }
    }

    private void add_organism(OrganismType type, int randomAmount)
    {
        for (int i = 0; i < randomAmount; i++)
        {
            int random_row = rand.nextInt(this.board_height);
            int random_column = rand.nextInt(this.board_width);
            while (this.grid_board[random_row][random_column] != 'e')
            {
                random_row = rand.nextInt(this.board_height);
                random_column = rand.nextInt(this.board_width);
            }
            int start_size = this.organisms.size();
            switch (type)
            {
                case HUMAN:
                    this.organisms.add(new Human(random_row, random_column));
                    break;
                case WOLF:
                    this.organisms.add(new Wolf(random_row, random_column));
                    break;
                case SHEEP:
                    this.organisms.add(new Sheep(random_row, random_column));
                    break;
                case FOX:
                    this.organisms.add(new Fox(random_row, random_column));
                    break;
                case TURTLE:
                    this.organisms.add(new Turtle(random_row, random_column));
                    break;
                case ANTELOPE:
                    this.organisms.add(new Antelope(random_row, random_column));
                    break;
                case CYBER_SHEEP:
                    this.organisms.add(new CyberSheep(random_row, random_column));
                    break;
                case GRASS:
                    this.organisms.add(new Grass(random_row, random_column));
                    break;
                case SOW_THISTLE:
                    this.organisms.add(new SowThistle(random_row, random_column));
                    break;
                case GUARANA:
                    this.organisms.add(new Guarana(random_row, random_column));
                    break;
                case BELLADONNA:
                    this.organisms.add(new Belladonna(random_row, random_column));
                    break;
                case SOSNOWSKY_HOGWEED:
                    this.organisms.add(new SosnowskyHogweed(random_row, random_column));
                    break;
                default:
                    System.out.println("Wrong organism type!!!\n");
                    break;
            }
            int new_size = this.organisms.size();
            if (new_size > start_size)
            {
                this.grid_board[random_row][random_column] = this.organisms.get(this.organisms.size() - 1).get_character();
            }
        }
    }

    public void play_simulation()
    {
        // Draw starting board
        this.drawBoard();
        while (true)
        {
            System.out.println("###\tTurn " + this.turn_number + "\t###");
            int organism_index = 0;

            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (Organism organism : this.organisms)
            {
                organism.increment_age();
                organism.action(this.grid_board);
                organism.collision(this.grid_board, this.organisms, organism_index);
                organism_index += 1;
            }
            this.turn_number += 1;

            // Draw board after changes
            this.drawBoard();
            System.out.println("End of play function = ###\tTurn " + this.turn_number + "\t###");
            if (this.turn_number == 20) break;
        }
    }

}
