package Organisms.Enums;

import java.util.Objects;

public enum ActionType
{
    KILLING("Killing"),
    MOVE("Move"),
    SOW("Sow"),
    STAY("Stay");

    final String name;
    ActionType(String n)
    {
        this.name = n;
    }

    private String getName()
    {
        return this.name;
    }

    public static ActionResult fromString(String string, int x, int y)
    {
        for (ActionType actionType : ActionType.values()) {
            if (Objects.equals(actionType.getName(), string)) {
                return new ActionResult(actionType, x, y);
            }
        }
        // Handle case where character doesn't match any enum constant
        throw new IllegalArgumentException("No enum constant with string " + string);
    }
}

