package Client;

import java.util.Scanner;
import Interfaces.IStringProcessor;
import Models.Command;
import Models.Request;
import Models.Results;

public class StringProcessorProxy_Commands implements IStringProcessor {
    private static StringProcessorProxy_Commands myInstance = new StringProcessorProxy_Commands();

    public static StringProcessorProxy_Commands getInstance() { return myInstance; }

    private StringProcessorProxy_Commands() {}

    @Override
    public String toLowerCase(Request s) {
        return null;
    }

    @Override
    public String trim(Request s) {
        return null;
    }

    @Override
    public String parseInteger(Request s) throws NumberFormatException {
        return null;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true)
        {
            System.out.println("Welcome to Phase 0.5! Prepare to have your strings processed! (Enter Q to quit)");
            System.out.print("Enter a method to process: ");
            String type = in.nextLine().toLowerCase();
            if(type.equals("q")) { break; }
            else if(!type.equals("parseinteger")
                    && !type.equals("trim")
                    && !type.equals("tolowercase"))
            {
                System.out.println("Sorry, that's not a valid suffix. Valid options include: toLowerCase, trim, and parseInteger.");
            }
            else
            {
                System.out.print("Enter the string: ");

                Request input = new Request(in.nextLine());
                if(input.getData().toLowerCase().equals("q")) { break; }

                Command command = new Command(type, input);
                Results result = ClientCommunicator.getInstance().sendCommand(command);

                if (result.isSuccess())
                {
                    System.out.println(result.getData());
                }
                else
                {
                    System.out.println(result.getErrorInfo());
                }
                System.out.println();
            }
        }
    }
}
