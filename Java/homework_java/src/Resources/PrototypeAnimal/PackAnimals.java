package Resources.PrototypeAnimal;

import Resources.PrototypeAnimal.Animals;

import java.util.Date;

public abstract class PackAnimals extends Animals {
    int loadCapacity;

    public PackAnimals(String name, Date birthData, int loadCapacity) {
        super(name, birthData);
        this.loadCapacity = loadCapacity;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }
}
