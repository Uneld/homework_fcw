package Interfaces;

import Exceptions.NoCommandException;
import Records.Command;

public interface InterfaceCommands {
    void trainNewCommand( Command command);

    String performCommand( Command command) throws NoCommandException;
}
