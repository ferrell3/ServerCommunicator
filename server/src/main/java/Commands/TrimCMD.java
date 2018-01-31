package Commands;

import Interfaces.ICommand;
import Models.Request;
import Models.Results;
import Server.StringProcessor;

public class TrimCMD implements ICommand{
    Request input;

    public TrimCMD(Request input){
        this.input = input;
    }

    @Override
    public Results execute() {
        return new Results(StringProcessor.getInstance().trim(input));
    }
}
