package Resources.Animals;

import Resources.Command;
import Resources.PrototypeAnimal.PackAnimals;

import java.time.LocalDate;
import java.util.Date;

public class Donkey extends PackAnimals {
    public Donkey(String name, LocalDate birthData, int loadCapacity) {
        super(name, birthData, loadCapacity);
        this.trainNewCommand(new Command("Walk"));
        this.trainNewCommand(new Command("Carry Load"));
        this.trainNewCommand(new Command("Bray"));
    }
}
