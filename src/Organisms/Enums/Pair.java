package Organisms.Enums;

public class Pair<X, Y>
{
    private final X row;
    private final Y col;

    public Pair(X row, Y col)
    {
        this.row = row;
        this.col = col;
    }

    public X get_row() {
        return row;
    }

    public Y get_col() {
        return col;
    }
}
