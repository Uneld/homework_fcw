package Resources.Animals;

import Resources.Command;
import Resources.PrototypeAnimal.Pets;

import java.time.LocalDate;
import java.util.Date;

public class Hamster extends Pets {
    public Hamster(String name, LocalDate birthData, String breed) {
        super(name, birthData, breed);
        this.trainNewCommand(new Command("Roll"));
        this.trainNewCommand(new Command("Hide"));
    }
}
