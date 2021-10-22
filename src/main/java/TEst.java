import de.febanhd.fcommand.FCommandManager;
import de.febanhd.fcommand.Parameter;
import de.febanhd.fcommand.builder.CommandBuilder;
import de.febanhd.fcommand.builder.ParameterBuilder;
import de.febanhd.fcommand.parser.impl.EmptyStringParameterParser;
import de.febanhd.fcommand.wrapper.cosole.ConsoleCommandWrapper;

public class TEst {

    private FCommandManager commandManager;

    public TEst() {
        ConsoleCommandWrapper wrapper = new ConsoleCommandWrapper();
        this.commandManager = FCommandManager.create(wrapper);


        commandManager.registerCommand(
                CommandBuilder.beginCommand("test")
                        .parameter(
                                ParameterBuilder.beginParameter("name")
                                        .required()
                                        .parser(new EmptyStringParameterParser())
                                        .build()
                        )
                        .handler((executor, ctx) -> {
                            executor.sendMessage("Test loool");
                            executor.sendMessage("name: " + ctx.getParameterValue("name", String.class));
                        })
                        .build()
        );


        while(true) {
            wrapper.update();
            try {
                Thread.sleep(25);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }


    }



    public static void main(String[] args) {
        new TEst();
    }
}
