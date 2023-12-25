package Interfaces;

import Exceptions.NoCommandException;
import Resources.Command;

public interface InterfaceCommands {
    void trainNewCommand( Command command);

    String performCommand( Command command) throws NoCommandException;
}
