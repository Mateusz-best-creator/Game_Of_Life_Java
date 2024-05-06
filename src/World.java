
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

import Organisms.Animals.*;
import Organisms.Enums.*;
import Organisms.Enums.Pair;
import Organisms.Organism;
import Organisms.Animals.KeyboardPress;
import Organisms.Plants.*;

public class World
{
    int board_height, board_width;
    int screen_height = 800, screen_width = 800;
    int image_height, image_width;

    private final Vector<Pair<Integer, Integer>> organisms_coords_to_add = new Vector<>();
    private final Vector<Pair<Integer, Integer>> organisms_coords_to_remove = new Vector<>();
    private final Vector<OrganismType> organisms_types_to_add = new Vector<>();
    private final Vector<Integer> organism_indexes_to_remove = new Vector<>();

    static private final String FILENAME = "src/Filenames/organisms.txt";
    Vector<Organism> organisms;
    private char[][] grid_board;
    Random rand = new Random();
    int turn_number = 1;

    JFrame frame;

    public World(int height, int width)
    {
        this.frame = new JFrame("Mateusz Wieczorek s197743");
        frame.setSize(this.screen_width + 10, this.screen_height + 35);
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
        this.drawBoard();
    }

    private void drawBoard()
    {
        this.frame.setContentPane(new Panel(this.board_height, this.board_width, this.screen_height, this.screen_width,
                this.image_height, this.image_width, this.organisms));
        frame.setVisible(true);
    }

