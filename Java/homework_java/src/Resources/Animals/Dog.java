package Resources.Animals;

import Records.Command;
import Resources.PrototypeAnimal.Pets;

import java.time.LocalDate;

public class Dog extends Pets {
    public Dog(String name, LocalDate birthData, String breed) {
        super(name, birthData, breed);
        this.trainNewCommand(new Command("Sit"));
        this.trainNewCommand(new Command("Stay"));
        this.trainNewCommand(new Command("Fetch"));
    }
}
