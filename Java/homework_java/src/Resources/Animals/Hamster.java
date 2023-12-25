package Resources.Animals;

import Resources.Command;
import Resources.PrototypeAnimal.Pets;

import java.util.Date;

public class Hamster extends Pets {
    public Hamster(String name, Date birthData, String breed) {
        super(name, birthData, breed);
        this.trainNewCommand(new Command("Roll"));
        this.trainNewCommand(new Command("Hide"));
    }
}
