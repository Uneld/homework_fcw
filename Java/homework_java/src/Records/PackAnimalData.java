package Records;

import Enums.TypeAnimals;

import java.time.LocalDate;

public record PackAnimalData(String name, LocalDate birthData, TypeAnimals type, int loadCapacity) {
}
