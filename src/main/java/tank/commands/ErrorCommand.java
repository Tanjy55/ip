package tank.commands;

public class ErrorCommand extends Command {
    private final String errorMessage;

    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(errorMessage);
    }
}
