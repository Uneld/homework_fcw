package Records;

import Enums.TypeAnimals;

import java.time.LocalDate;

public record PetsData(String name, LocalDate birthData, TypeAnimals type, String breed) {
}
