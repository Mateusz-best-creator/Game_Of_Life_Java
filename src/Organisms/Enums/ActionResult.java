package Organisms.Enums;

import javax.swing.*;
import java.util.Vector;

public class ActionResult
{
    private final ActionType action_type;
    private Vector<Pair<Integer,Integer>> coords_to_remove = new Vector<>();
    private int row;
    private int col;

    public ActionResult(ActionType type, Vector<Pair<Integer,Integer>> coords_to_remove)
    {
        this.action_type = type;
        this.coords_to_remove = coords_to_remove;
    }

    public ActionResult(ActionType type, int row, int col)
    {
        this.action_type = type;
        this.row = row;
        this.col = col;
    }

    public ActionResult(ActionType type)
    {
        this.action_type = type;
    }

    public int get_row()
    {
        return this.row;
    }

    public int get_col()
    {
        return this.col;
    }

    public ActionType get_type()
    {
        return action_type;
    }

    public Vector<Pair<Integer, Integer>> get_coords_to_remove()
    {
        return coords_to_remove;
    }
}
