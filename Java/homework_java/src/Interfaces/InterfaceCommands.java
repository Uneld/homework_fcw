package Interfaces;

import Exceptions.ExceptionNoCommand;
import Resources.Command;

public interface InterfaceCommands {
    void trainNewCommand( Command command);

    String performCommand( Command command) throws ExceptionNoCommand;
}
