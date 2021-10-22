package de.febanhd.fcommand.parser;

import de.febanhd.fcommand.executor.CommandExecutor;

import java.util.List;

public interface ParameterParser<T> {

    List<String> getCompletions(CommandExecutor executor);
    boolean isValid(String in, CommandExecutor executor);
    T convertString(String string);
}
