package de.febanhd.fcommand.parser.impl;

import de.febanhd.fcommand.parser.ParameterParser;
import de.febanhd.fcommand.executor.CommandExecutor;

import java.util.ArrayList;
import java.util.List;

public class EmptyStringParameterParser implements ParameterParser<String> {

    @Override
    public List<String> getCompletions(CommandExecutor executor) {
        return new ArrayList<>();
    }

    @Override
    public boolean isValid(String in, CommandExecutor executor) {
        return true;
    }

    @Override
    public String convertString(String string) {
        return string;
    }
}
