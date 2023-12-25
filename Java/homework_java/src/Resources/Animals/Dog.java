package Resources.Animals;

import Resources.Command;
import Resources.PrototypeAnimal.Pets;

import java.util.Date;

public class Dog extends Pets {
    public Dog(String name, Date birthData, String breed) {
        super(name, birthData, breed);
        this.trainNewCommand(new Command("Sit"));
        this.trainNewCommand(new Command("Stay"));
        this.trainNewCommand(new Command("Fetch"));
    }
}
