package Client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import Models.Command;
import Models.GenericCommand;
import Models.Request;
import Models.Results;

import static java.net.HttpURLConnection.HTTP_OK;

public class ClientCommunicator {

    private static ClientCommunicator myInstance = new ClientCommunicator();

    public static ClientCommunicator getInstance() { return myInstance; }

    private String serverHost = "192.168.2.169";
    private String serverPort = "8888";

    private ClientCommunicator() {}

    public Results send(String urlPath, Request reqInfo){
        String url = "http://" + serverHost + ":" + serverPort + "/" + urlPath;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonStr = gson.toJson(reqInfo);
        return post(url, jsonStr);
    }

    public Results sendCommand(Command command){
        String url = "http://" + serverHost + ":" + serverPort + "/";

        Gson gson = new GsonBuilder().create();
        String jsonStr = gson.toJson(command);
        return post(url, jsonStr);
    }

    public Results sendGenericCommand(GenericCommand command) {
        String url = "http://" + serverHost + ":" + serverPort + "/G";

        Gson gson = new GsonBuilder().create();
        String jsonStr = gson.toJson(command);
        return post(url, jsonStr);
    }

    private Results post(String urlAddress, String reqData)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Results response = new Results();
        try {
            URL url = new URL(urlAddress);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //REQUEST
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.connect();

            OutputStream reqBody = conn.getOutputStream();

            OutputStreamWriter sw = new OutputStreamWriter(reqBody);
            sw.write(reqData);
            sw.flush();

            reqBody.close();

            //RESPONSE
            if(conn.getResponseCode() == HTTP_OK)
            {
                response.setSuccess(true);
            }
            else
            {
                response.setSuccess(false);
                response.setErrorInfo("ERROR: " + conn.getResponseMessage());
            }

            Reader read = new InputStreamReader(conn.getInputStream());
            response = gson.fromJson(read, Results.class);
            read.close();
        } catch (Exception e) {
            response.setErrorInfo(e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }


}