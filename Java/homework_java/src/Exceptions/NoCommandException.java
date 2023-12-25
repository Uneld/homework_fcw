package Exceptions;

import Resources.Command;

public class NoCommandException extends Exception{
    String nameAnimal;
    Command command;

    public NoCommandException(String nameAnimal, Command command) {
        this.nameAnimal = nameAnimal;
        this.command = command;
    }

    @Override
    public String getMessage() {
        return '\"' + nameAnimal + '\"' + " не знает команды " + command.getName();
    }
}
