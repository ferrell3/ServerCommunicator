package Server;

import Interfaces.IStringProcessor;
import Models.Request;

public class StringProcessor implements IStringProcessor{
    private static StringProcessor myInstance = new StringProcessor();

    public static StringProcessor getInstance() { return myInstance; }

    private StringProcessor() {}

    @Override
    public String toLowerCase(Request input) {
        System.out.println("In toLowerCase");
        System.out.println("Input: " + input.getData());
        return input.getData().toLowerCase();
    }

    @Override
    public String trim(Request input) {
        System.out.println("In trim");
        System.out.println("Input: " + input.getData());

        return input.getData().trim();
    }

    @Override
    public String parseInteger(Request input) throws NumberFormatException {
        System.out.println("In parseInteger");
        System.out.println("Input: " + input.getData());

        int num;
        try {
            num = Integer.parseInt(input.getData());
            return Integer.toString(num);
        } catch (NumberFormatException e){
            throw e;
        }
    }

}
