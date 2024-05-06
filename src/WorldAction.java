
public enum WorldAction
{
    SAVE_TO_FILE('s'),
    READ_FROM_FILE('r'),
    QUIT('q'),
    ADDING('a'),
    CELL_NOT_EMPTY('e'),
    PLAY('p');

    private final char character;

    WorldAction(char ch) {
        this.character = ch;
    }

    public static WorldAction from_char(char ch) {
        for (WorldAction action : WorldAction.values()) {
            if (action.character == ch) {
                return action;
            }
        }
        throw new IllegalArgumentException("No constant with character " + ch + " found");
    }

    public char getChar()
    {
        return this.character;
    }
}
