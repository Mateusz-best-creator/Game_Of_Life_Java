package Organisms.Enums;

import java.util.Objects;

public class CollisionResult
{
    private CollisionType type;
    private int row;
    private int col;
    private int organism_index = -1;

    public CollisionResult(CollisionType type, int row, int col, int index)
    {
        this.type = type;
        this.row = row;
        this.col = col;
        this.organism_index = index;
    }

    public CollisionResult(CollisionType type, int row, int col)
    {
        this.type = type;
        this.row = row;
        this.col = col;
    }

    public CollisionType getType()
    {
        return type;
    }

    public int get_row()
    {
        return row;
    }

    public int get_col()
    {
        return col;
    }

    public int get_index()
    {
        return this.organism_index;
    }
}
