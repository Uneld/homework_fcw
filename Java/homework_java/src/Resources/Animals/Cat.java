package Resources.Animals;

import Records.Command;
import Resources.PrototypeAnimal.Pets;

import java.time.LocalDate;

public class Cat extends Pets {
    public Cat(String name, LocalDate birthData, String breed) {
        super(name, birthData, breed);
        this.trainNewCommand(new Command("Sit"));
        this.trainNewCommand(new Command("Jump"));
    }
}
