package Program;

import Exceptions.NoTypeAnimalException;
import Exceptions.NotFoundAnimalException;
import Records.PackAnimalData;
import Records.PetsData;
import Resources.Animals.*;
import Resources.PrototypeAnimal.Animals;

import java.util.List;

public class HumanFriends implements Interfaces.HumanFriendsInterface {
    List<Animals> animalsList;

    public HumanFriends(List<Animals> animalsList) {
        this.animalsList = animalsList;
    }

    @Override
    public void addNewAnimals(PetsData data) throws NoTypeAnimalException {
        Animals temp;
        switch (data.type()) {
            case CAT -> {
                temp = new Cat(data.name(), data.birthData(), data.breed());
            }
            case DOG -> {
                temp = new Dog(data.name(), data.birthData(), data.breed());
            }
            case HAMSTER -> {
                temp = new Hamster(data.name(), data.birthData(), data.breed());
            }
            default -> throw new NoTypeAnimalException(data.type());
        }

        animalsList.add(temp);
    }

    @Override
    public void addNewAnimals(PackAnimalData data) throws NoTypeAnimalException {
        Animals temp;
        switch (data.type()) {
            case CAMEL -> {
                temp = new Camel(data.name(), data.birthData(), data.loadCapacity());
            }
            case DONKEY -> {
                temp = new Donkey(data.name(), data.birthData(), data.loadCapacity());
            }
            case HORSE -> {
                temp = new Horse(data.name(), data.birthData(), data.loadCapacity());
            }
            default -> throw new NoTypeAnimalException(data.type());
        }

        animalsList.add(temp);
    }

    @Override
    public Animals findAnimalsByName(String name) throws NotFoundAnimalException {
        for (Animals animal : animalsList) {
            if (animal.getName().equals(name)) {
                return animal;
            }
        }
        throw new NotFoundAnimalException(name);
    }

    @Override
    public Animals findAnimalsByNameAndAge(String name, int age) throws NotFoundAnimalException {
        for (Animals animal : animalsList) {
            if (animal.getName().equals(name) && animal.getAge() == age) {
                return animal;
            }
        }
        throw new NotFoundAnimalException(name, age);
    }

    @Override
    public List<Animals> getAllAnimalsList() {
        return animalsList;
    }
}
