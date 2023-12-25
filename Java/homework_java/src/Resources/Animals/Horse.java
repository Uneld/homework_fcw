package Resources.Animals;

import Resources.Command;
import Resources.PrototypeAnimal.PackAnimals;

import java.util.Date;

public class Horse extends PackAnimals {
    public Horse(String name, Date birthData, int loadCapacity) {
        super(name, birthData, loadCapacity);
        this.trainNewCommand(new Command("Sit"));
        this.trainNewCommand(new Command("Jump"));
    }
}
