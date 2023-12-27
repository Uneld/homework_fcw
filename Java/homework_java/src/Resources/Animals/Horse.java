package Resources.Animals;

import Records.Command;
import Resources.PrototypeAnimal.PackAnimals;

import java.time.LocalDate;

public class Horse extends PackAnimals {
    public Horse(String name, LocalDate birthData, int loadCapacity) {
        super(name, birthData, loadCapacity);
        this.trainNewCommand(new Command("Sit"));
        this.trainNewCommand(new Command("Jump"));
    }
}
