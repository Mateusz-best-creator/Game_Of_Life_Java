package Organisms.Enums;

import java.util.Objects;

public enum CollisionType
{
    Multiplication("Multiplication"),
    FIGHT("Fight"),
    NONE("None");

    final String name;
    CollisionType(String n) {
        this.name = n;
    }

    private String getName() {
        return this.name;
    }

    public static CollisionResult fromString(String string, int x, int y)
    {
        for (CollisionType collisionType : CollisionType.values()) {
            if (Objects.equals(collisionType.getName(), string)) {
                return new CollisionResult(collisionType, x, y);
            }
        }
        // Handle case where character doesn't match any enum constant
        throw new IllegalArgumentException("No enum constant with string " + string);
    }
}
