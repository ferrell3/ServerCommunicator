package Server;

import com.sun.net.httpserver.*;

import java.io.IOException;
import java.net.*;

import Handlers.ExecCommandHandler;
import Handlers.GCommandHandler;
import Handlers.ParseIntegerHandler;
import Handlers.ToLowerCaseHandler;
import Handlers.TrimHandler;

public class ServerCommunicator {
    private static final int MAX_WAITING_CONNECTIONS = 12;

    private HttpServer server;

    public static void main(String[] args) {
        String portNumber = args[0];
        new ServerCommunicator().init(portNumber);
    }

    private void init(String portNumber){
        System.out.println("Initializing HTTP server on port " + portNumber);

        try {
            server = HttpServer.create(new InetSocketAddress(Integer.parseInt(portNumber)), MAX_WAITING_CONNECTIONS);
        }catch (IOException e)
        {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null);

        System.out.println("Creating contexts");

        server.createContext("/tolowercase", new ToLowerCaseHandler());
        server.createContext("/parseinteger", new ParseIntegerHandler());
        server.createContext("/trim", new TrimHandler());
        server.createContext("/", new ExecCommandHandler());
        server.createContext("/G", new GCommandHandler());


        System.out.println("Starting server");

        server.start();

        System.out.println("Server started");

        // TEST - ServerTest1 branch test.
    }
}
