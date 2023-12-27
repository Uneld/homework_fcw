package Interfaces;

import Exceptions.AnimalExistsException;
import Exceptions.NoTypeAnimalException;
import Exceptions.NotFoundAnimalException;
import Records.PackAnimalData;
import Records.PetsData;
import Resources.PrototypeAnimal.Animals;

import java.util.List;

public interface HumanFriendsInterface {
    void addNewAnimals(PetsData data) throws NoTypeAnimalException, AnimalExistsException;

    void addNewAnimals(PackAnimalData data) throws NoTypeAnimalException, AnimalExistsException;

    Animals findAnimalsByName(String name) throws NotFoundAnimalException;

    Animals findAnimalsByNameAndAge(String name, int age) throws NotFoundAnimalException;

    List<Animals> getAllAnimalsList();
    void deleteAnimal(Animals animal);
}
