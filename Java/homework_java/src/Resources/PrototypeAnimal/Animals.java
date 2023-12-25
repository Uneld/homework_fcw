package Resources.PrototypeAnimal;

import Exceptions.ExceptionNoCommand;
import Interfaces.InterfaceCommands;
import Resources.Command;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Animals implements InterfaceCommands {
    String name = "Нет имени";
    LocalDate birthData;

    List<Command> commands;
    static int counter;

    public Animals(String name, LocalDate birthData) {
        this.birthData = birthData;
        commands = new ArrayList<>();
        counter++;
    }

    int getCountAnimals() {
        return counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        LocalDate dateNow = LocalDate.now();
        Period period = Period.between(dateNow, birthData);

        return period.getYears();
    }

    @Override
    public void trainNewCommand(Command command) {
        commands.add(command);
    }

    @Override
    public String performCommand(Command command) throws ExceptionNoCommand {
        if (!commands.contains(command)) {
            throw new ExceptionNoCommand(name, command);
        }
        return name + " выполнило команду " + command.getName();
    }
}
