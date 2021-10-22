package de.febanhd.fcommand.exceptions;

import de.febanhd.fcommand.Command;

public class InvalidCommandUsageException extends Exception {

    private final Command command;

    public InvalidCommandUsageException(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
