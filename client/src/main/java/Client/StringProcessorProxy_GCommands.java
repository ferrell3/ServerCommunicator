package Client;

import Interfaces.IStringProcessor;
import java.util.Scanner;
import Models.GenericCommand;
import Models.Request;
import Models.Results;

public class StringProcessorProxy_GCommands implements IStringProcessor {
    private static StringProcessorProxy_GCommands myInstance = new StringProcessorProxy_GCommands();

    public static StringProcessorProxy_GCommands getInstance() { return myInstance; }

    private StringProcessorProxy_GCommands() {}

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

//                Request input = new Request(in.nextLine());
                String input = in.nextLine();
                if(input.toLowerCase().equals("q")) { break; }
//                GenericCommand command = new GenericCommand("IStringProcessor", type,
//                        new Class<?>[]{ Request.class }, new Object[]{new Request(input)});

//                GenericCommand move = new GenericCommand("VideoGame", "move",
//                        new Class<?>[]{ int.class, Request.class },
//                        new Object[] { 3 , new Location(75, 12) });

//                Results result = ClientCommunicator.getInstance().sendGenericCommand(command);
//
//                if (result.isSuccess())
//                {
//                    System.out.println(result.getData());
//                }
//                else
//                {
//                    System.out.println(result.getErrorInfo());
//                }
//                System.out.println();
            }
        }
    }
}
