package Commands;

import Interfaces.ICommand;
import Models.Request;
import Models.Results;
import Server.StringProcessor;

public class ToLowerCaseCMD implements ICommand {
    private Request input;

    public ToLowerCaseCMD(Request input){
        this.input = input;
    }

    @Override
    public Results execute() {
        return StringProcessor.getInstance().toLowerCase(input);
//        return new Results(StringProcessor.getInstance().toLowerCase(input));
    }
}