    private void sort_organisms()
    {
        // Find the index of the human (if exists)
        int humanIndex = -1;
        for (int i = 0; i < this.organisms.size(); i++)
        {
            if (this.organisms.get(i).get_type() == OrganismType.HUMAN)
            {
                humanIndex = i;
                break;
            }
        }

        // Sort organisms by initiative and age
        Comparator<Organism> compareInitiative = Comparator.comparing(Organism::get_initiative);
        Comparator<Organism> compareAge = Comparator.comparing(Organism::get_age);
        Comparator<Organism> compare = compareInitiative.thenComparing(compareAge);
        this.organisms.sort(compare.reversed());

        // If human exists, move it to the beginning
        if (humanIndex != -1)
        {
            Organism human = this.organisms.remove(humanIndex);
            this.organisms.add(0, human);
        }
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
        }
        this.sort_organisms();
    }

    private void add_organism(OrganismType type, int randomAmount, int specified_row, int specified_column)
    {
        if (type == null)
            return;
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
                case WOLF:
                    this.organisms.add(new Wolf(random_row, random_column));
                    break;
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

        boolean playing = true;

        while (playing)
        {
            // get keyboard press and wait until it is p or s or l
            WorldAction worldAction = this.get_pressed_key(this.frame);
            if (worldAction == WorldAction.SAVE_TO_FILE)
            {
                this.save_to_file();
                continue;
            }
            else if (worldAction == WorldAction.READ_FROM_FILE)
            {
                this.read_from_file();
                continue;
            }
            else if (worldAction == WorldAction.CELL_NOT_EMPTY)
            {
                System.out.println("Cant add organism here, cell is not empty...");
                continue;
            }
            else if (worldAction == WorldAction.QUIT)
            {
                playing = false;
                continue;
            }
            // Playing case
            else if (worldAction == WorldAction.ADDING)
            {
                this.sort_organisms();
                this.update_board();
                this.drawBoard();
            }

            System.out.println("\n###\tTurn " + this.turn_number + "\t###\n");
            int organism_index = 0;

            for (Organism organism : this.organisms)
            {
                organism.increment_age();

                if (this.organism_indexes_to_remove.contains(organism_index))
                {
                    continue;
                }

                this.handle_action(organism);
                this.handle_collision(organism, organism_index);

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

            // Update board characters
            this.update_board();
            // Draw board after changes
            this.drawBoard();
        }
        this.frame.dispose();
    }

    private void handle_action(Organism organism)
    {
        ActionResult action_result = organism.action(this.grid_board);
        switch (action_result.get_type())
        {
            case KILLING:
                Vector<Pair<Integer, Integer>> coordinates = action_result.get_coordinates();
                this.organisms_coords_to_remove.addAll(coordinates);
                for (Pair<Integer, Integer> integerIntegerPair : organisms_coords_to_remove)
                {
                    int r = integerIntegerPair.get_row();
                    int c = integerIntegerPair.get_col();
                    int index = 0;
                    for (Organism o : this.organisms)
                    {
                        if (o.get_row() == r && o.get_column() == c)
                        {
                            this.organism_indexes_to_remove.add(index);
                        }
                        index += 1;
                    }
                }
                break;
            case SOW:
                int r = action_result.get_row();
                int c = action_result.get_col();
                this.organisms_coords_to_add.add(new Pair<>(r, c));
                this.organisms_types_to_add.add(organism.get_type());
                break;
            case MOVE:
            case STAY:
                break;
        }
        if (organism instanceof KeyboardPress)
        {
            // Cast the organism to KeyboardControllable and call handleKeyboardInput
            ((KeyboardPress) organism).handleKeyboardInput(frame, this.grid_board);
        }
    }

    private void handle_collision(Organism organism, int organism_index)
    {
        CollisionResult collision_type = organism.collision(this.grid_board, this.organisms, organism_index);
        int row = collision_type.get_row();
        int col = collision_type.get_col();

        switch (collision_type.getType())
        {
            case FIGHT:
                int index_to_remove = collision_type.get_index();
                if (this.organisms.get(index_to_remove).get_type() == OrganismType.GUARANA)
                {
                    System.out.println(organism.get_name() + " at (" + organism.get_row() + ", " + organism.get_column() + ") gets +3 to its strength...");
                    organism.increase_guarana_strength();
                }
                organisms_coords_to_remove.add(new Pair<>(row, col));
                organism_indexes_to_remove.add(index_to_remove);
                break;
            case Multiplication:
                organisms_coords_to_add.add(new Pair<>(row, col));
                organisms_types_to_add.add(organism.get_type());
                break;
            case POISON_PLANT:
                for (int index : collision_type.get_indexes())
                    organism_indexes_to_remove.add(index);
                break;
            // Including NULL return here
            default:
                break;
        }
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
        if (this.organisms.isEmpty())
            return;

        Comparator<Integer> comp = Collections.reverseOrder();
        this.organism_indexes_to_remove.sort(comp);
        // Transform the Vector to remove duplicates and maintain sorting order
        Set<Integer> set = new TreeSet<>(Collections.reverseOrder());
        set.addAll(organism_indexes_to_remove);
        organism_indexes_to_remove.clear();
        organism_indexes_to_remove.addAll(set);

        for (int index : this.organism_indexes_to_remove)
        {
            int row = this.organisms.get(index).get_row();
            int col = this.organisms.get(index).get_column();
            System.out.println("Removing " + this.organisms.get(index).get_type() + " at (" + row + ", " + col + ")");

            //Decrement static counter
            this.organisms.get(index).decrease_static_counter();
            // Remove organism from vector that has index i
            this.organisms.remove(index);
        }
    }

    private void update_board()
    {
        for (int i = 0; i < board_width; i++)
            for (int j = 0; j < board_height; j++)
                this.grid_board[i][j] = 'e';
        for (Organism o : this.organisms)
            this.grid_board[o.get_row()][o.get_column()] = o.get_character();
    }

    private void save_to_file()
    {
        try (FileWriter writer = new FileWriter(FILENAME))
        {
            writer.write(board_height + " " + board_width + "\n");
            for (Organism o : this.organisms)
            {
                if (o.get_type() == OrganismType.HUMAN)
                {
                    Human temp_human = new Human(o);
                    String msg = temp_human.string_check_ability();
                    int ability_counter = Human.get_ability_local_counter();

                    writer.write(o.get_character() + " " + o.get_age() + " " + o.get_name() + " " + o.get_row() + " "
                            + o.get_column() + " " + o.get_strength() + " " + o.get_initiative() + " "
                            + msg + " " + ability_counter + "\n");
                }
                else
                {
                    writer.write(o.get_character() + " " + o.get_age() + " " + o.get_name() + " " + o.get_row() + " "
                            + o.get_column() + " " + o.get_strength() + " " + o.get_initiative() + "\n");
                }
            }
        }
        catch (IOException e)
        {
            // Handle any potential I/O errors
            e.printStackTrace();
        }
    }

    public void read_from_file()
    {
        for (int i = 0; i < this.grid_board.length; i++)
            for (int j = 0; j < this.grid_board[0].length; j++)
                this.grid_board[i][j] = 'e';
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME)))
        {
            String line;
            // Read the first line (special) separately
            if ((line = reader.readLine()) != null)
            {
                String[] split_fragments = line.split(" ");
                this.board_width = Integer.parseInt(split_fragments[0]);
                this.board_height = Integer.parseInt(split_fragments[1]);
                this.image_height = this.screen_height / this.board_height;
                this.image_width = this.screen_width / this.board_width;
            }
            this.organisms.clear();
            // Loop through the rest of the file
            while ((line = reader.readLine()) != null)
            {
                String[] split_fragments = line.split(" ");
                OrganismType type = OrganismType.fromChar(split_fragments[0].charAt(0));
                int age = Integer.parseInt(split_fragments[1]);
                int row = Integer.parseInt(split_fragments[3]);
                int column = Integer.parseInt(split_fragments[4]);
                int strength = Integer.parseInt(split_fragments[5]);
                int initiative = Integer.parseInt(split_fragments[6]);

                if (type == OrganismType.HUMAN)
                {
                    boolean ability_activated = false;
                    String bool_result = split_fragments[7];
                    if (Objects.equals(bool_result, "YES"))
                        ability_activated = true;
                    int ability_counter_from_file = Integer.parseInt(split_fragments[8]);
                    this.organisms.add(new Human(row, column, age, ability_activated, ability_counter_from_file));
                }
                else
                {
                    this.add_organism(type, 1, row, column);
                }
                int last_index = this.organisms.size() - 1;
                this.organisms.get(last_index).set_age(age);
                this.organisms.get(last_index).set_strength(strength);
                this.organisms.get(last_index).set_initiative(initiative);
            }
            // Sort all organisms
            this.sort_organisms();
            // Update board characters
            this.update_board();
            // Draw board
            this.drawBoard();
        }
        catch (IOException e)
        {
            // Handle any potential I/O errors
            e.printStackTrace();
        }
    }

    private int calculate_row(int row_click)
    {
        return row_click / this.image_height;
    }

    private int calculate_column(int col_click)
    {
        return col_click / this.image_width;
    }

    private boolean check_if_empty(int row, int col)
    {
        return this.grid_board[row][col] == 'e';
    }

    private WorldAction get_pressed_key(JFrame frame)
    {
        final char[] character = new char[1];
        Object lock = new Object();
        frame.addKeyListener(new KeyListener() {
            boolean pressedKey = false;

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e)
            {
                int keyCode = e.getKeyCode();
                if (!pressedKey)
                {
                    if (keyCode == KeyEvent.VK_P)
                    {
                        System.out.println("Going to the next turn...");
                        pressedKey = true;
                        character[0] = 'p';
                    }
                    else if (keyCode == KeyEvent.VK_S)
                    {
                        System.out.println("Saving state of the game to file...");
                        pressedKey = true;
                        character[0] = 's';
                    }
                    else if (keyCode == KeyEvent.VK_R)
                    {
                        System.out.println("Reading state of the game from file...");
                        pressedKey = true;
                        character[0] = 'r';
                    }
                    else if (keyCode == KeyEvent.VK_Q)
                    {
                        System.out.println("Bye...");
                        pressedKey = true;
                        character[0] = 'q';
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

        frame.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                // Calculate the square numbers
                int squareRow = calculate_row(e.getY());
                int squareCol = calculate_column(e.getX());

                if(!check_if_empty(squareRow, squareCol))
                {
                    character[0] = 'e';
                    return;
                }

                System.out.println("w -> wolf\ts -> sheep");
                System.out.println("f -> fox\tt -> turtle\ta -> antelope");
                System.out.println("c -> cyber_sheep\tG -> grass\tS -> sow_thistle");
                System.out.println("B -> belladonna\tU -> guarana\tO -> sosnowsky_hogweed");
                Scanner scanner = new Scanner(System.in);
                System.out.println("\nPlease choose type of organism you want to add: ");
                char new_organism_character = scanner.nextLine().charAt(0);

                synchronized (lock)
                {
                    // Means we add new character
                    character[0] = 'a';
                    lock.notify();
                }
                OrganismType type = null;
                switch (new_organism_character)
                {
                    case 'w': type = OrganismType.WOLF; break;
                    case 's': type = OrganismType.SHEEP; break;
                    case 'f': type = OrganismType.FOX; break;
                    case 't': type = OrganismType.TURTLE; break;
                    case 'a': type = OrganismType.ANTELOPE; break;
                    case 'c': type = OrganismType.CYBER_SHEEP; break;
                    case 'G': type = OrganismType.GRASS; break;
                    case 'S': type = OrganismType.SOW_THISTLE; break;
                    case 'B': type = OrganismType.BELLADONNA; break;
                    case 'U': type = OrganismType.GUARANA; break;
                    case 'O': type = OrganismType.SOSNOWSKY_HOGWEED; break;
                }
                add_organism(type, 1, squareRow, squareCol);
                if (type != null)
                {
                    System.out.println("Adding new " + type.name() + " at (" + squareRow + ", " + squareCol + ")");
                }
            }
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
        return WorldAction.from_char(character[0]);
    }
}
