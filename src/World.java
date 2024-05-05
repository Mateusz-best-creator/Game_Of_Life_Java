
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.*;

import Organisms.Animals.*;
import Organisms.Enums.CollisionResult;
import Organisms.Organism;
import Organisms.Enums.OrganismType;
import Organisms.Animals.KeyboardPress;
import Organisms.Plants.SosnowskyHogweed;

public class World
{
    static protected int MAX_ANIMAL_AMOUNT = 5;
    Map<String, Integer> entityQuantities = new HashMap<>();

    int board_height, board_width;
    int screen_height = 800, screen_width = 800;
    int image_height, image_width;

    private final Vector<Pair<Integer, Integer>> organisms_coords_to_add = new Vector<>();
    private final Vector<Pair<Integer, Integer>> organisms_coords_to_remove = new Vector<>();
    private final Vector<OrganismType> organisms_types_to_add = new Vector<>();
    private final Vector<Integer> organism_indexes_to_remove = new Vector<>();

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
        this.sort_organisms();
    }

    private void drawBoard()
    {
        this.frame.setContentPane(new Panel(this.board_height, this.board_width, this.screen_height, this.screen_width,
                this.image_height, this.image_width, this.organisms));
        frame.setVisible(true);
    }

    private void sort_organisms()
    {
        Comparator<Organism> compareInitiative = Comparator.comparing(Organism::get_initiative);
        Comparator<Organism> compareAge = Comparator.comparing(Organism::get_age);
        Comparator<Organism> compare = compareInitiative.thenComparing(compareAge);
        this.organisms.sort(compare);
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

        int ORGANISM_TYPES = 12;
        for (int i = 0; i < ORGANISM_TYPES; i++)
        {
            OrganismType type = OrganismType.fromChar(characters[i]);
            int randomAmount = rand.nextInt(max_amount) + 1;

            if (type == OrganismType.HUMAN)
                this.add_organism(type, 1, -1, -1);
            else
                this.add_organism(type, randomAmount, -1, -1);
            Organism o = this.organisms.get(this.organisms.size() - 1);
            this.entityQuantities.put(o.get_name(), o.get_organism_counter());
        }
    }

    private void add_organism(OrganismType type, int randomAmount, int specified_row, int specified_column)
    {
        for (int i = 0; i < randomAmount; i++)
        {
            int random_row = rand.nextInt(this.board_height);
            int random_column = rand.nextInt(this.board_width);
            if (specified_row != -1 && specified_column != -1)
            {
                random_row = specified_row;
                random_column = specified_column;
            }

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
//                case WOLF:
//                    this.organisms.add(new Wolf(random_row, random_column));
//                    break;
//                case SHEEP:
//                    this.organisms.add(new Sheep(random_row, random_column));
//                    break;
//                case FOX:
//                    this.organisms.add(new Fox(random_row, random_column));
//                    break;
//                case TURTLE:
//                    this.organisms.add(new Turtle(random_row, random_column));
//                    break;
//                case ANTELOPE:
//                    this.organisms.add(new Antelope(random_row, random_column));
//                    break;
                case CYBER_SHEEP:
                    this.organisms.add(new CyberSheep(random_row, random_column));
                    break;
//                case GRASS:
//                    this.organisms.add(new Grass(random_row, random_column));
//                    break;
//                case SOW_THISTLE:
//                    this.organisms.add(new SowThistle(random_row, random_column));
//                    break;
//                case GUARANA:
//                    this.organisms.add(new Guarana(random_row, random_column));
//                    break;
//                case BELLADONNA:
//                    this.organisms.add(new Belladonna(random_row, random_column));
//                    break;
                case SOSNOWSKY_HOGWEED:
                    this.organisms.add(new SosnowskyHogweed(random_row, random_column));
                    break;
                default:
                    System.out.println("Wrong organism type: " + type.name());
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
        // sort organisms
        this.sort_organisms();

        do
        {
            System.out.println("###\tTurn " + this.turn_number + "\t###");
            int organism_index = 0;

            for (Organism organism : this.organisms)
            {
                organism.increment_age();

                if (this.organism_indexes_to_remove.contains(organism_index))
                {
                    continue;
                }

                organism.action(this.grid_board);
                if (organism instanceof KeyboardPress)
                {
                    // Cast the organism to KeyboardControllable and call handleKeyboardInput
                    ((KeyboardPress) organism).handleKeyboardInput(frame);
                }
                CollisionResult collision_type = organism.collision(this.grid_board, this.organisms, organism_index);
                int row = collision_type.get_row();
                int col = collision_type.get_col();

                switch (collision_type.getType())
                {
                    case FIGHT:
                        organisms_coords_to_remove.add(new Pair<>(row, col));
                        organism_indexes_to_remove.add(collision_type.get_index());
                        break;
                    case Multiplication:
                        organisms_coords_to_add.add(new Pair<>(row, col));
                        organisms_types_to_add.add(organism.get_type());
                        break;
                    // Including NULL return here
                    default:
                        break;
                }

                organism_index += 1;
            }
            this.turn_number += 1;

            this.multiply_organisms();
            this.remove_organisms();

            // Clear organisms data
            organisms_coords_to_add.clear();
            organisms_types_to_add.clear();
            organisms_coords_to_remove.clear();
            organism_indexes_to_remove.clear();

            // Draw board after changes
            this.drawBoard();
        } while (this.turn_number != 20);
    }

    // Multiply organisms after multiplication
    private void multiply_organisms()
    {
        for (int index = 0; index < this.organisms_coords_to_add.size(); index++)
        {
            boolean added = false;
            OrganismType type = organisms_types_to_add.get(index);
            int currentRow = organisms_coords_to_add.get(index).get_row();
            int currentCol = organisms_coords_to_add.get(index).get_col();

            for (int j = -1; j <= 1; j++)
            {
                for (int k = -1; k <= 1; k++)
                {

                    int new_row = currentRow + j;
                    int new_column = currentCol + k;

                    if (new_row >= 0 && new_row < this.grid_board.length
                        && new_column >= 0 && new_column < this.grid_board[0].length
                        && this.grid_board[new_row][new_column] == 'e')
                    {
                        System.out.println("Creating new " + type.name() + " at (" + new_row + ", " + new_column + ")");
                        this.add_organism(type, 1, new_row, new_column);
                        added = true;
                        break;
                    }
                }
                if (added)
                    break;
            }
        }
    }

    // Remove organisms after collisions
    private void remove_organisms()
    {
        Comparator<Integer> comp = Collections.reverseOrder();
        this.organism_indexes_to_remove.sort(comp);

        for (int i = this.organism_indexes_to_remove.size() - 1; i >= 0; i--)
        {
            int index = this.organism_indexes_to_remove.get(i);
            int row = this.organisms_coords_to_remove.get(i).get_row();
            int col = this.organisms_coords_to_remove.get(i).get_col();
            System.out.println("Removing " + this.organisms.get(index).get_type() + " at (" + row + ", " + col + ")");

            //Decrement static counter
            this.organisms.get(index).decrease_static_counter();
            // Remove organism from vector that has index i
            this.organisms.remove(index);
        }

    }
}
