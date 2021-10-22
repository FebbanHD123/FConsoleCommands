package de.febanhd.fcommand;

import de.febanhd.fcommand.parser.ParameterParser;

public class Parameter {

    private final String name;
    private final String description;
    private final String permission;
    private final boolean required;
    private final boolean openEnd;
    private final ParameterParser parser;

    public Parameter(String name, String description, String permission, boolean required, boolean openEnd, ParameterParser autoCompletionHandler) {
        this.name = name;
        this.description = description;
        this.permission = permission;
        this.required = required;
        this.openEnd = openEnd;
        this.parser = autoCompletionHandler;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPermission() {
        return permission;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean isOpenEnd() {
        return openEnd;
    }

    public ParameterParser getParser() {
        return parser;
    }
}
