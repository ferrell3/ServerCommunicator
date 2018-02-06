package Commands;

import Interfaces.ICommand;
import Models.Request;
import Models.Results;
import Server.StringProcessor;

public class ParseIntegerCMD implements ICommand {
    Request input;

    public ParseIntegerCMD(Request input){
        this.input = input;
    }

    @Override
    public Results execute() {
        return StringProcessor.getInstance().parseInteger(input);
    }
}
