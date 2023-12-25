package Program;

import Enums.TypeAnimals;
import Exceptions.NoTypeAnimalException;
import Interfaces.HumanFriendsInterface;
import Interfaces.IOViewInterface;
import Records.PackAnimalData;
import Records.PetsData;
import Resources.Animals.*;
import Resources.PrototypeAnimal.Animals;
import Resources.PrototypeAnimal.Pets;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Worker {
    private final HumanFriendsInterface hfStore;
    private final IOViewInterface IOView;

    private boolean flagWork = true;
    private int switchMainMenu = 0;
    private int switchAnimalMenu = 0;

    final int MAX_MAIN_MENU_CHOOSE = 5;
    final int MIN_MAIN_MENU_CHOOSE = 0;

    final int MAX_TYPE_CHOOSE = 6;
    final int MIN_TYPE_CHOOSE = 0;

    public Worker(HumanFriendsInterface hfStore, IOViewInterface IOView) {
        this.hfStore = hfStore;
        this.IOView = IOView;
    }

    public void process() {
        Integer choose = 0;
        while (flagWork) {

            switch (switchMainMenu) {
                case 0 -> {
                    showMainMenu();
                    try {
                        choose = Integer.parseInt(IOView.inputRequest());
                        if (choose > MAX_MAIN_MENU_CHOOSE || choose < MIN_MAIN_MENU_CHOOSE) {
                            throw new NumberFormatException();
                        }
                        switchMainMenu = choose;
                    } catch (NumberFormatException e) {
                        IOView.showError("Не верно выбран пункт меню");
                    }
                }
                case 1 -> {
                    String name;
                    LocalDate birthData;
                    int type = 0;

                    IOView.showMessage("Введите имя животного:");
                    name = IOView.inputRequest().strip();

                    String inputDate = "";
                    IOView.showMessage("Введите дату рождения животного в формате dd-MM-yyyy:");
                    try {
                        inputDate = IOView.inputRequest();
                        birthData = parseDate(inputDate);
                    } catch (DateTimeParseException e) {
                        IOView.showError("Неправильная дата: " + inputDate);
                        switchMainMenu = 1;
                        continue;
                    }

                    IOView.showMessage("Выберите тип животного");
                    showTypeAnimals();
                    try {
                        type = Integer.parseInt(IOView.inputRequest()) - 1;
                        if (type > MAX_TYPE_CHOOSE || type < MIN_TYPE_CHOOSE) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException e) {
                        IOView.showError("Не верно выбран тип");
                        switchMainMenu = 1;
                        continue;
                    }

                    TypeAnimals typeAnimals = TypeAnimals.values()[type];
                    switch (typeAnimals) {
                        case CAT, DOG, HAMSTER -> {
                            IOView.showMessage("Введите породу:");
                            String breed = IOView.inputRequest().strip();
                            PetsData petsData = new PetsData(name, birthData, typeAnimals, breed);
                            hfStore.addNewAnimals(petsData);
                        }
                        case DONKEY, HORSE, CAMEL -> {
                            IOView.showMessage("Введите переносимы вес:");
                            int loadCapacity = 0;
                            try {
                                loadCapacity = Integer.parseInt(IOView.inputRequest());
                            } catch (NumberFormatException e) {
                                IOView.showError("Не верный ввод веса");
                                switchMainMenu = 1;
                                continue;
                            }
                            PackAnimalData packAnimalData = new PackAnimalData(name, birthData, typeAnimals, loadCapacity);
                            hfStore.addNewAnimals(packAnimalData);
                        }
                    }

                    IOView.showMessage("Животное успешно добавлено");
                    switchMainMenu = 0;
                }
                case 4 -> {
                    List<Animals> animalsList = hfStore.getAllAnimalsList();
                    int count = 0;
                    for (Animals animal : animalsList) {
                        count++;
                        showAnimalInfo(count, animal);
                    }
                    IOView.showMessage("");
                    switchMainMenu = 0;
                }
                case 5 -> {
                    flagWork = false;
                    IOView.showMessage("Завершение программы");
                }
            }
        }

    }

    private void showMainMenu() {
        IOView.showMessage("Реестр домашних животных");
        IOView.showMessage("1. Завести новое животное");
        IOView.showMessage("2. Найти животное по имени");
        IOView.showMessage("3. Найти животное по имени и возрасту");
        IOView.showMessage("4. Вывести список животных по возрасту");
        IOView.showMessage("5. Выход");
    }

    private void showAnimalMenu() {
        IOView.showMessage("1. Вывести список команд которые выполняет");
        IOView.showMessage("2. Исполнить команду");
        IOView.showMessage("3. Обучение новой команде");
    }

    private void showTypeAnimals() {
        IOView.showMessage("1. Кот");
        IOView.showMessage("2. Собака");
        IOView.showMessage("3. Хомяк");
        IOView.showMessage("4. Осел");
        IOView.showMessage("5. Лошадь");
        IOView.showMessage("6. Верблюд");
    }

    private LocalDate parseDate(String stringDate) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(stringDate, formatter);
    }

    private void showAnimalInfo(int count, Animals animal) {
        String typeAnimal = "Кот";

        if (animal instanceof Dog) {
            typeAnimal = "Собака";
        }
        if (animal instanceof Hamster) {
            typeAnimal = "Хомяк";
        }
        if (animal instanceof Horse) {
            typeAnimal = "Лошадь";
        }
        if (animal instanceof Camel) {
            typeAnimal = "Верблюд";
        }
        if (animal instanceof Donkey) {
            typeAnimal = "Осел";
        }

        IOView.showMessage(count + ". " + "Имя: " + animal.getName() + "; Возраст: " + animal.getAge() + "; Тип: " + typeAnimal + ';');
    }
}
