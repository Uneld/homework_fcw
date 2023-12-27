package Resources.Animals;

import Records.Command;
import Resources.PrototypeAnimal.Pets;

import java.time.LocalDate;

public class Hamster extends Pets {
    public Hamster(String name, LocalDate birthData, String breed) {
        super(name, birthData, breed);
        this.trainNewCommand(new Command("Roll"));
        this.trainNewCommand(new Command("Hide"));
    }
}
