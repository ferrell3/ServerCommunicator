package Handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;

import Models.Request;
import Models.Results;
import Server.StringProcessor;

public class ToLowerCaseHandler implements HttpHandler {

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
//                System.out.println(reqData.getData());
                // TODO: Convert string to lowercase
                Results respData = StringProcessor.getInstance().toLowerCase(reqData);
                respData.setSuccess(true);

                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                // We are not sending a response body, so close the response body
                // output stream, indicating that the response is complete.
                //httpExchange.getResponseBody().close();

                String gsonResponse = gson.toJson(respData);
                PrintWriter pw = new PrintWriter(httpExchange.getResponseBody());
                pw.write(gsonResponse);
                pw.close();

                success = true;

            }
            if (!success) {
                // The HTTP request was invalid somehow, so we return a "bad request"
                // status code to the client.
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                // We are not sending a response body, so close the response body
                // output stream, indicating that the response is complete.
                httpExchange.getResponseBody().close();


                //ADD THE RESPONSE BODY AND ERROR INFO

            }
        }
        catch (IOException e) {
            // Some kind of internal error has occurred inside the server (not the
            // client's fault), so we return an "internal server error" status code
            // to the client.
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            // We are not sending a response body, so close the response body
            // output stream, indicating that the response is complete.
            httpExchange.getResponseBody().close();

            // Display/log the stack trace
            e.printStackTrace();
        }

        //String gsonResp = gson.toJson(regResponse);
//        PrintWriter pw = new PrintWriter(httpExchange.getResponseBody());
//        //pw.write(gsonResp);
//        pw.close();
    }

    /*
		The readString method shows how to read a String from an InputStream.
	*/
    private String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
}
