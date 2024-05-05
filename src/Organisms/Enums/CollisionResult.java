package Organisms.Enums;

import java.util.Vector;

public class CollisionResult
{
    private final CollisionType collision_type;
    private int row;
    private int col;
    private int organism_index = -1;

    // Special for suicide type
    private int[] organism_indexes = new int[2];

    public CollisionResult(CollisionType type, int row, int col, int index)
    {
        this.collision_type = type;
        this.row = row;
        this.col = col;
        this.organism_index = index;
    }

    public CollisionResult(CollisionType type, int[] indexes)
    {
        this.collision_type = type;
        this.organism_indexes = indexes;
    }

    public CollisionResult(CollisionType type, int row, int col)
    {
        this.collision_type = type;
        this.row = row;
        this.col = col;
    }

    public CollisionResult(CollisionType type)
    {
        this.collision_type = type;
    }

    public CollisionType getType()
    {
        return collision_type;
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

    public int[] get_indexes()
    {
        return this.organism_indexes;
    }
}
