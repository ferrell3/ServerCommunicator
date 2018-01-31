package Handlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;

import Commands.ParseIntegerCMD;
import Commands.ToLowerCaseCMD;
import Commands.TrimCMD;
import Interfaces.ICommand;
import Models.Results;
import Models.Command;

public class ExecCommandHandler implements HttpHandler{

    public void handle(HttpExchange httpExchange) throws IOException {
        boolean success = false;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            if (httpExchange.getRequestMethod().toLowerCase().equals("post")) {
                // Extract the JSON string from the HTTP request body
                Reader read = new InputStreamReader(httpExchange.getRequestBody());
                Command request = gson.fromJson(read, Command.class);
                read.close();

                // TODO: execute command
                ICommand command;
                if(request.getType().equals("tolowercase"))
                {
                    command = new ToLowerCaseCMD(request.getInput());
                }
                else if(request.getType().equals("trim"))
                {
                    command = new TrimCMD(request.getInput());
                }
                else //parseInteger
                {
                    command = new ParseIntegerCMD(request.getInput());
                }

                Results result = new Results();

                try {
                    result = command.execute();
                    result.setSuccess(true);
                }catch (NumberFormatException e)
                {
                    result.setErrorInfo("Sorry, that's not a valid number format");
                    result.setSuccess(false);
                }




                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                String gsonResponse = gson.toJson(result);

                PrintWriter pw = new PrintWriter(httpExchange.getResponseBody());
                pw.write(gsonResponse);
                pw.close();

                success = true;
            }
            if (!success) {
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                httpExchange.getResponseBody().close();
            }
        }
        catch (IOException e) {
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            httpExchange.getResponseBody().close();

            e.printStackTrace();
        }
    }
}
