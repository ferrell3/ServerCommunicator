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

import Models.Request;
import Models.Results;
import Server.StringProcessor;

public class ParseIntegerHandler implements HttpHandler {

    public void handle(HttpExchange httpExchange) throws IOException {
        boolean success = false;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            if (httpExchange.getRequestMethod().toLowerCase().equals("post")) {
                // Extract the JSON string from the HTTP request body
                Reader read = new InputStreamReader(httpExchange.getRequestBody());
                Request reqData = gson.fromJson(read, Request.class);
                read.close();

                // Display/log the request JSON data
                System.out.println(reqData.getData());

                // TODO: Parse the integer from the string
                Results respData = new Results();
                try
                {
                    respData = StringProcessor.getInstance().parseInteger(reqData);
//                    respData.setData(data);
                    respData.setSuccess(true);
                }catch (NumberFormatException e)
                {
                    respData.setSuccess(false);
                    respData.setErrorInfo("Sorry, that's not a valid number format");
                }

                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                String gsonResponse = gson.toJson(respData);
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
