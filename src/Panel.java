
import javax.swing.*;
import java.awt.*;
import java.util.Vector;

import Organisms.Organism;

public class Panel extends JPanel
{
    Vector<Point> vertical_starting_points = new Vector<>();
    Vector<Point> vertical_ending_points = new Vector<>();

    Vector<Point> horizontal_starting_points = new Vector<>();
    Vector<Point> horizontal_ending_points = new Vector<>();

    int image_height, image_width;
    Vector<Organism> organisms;

    public Panel(int board_height, int board_width, int screen_height, int screen_width,
                 int img_height, int img_width, Vector<Organism> o)
    {
        for (int i = 1; i < board_width; i++)
        {
            vertical_starting_points.add(new Point((screen_width / board_width) * i, 0));
            vertical_ending_points.add(new Point((screen_width / board_width) * i, screen_height));
        }
        for (int i = 1; i < board_height; i++)
        {
            horizontal_starting_points.add(new Point(0, (screen_height / board_height) * i));
            horizontal_ending_points.add(new Point(screen_width, (screen_height / board_height) * i));
        }
        this.image_height = img_height;
        this.image_width = img_width;
        this.organisms = o;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(4));
        Image o_image;
        int o_row, o_column;
        for (int i = 0; i < this.organisms.size(); i++)
        {
            Organism o = this.organisms.get(i);
            o_image = o.get_image();
            o_row = o.get_row();
            o_column = o.get_column();
            g.drawImage(o_image, o_row * image_width, o_column * image_height, image_width, image_height, null);
        }

        // Draw vertical lines
        for (int i = 0; i < vertical_starting_points.size(); i++)
        {
            Point v_start = vertical_starting_points.get(i);
            Point v_end = vertical_ending_points.get(i);
            g.drawLine(v_start.get_x(), v_start.get_y(), v_end.get_x(), v_end.get_y());
        }

        // Draw horizontal lines
        for (int i = 0; i < horizontal_starting_points.size(); i++)
        {
            Point h_start = horizontal_starting_points.get(i);
            Point h_end = horizontal_ending_points.get(i);
            g.drawLine(h_start.get_x(), h_start.get_y(), h_end.get_x(), h_end.get_y());
        }
        repaint();
    }
}
