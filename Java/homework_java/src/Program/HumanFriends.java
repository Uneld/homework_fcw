package Program;

import Exceptions.AnimalExistsException;
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
    public void addNewAnimals(PetsData data) throws NoTypeAnimalException, AnimalExistsException {
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
        checkContains(temp);
        animalsList.add(temp);
    }

    @Override
    public void addNewAnimals(PackAnimalData data) throws NoTypeAnimalException, AnimalExistsException {
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
        checkContains(temp);
        animalsList.add(temp);
    }

    @Override
    public Animals findAnimalsByName(String name) throws NotFoundAnimalException {
        for (Animals animal : animalsList) {
            if (animal.getName().strip().equalsIgnoreCase(name)) {
                return animal;
            }
        }
        throw new NotFoundAnimalException(name);
    }

    @Override
    public Animals findAnimalsByNameAndAge(String name, int age) throws NotFoundAnimalException {
        for (Animals animal : animalsList) {
            String animalName = animal.getName().strip();

            if (animalName.equalsIgnoreCase(name) && animal.getAge() == age) {
                return animal;
            }
        }
        throw new NotFoundAnimalException(name, age);
    }

    @Override
    public List<Animals> getAllAnimalsList() {
        return animalsList;
    }

    @Override
    public void deleteAnimal(Animals animal){
        animalsList.remove(animal);
    }

    private void checkContains(Animals animal) throws AnimalExistsException {
        if (animalsList.contains(animal)){
            throw new AnimalExistsException();
        }
    }
}
