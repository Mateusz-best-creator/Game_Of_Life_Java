
import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        int width = Integer.parseInt(JOptionPane.showInputDialog("Enter board width"));
        int height = Integer.parseInt(JOptionPane.showInputDialog("Enter board height"));
        World world = new World(height, width);
        world.play_simulation();
    }
}
