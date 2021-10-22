package de.febanhd.fcommand.wrapper.cosole;

import de.febanhd.fcommand.Command;
import de.febanhd.fcommand.FCommandManager;
import de.febanhd.fcommand.executor.CommandExecutor;
import de.febanhd.fcommand.wrapper.CommandWrapper;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.Parser;
import org.jline.reader.impl.DefaultParser;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;
import java.nio.charset.Charset;

public class ConsoleCommandWrapper implements CommandWrapper {

    private final LineReader reader;
    private FCommandManager commandManager;

    public ConsoleCommandWrapper() {
        TerminalBuilder terminalBuilder = TerminalBuilder.builder();
        terminalBuilder.encoding(Charset.defaultCharset());
        terminalBuilder.system(true);
        Terminal terminal = null;
        try {
            terminal = terminalBuilder.build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LineReader reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .parser(new DefaultParser())
                .build();
        this.reader = reader;

    }

    @Override
    public void register(Command command, FCommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void update() {
        if(reader == null)return;
        String line = null;
        try {
            line = reader.readLine();
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(line == null) return;
        line = line.trim();
        if(line.isEmpty()) return;
        commandManager.execute(System.out::println, line);
    }
}
