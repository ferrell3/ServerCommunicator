package Server;

import Interfaces.IStringProcessor;
import Models.Request;
import Models.Results;

public class StringProcessor implements IStringProcessor{
    private static StringProcessor myInstance = new StringProcessor();

    public static StringProcessor getInstance() { return myInstance; }

    private StringProcessor() {}

    @Override
    public Results toLowerCase(Request input) {
        System.out.println("In toLowerCase");
        System.out.println("Input: " + input.getData());
        return new Results(input.getData().toLowerCase());
    }

    @Override
    public Results trim(Request input) {
        System.out.println("In trim");
        System.out.println("Input: " + input.getData());

        return new Results(input.getData().trim());
    }

    @Override
    public Results parseInteger(Request input) throws NumberFormatException {
        System.out.println("In parseInteger");
        System.out.println("Input: " + input.getData());

        int num;
        try {
            num = Integer.parseInt(input.getData());
            return new Results(Integer.toString(num));
        } catch (NumberFormatException e){
//            System.out.println("Caught in Server.StringProcessor.parseInteger()");
            throw e;
        }
    }

}
