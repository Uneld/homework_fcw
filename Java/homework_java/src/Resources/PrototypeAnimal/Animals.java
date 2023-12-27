package Resources.PrototypeAnimal;

import Exceptions.NoCommandException;
import Interfaces.InterfaceCommands;
import Records.Command;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public abstract class Animals implements InterfaceCommands, Comparable<Animals> {
    String name = "Нет имени";
    LocalDate birthDate;

    List<Command> commands;
    static int counter;

    public Animals(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
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
        Period period = Period.between(birthDate, dateNow);

        return period.getYears();
    }

    public List<Command> getCommands() {
        return commands;
    }

    @Override
    public void trainNewCommand(Command command) {
        commands.add(command);
    }

    @Override
    public String performCommand(Command command) throws NoCommandException {
        if (!commands.contains(command)) {
            throw new NoCommandException(name, command);
        }
        return '"' + name + " выполнило команду " + command.name() + '"';
    }

    @Override
    public int compareTo(Animals o) {
        return this.getAge() - o.getAge();
    }
}
