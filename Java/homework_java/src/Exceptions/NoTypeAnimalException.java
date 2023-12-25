package Exceptions;

import Enums.TypeAnimals;

public class NoTypeAnimalException extends Exception {
    TypeAnimals type;
    public NoTypeAnimalException(TypeAnimals type) {
        this.type = type;
    }

    public TypeAnimals getType() {
        return type;
    }
}
