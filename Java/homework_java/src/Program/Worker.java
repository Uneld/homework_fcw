package Program;

import Enums.TypeAnimals;
import Exceptions.AnimalExistsException;
import Exceptions.NoCommandException;
import Exceptions.NotFoundAnimalException;
import Interfaces.HumanFriendsInterface;
import Interfaces.IOViewInterface;
import Records.PackAnimalData;
import Records.PetsData;
import Resources.Animals.*;
import Records.Command;
import Resources.PrototypeAnimal.Animals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;

public class Worker {
    private final HumanFriendsInterface hfStore;
    private final IOViewInterface IOView;

    private boolean flagWork = true;
    private int switchMainMenu = 0;
    private int switchAnimalMenu = 0;

    private Animals findAnimal;

    final int MAX_MAIN_MENU_CHOOSE = 5;
    final int MIN_MAIN_MENU_CHOOSE = 1;

    final int MAX_TYPE_CHOOSE = 6;
    final int MIN_TYPE_CHOOSE = 1;

    final int STATE_ANIMAL_MENU = 59;
    final int MAX_ANIMAL_MENU_CHOOSE = 5;
    final int MIN_ANIMAL_MENU_CHOOSE = 1;

    public Worker(HumanFriendsInterface hfStore, IOViewInterface IOView) {
        this.hfStore = hfStore;
        this.IOView = IOView;
    }

    public void process() {
        int choose = 0;
        while (flagWork) {

            switch (switchMainMenu) {
                case 0 -> {
                    showMainMenu();
                    try {
                        choose = requestAction(MIN_MAIN_MENU_CHOOSE, MAX_MAIN_MENU_CHOOSE);
                        switchMainMenu = choose;
                    } catch (NumberFormatException e) {
                        IOView.showError("Не верно выбран пункт меню");
                    }
                }
                case 1 -> {
                    String name;
                    LocalDate birthData;
                    int type = 0;

                    name = requestAnimalName();

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
                        type = requestAction(MIN_TYPE_CHOOSE, MAX_TYPE_CHOOSE);
                    } catch (NumberFormatException e) {
                        IOView.showError("Не верно выбран тип");
                        switchMainMenu = 1;
                        continue;
                    }

                    type--; // переход к счету от 0
                    TypeAnimals typeAnimals = TypeAnimals.values()[type];
                    switch (typeAnimals) {
                        case CAT, DOG, HAMSTER -> {
                            IOView.showMessage("Введите породу:");
                            String breed = IOView.inputRequest().strip();

                            PetsData petsData = new PetsData(name, birthData, typeAnimals, breed);
                            try {
                                hfStore.addNewAnimals(petsData);
                            } catch (AnimalExistsException e) {
                                IOView.showError("Животное уже существует");
                                switchMainMenu = 1;
                                continue;
                            }
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
                            try {
                                hfStore.addNewAnimals(packAnimalData);
                            } catch (AnimalExistsException e) {
                                IOView.showError("Животное уже существует");
                                switchMainMenu = 1;
                                continue;
                            }
                        }
                    }

                    IOView.showMessage("Животное успешно добавлено");
                    switchMainMenu = 0;
                }
                case 2 -> {
                    String name = requestAnimalName();

                    try {
                        findAnimal = hfStore.findAnimalsByName(name);
                        switchMainMenu = STATE_ANIMAL_MENU;
                    } catch (NotFoundAnimalException e) {
                        IOView.showError("Не найдено животное с таким именем и возрастом");
                        switchMainMenu = 0;
                    }
                }
                case 3 -> {
                    String name = requestAnimalName();
                    int age;

                    try {
                        age = requestAge();
                    } catch (NumberFormatException e) {
                        IOView.showError("Не верно введен возраст");
                        switchMainMenu = 0;
                        continue;
                    }

                    try {
                        findAnimal = hfStore.findAnimalsByNameAndAge(name, age);
                        switchMainMenu = STATE_ANIMAL_MENU;
                    } catch (NotFoundAnimalException e) {
                        IOView.showError("Не найдено животное с таким именем и возрастом");
                        switchMainMenu = 0;
                    }
                }
                case 4 -> {
                    IOView.showMessage("Список животных по возрасту");
                    List<Animals> animalsList = hfStore.getAllAnimalsList();
                    animalsList.sort(Comparator.naturalOrder());
                    int count = 0;
                    for (Animals animal : animalsList) {
                        count++;
                        showAnimalInfo(count, animal);
                    }
                    IOView.showMessage("Общее количество животных: " + animalsList.size());
                    IOView.showMessage("");
                    switchMainMenu = 0;
                }
                case 5 -> {
                    flagWork = false;
                    IOView.showMessage("Завершение программы");
                }
                case STATE_ANIMAL_MENU -> { //состояние для подменю

                    int chooseAnimalMenu;
                    switch (switchAnimalMenu) {
                        case 0 -> {
                            showAnimalMenu();
                            try {
                                chooseAnimalMenu = requestAction(MIN_ANIMAL_MENU_CHOOSE, MAX_ANIMAL_MENU_CHOOSE);
                                switchAnimalMenu = chooseAnimalMenu;
                            } catch (NumberFormatException e) {
                                IOView.showError("Не верно выбран пункт меню");
                            }
                        }
                        case 1 ->{
                            List<Command> commandList= findAnimal.getCommands();
                            int count = 0;
                            for (Command command : commandList) {
                                count++;
                                showCommands(count, command);
                            }
                            switchAnimalMenu = 0;
                        }
                        case 2 ->{
                            Command command = requestAnimalCommand();
                            try {
                                IOView.showMessage("");
                                IOView.showMessage(findAnimal.performCommand(command));
                                IOView.showMessage("");
                            } catch (NoCommandException e) {
                                IOView.showError(e.getMessage());
                            }
                            switchAnimalMenu = 0;
                        }
                        case 3 ->{
                            Command command = requestAnimalCommand();
                            findAnimal.trainNewCommand(command);
                            IOView.showMessage("Животное успешно научилось новой команде");
                            switchAnimalMenu = 0;
                        }
                        case 4 ->{
                            hfStore.deleteAnimal(findAnimal);
                            IOView.showMessage("Животное успешно удалено");
                            switchAnimalMenu = 5;
                        }
                        case 5 -> {
                            switchAnimalMenu = 0;
                            switchMainMenu = 0;
                        }
                    }


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
        IOView.showMessage("4. Удалить животное");
        IOView.showMessage("5. Назад");
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

    private void showCommands(int count, Command command){
        IOView.showMessage(count + ". " + command.name() + ';');
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

    private String requestAnimalName() {
        IOView.showMessage("Введите имя животного:");
        return IOView.inputRequest().strip();
    }

    private Command requestAnimalCommand() {
        IOView.showMessage("Введите команду:");
        return new Command(IOView.inputRequest().strip());
    }

    private int requestAge() {
        IOView.showMessage("Введите возраст животного");
        return Integer.parseInt(IOView.inputRequest());
    }

    private int requestAction(int minThreshold, int maxThreshold) throws NumberFormatException {
        int action;
        action = Integer.parseInt(IOView.inputRequest());
        if (action < minThreshold || action > maxThreshold) {
            throw new NumberFormatException();
        }
        return action;
    }
}
