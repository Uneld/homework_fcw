package Exceptions;

import Enums.TypeAnimals;

public class NoTypeAnimalException extends RuntimeException {
    TypeAnimals type;
    public NoTypeAnimalException(TypeAnimals type) {
        this.type = type;
    }

    public TypeAnimals getType() {
        return type;
    }
}
