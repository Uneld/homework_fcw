package Interfaces;

import Exceptions.NoTypeAnimalException;
import Exceptions.NotFoundAnimalException;
import Records.PackAnimalData;
import Records.PetsData;
import Resources.PrototypeAnimal.Animals;

import java.util.List;

public interface HumanFriendsInterface {
    void addNewAnimals(PetsData data) throws NoTypeAnimalException;

    void addNewAnimals(PackAnimalData data) throws NoTypeAnimalException;

    Animals findAnimalsByName(String name) throws NotFoundAnimalException;

    Animals findAnimalsByNameAndAge(String name, int age) throws NotFoundAnimalException;

    List<Animals> getAllAnimalsList();
}
