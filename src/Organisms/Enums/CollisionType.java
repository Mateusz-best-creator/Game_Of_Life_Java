package Organisms.Enums;

import java.util.Objects;

public enum CollisionType
{
    Multiplication("Multiplication"),
    FIGHT("Fight"),
    NONE("None");

    final String name;
    CollisionType(String n)
    {
        this.name = n;
    }

    private String getName()
    {
        return this.name;
    }

    public static CollisionType fromString(String string)
    {
        for (CollisionType collision_type : CollisionType.values())
        {
            if (Objects.equals(collision_type.getName(), string)) {
                return collision_type;
            }
        }
        // Handle case where character doesn't match any enum constant
        throw new IllegalArgumentException("No enum constant with integer " + string);
    }
}
