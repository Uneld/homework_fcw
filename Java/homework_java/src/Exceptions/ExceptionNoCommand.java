package Exceptions;

import Resources.Command;

public class ExceptionNoCommand extends Exception{
    String nameAnimal;
    Command command;

    public ExceptionNoCommand(String nameAnimal, Command command) {
        this.nameAnimal = nameAnimal;
        this.command = command;
    }

    @Override
    public String getMessage() {
        return '\"' + nameAnimal + '\"' + " не знает команды " + command.getName();
    }
}
