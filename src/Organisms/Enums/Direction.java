package Organisms.Enums;

public enum Direction
{
    LEFT(1),
    TOP(2),
    RIGHT(3),
    BOTTOM(4);

    final int value;

    Direction(int v)
    {
        this.value = v;
    }

    private int getValue()
    {
        return this.value;
    }

    public static Direction fromInteger(int integer)
    {
        for (Direction dir : Direction.values())
        {
            if (dir.getValue() == integer) {
                return dir;
            }
        }
        // Handle case where character doesn't match any enum constant
        throw new IllegalArgumentException("No enum constant with integer " + integer);
    }
}
