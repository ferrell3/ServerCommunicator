package Client;

import java.util.Scanner;

import Interfaces.IStringProcessor;
import Models.Request;
import Models.Results;

public class StringProcessorProxy_NoCommands implements IStringProcessor {
    private static StringProcessorProxy_NoCommands myInstance = new StringProcessorProxy_NoCommands();

    public static StringProcessorProxy_NoCommands getInstance() { return myInstance; }

    private StringProcessorProxy_NoCommands() {}

    @Override
    public String toLowerCase(Request input) {
        return null;
    }

    @Override
    public String trim(Request input) {
        return null;
    }

    @Override
    public String parseInteger(Request input) throws NumberFormatException {
        return null;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true)
        {
            System.out.println("Welcome to Phase 0.5! Prepare to have your strings processed! (Enter Q to quit)");
            System.out.print("Enter a method to process: ");
            String url = in.nextLine().toLowerCase();
            if(url.equals("q")) { break; }
            else if(!url.equals("parseinteger")
                    && !url.equals("trim")
                    && !url.equals("tolowercase"))
            {
                System.out.println("Sorry, that's not a valid suffix. Maybe \"toLowerCase.\"");
            }
            else
            {
                System.out.print("Enter a string to process: ");

                String str = in.nextLine();
                if(str.toLowerCase().equals("q")) { break; }

                Request reqInfo = new Request(str);
                Results results = ClientCommunicator.getInstance().send(url, reqInfo);
                if (results.isSuccess())
                {
                    System.out.println(results.getData());
                }
                else
                {
                    System.out.println(results.getErrorInfo());
                }
                System.out.println();
            }
        }
    }
}
