package Resources.PrototypeAnimal;

import java.time.LocalDate;

public abstract class PackAnimals extends Animals {
    int loadCapacity;

    public PackAnimals(String name, LocalDate birthData, int loadCapacity) {
        super(name, birthData);
        this.loadCapacity = loadCapacity;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }
}
