package Resources.PrototypeAnimal;

import java.time.LocalDate;

public abstract class Pets extends Animals {
    String breed;

    public Pets(String name, LocalDate birthData, String breed) {
        super(name, birthData);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }
}
