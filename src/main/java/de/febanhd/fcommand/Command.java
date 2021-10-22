package de.febanhd.fcommand;

import de.febanhd.fcommand.executor.CommandExecutor;
import de.febanhd.fcommand.executor.CommandExecutorCallable;
import de.febanhd.fcommand.handler.CommandHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Command {

    private final String name;
    private final String description;
    private final List<String> aliases;
    private final List<Parameter> parameters;
    private final List<Command> subCommands;
    private final boolean executable;
    private final CommandHandler handler;
    private Command parentCommand;
    private final String permission;
    private final CommandExecutorCallable<List<String>> customUsageCallable;

    public Command(String name, String description, String permission, List<String> aliases, List<Parameter> parameters, List<Command> subCommands, boolean executable, CommandHandler handler, CommandExecutorCallable<List<String>> customUsageCallable) {
        this.name = name;
        this.aliases = aliases;
        this.description = description;
        this.permission = permission;
        this.parameters = parameters;
        this.subCommands = subCommands;
        this.handler = handler;
        this.executable = executable;
        this.customUsageCallable = customUsageCallable;
        this.subCommands.forEach(subFCommand -> subFCommand.setParent(this));
    }

    private String getParentUsage(String str){
        if(this.parentCommand != null) {
            return this.parentCommand.getParentUsage(this.name + " " + str);
        }else {
            return this.name + " " + str;
        }
    }

    public List<String> getUsages(CommandExecutor player) {
        List<String> usages;
        if(customUsageCallable != null) {
            usages = customUsageCallable.call(player);
            if(usages != null) return usages;
        }
        usages = new ArrayList<>();
        String parentUsage = this.getParentUsage("");
        if(executable) {
            StringJoiner joiner = new StringJoiner(" ");
            for (Parameter parameter : parameters) {
                String name = parameter.isRequired() ? "<" + parameter.getName() + ">" : "[" + parameter.getName() + "]";
                joiner.add(name);
            }
            usages.add( parentUsage + joiner);
        }

        for (Command subCommand : subCommands) {
            for (String subCommandUsage : subCommand.getUsages(player)) {
                usages.add(subCommandUsage);
            }
        }

        return usages;
    }

    public void setParent(Command Command) {
        if(this.parentCommand == null) this.parentCommand = Command;
        else throw new IllegalStateException("Parent command is already defined");
    }

    public List<Parameter> getNeededParameters() {
        return this.parameters.stream().filter(p -> p.isRequired()).collect(Collectors.toList());
    }

    public boolean hasSubCommands() {
        return this.subCommands.size() > 0;
    }

    public boolean hasRequiredParameters() {
        return this.parameters.stream().filter(parameter -> parameter.isRequired()).collect(Collectors.toList()).size() > 0;
    }

    public boolean isNameOrAlias(String in) {
        if (this.name.equalsIgnoreCase(in)) return true;
        for (String alias : aliases) {
            if (alias.equalsIgnoreCase(in)) return true;
        }
        return false;
    }

    public int getMaxLength() {
        return this.getMaxArgLength(0);
    }

    protected int getMaxArgLength(int i) {
        for (Parameter parameter : parameters)
            if(parameter.isOpenEnd()) return Integer.MAX_VALUE;
        if(hasSubCommands()) {
            int j = i;
            for (Command subCommand : subCommands) {
                int length = subCommand.getMaxArgLength(j);
                if(length > j) {
                    j = length;
                }
            }
            return j;
        }else {
            return i + this.parameters.size();
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public List<Command> getSubCommands() {
        return subCommands;
    }

    public boolean isExecutable() {
        return executable;
    }

    public CommandHandler getHandler() {
        return handler;
    }

    public Command getParentCommand() {
        return parentCommand;
    }

    public String getPermission() {
        return permission;
    }

    public CommandExecutorCallable<List<String>> getCustomUsageCallable() {
        return customUsageCallable;
    }
}
