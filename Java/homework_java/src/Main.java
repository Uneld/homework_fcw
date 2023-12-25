import Program.HumanFriends;
import Program.IOView;
import Program.Worker;
import Resources.Animals.Cat;
import Resources.Animals.Dog;
import Resources.PrototypeAnimal.Animals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Animals> animalsList = new ArrayList<>();
        animalsList.add(new Cat("Whiskers", LocalDate.of(2019, 5,15), "Siamese"));
        animalsList.add(new Dog("Buddy", LocalDate.of(2018, 12,10), "Dachshund"));
        animalsList.add(new Cat("Smudge", LocalDate.of(2020, 2,20), "Birman"));


        HumanFriends humanFriends = new HumanFriends(animalsList);
        IOView ioView = new IOView();
        Worker worker = new Worker(humanFriends, ioView);
        worker.process();
    }
}
