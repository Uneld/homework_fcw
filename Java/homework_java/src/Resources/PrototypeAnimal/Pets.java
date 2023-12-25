package Resources.PrototypeAnimal;

import Resources.PrototypeAnimal.Animals;

import java.util.Date;

public abstract class Pets extends Animals {
    String breed;

    public Pets(String name, Date birthData, String breed) {
        super(name, birthData);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }
}
