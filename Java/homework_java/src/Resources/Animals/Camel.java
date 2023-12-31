package Resources.Animals;

import Records.Command;
import Resources.PrototypeAnimal.PackAnimals;

import java.time.LocalDate;

public class Camel extends PackAnimals {
    public Camel(String name, LocalDate birthData, int loadCapacity) {
        super(name, birthData, loadCapacity);
        this.trainNewCommand(new Command("Walk"));
        this.trainNewCommand(new Command("Run"));
    }
}
