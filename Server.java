import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static int port = 2000;
    public static List<ServerThread> serverThreads;

    public static void main(String args[]) throws IOException {
        if(args.length == 1)
        {
            port = Integer.parseInt(args[0]);
        }
        new Server().RunServer();
    }
    public static void broadcastMsg(String message)
    {
        for(int x = 0; x < serverThreads.size(); x++)
        {
            try
            {
            Socket currsocket = serverThreads.get(x).socket;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(currsocket.getInputStream()));
            PrintWriter out = new PrintWriter(currsocket.getOutputStream(), true);

            out.println(message);            
        }
        catch(IOException ioe)
        {
            System.out.println(ioe.getStackTrace());
        }
    }
    }

    public void RunServer() throws IOException{
        ServerSocket serversocket = new ServerSocket(port);
        System.out.println("Server (" + serversocket.getInetAddress().getLocalHost().getHostName()  + ") up and running ready for connections on port " + serversocket.getLocalPort());
        serverThreads = new ArrayList<ServerThread>();
        while (true) {
            Socket socket = serversocket.accept();
            serverThreads.add(new ServerThread(socket));
            System.out.println(serverThreads.size() + " connected");
            for(int x = 0; x < serverThreads.size(); x++)
            {
                Socket currsocket = serverThreads.get(x).socket;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(currsocket.getInputStream()));
                PrintWriter out = new PrintWriter(currsocket.getOutputStream(), true);
                out.println("Welcome to the server.");            
            }
        }
    }
}