package de.febanhd.fcommand.exceptions;

import de.febanhd.fcommand.Command;
import de.febanhd.fcommand.Parameter;

public class InvalidCommandParameterException extends Exception {

    private final Command command;
    private final Parameter parameter;

    public InvalidCommandParameterException(Command command, Parameter parameter) {
        this.command = command;
        this.parameter = parameter;
    }

    public Command getCommand() {
        return command;
    }

    public Parameter getParameter() {
        return parameter;
    }
}
