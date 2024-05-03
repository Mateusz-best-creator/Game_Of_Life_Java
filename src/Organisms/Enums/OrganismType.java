package Organisms.Enums;

public enum OrganismType
{
    HUMAN('H'),
    WOLF('w'),
    SHEEP('s'),
    FOX('f'),
    TURTLE('t'),
    ANTELOPE('a'),
    CYBER_SHEEP('c'),
    GRASS('G'),
    SOW_THISTLE('S'),
    BELLADONNA('B'),
    GUARANA('U'),
    SOSNOWSKY_HOGWEED('O');

    private char character;

    OrganismType(char ch)
    {
        this.character = ch;
    }

    private char getCharacter()
    {
        return this.character;
    }

    // Static method to initialize enum from character
    public static OrganismType fromChar(char ch)
    {
        for (OrganismType type : OrganismType.values())
        {
            if (type.getCharacter() == ch) {
                return type;
            }
        }
        // Handle case where character doesn't match any enum constant
        throw new IllegalArgumentException("No enum constant with character " + ch);
    }
}
