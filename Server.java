import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class Server {
    public static int port = 2000;

    public static void main(String args[]) throws IOException {
        if(args.length == 1)
        {
            port = Integer.parseInt(args[0]);
        }
        new Server().RunServer();
    }

    public void RunServer() throws IOException{
        ServerSocket serversocket = new ServerSocket(port);
        System.out.println("Server (" + serversocket.getInetAddress().getLocalHost().getHostName()  + ") up and running ready for connections on port " + serversocket.getLocalPort());
        while (true) {
            Socket socket = serversocket.accept();
            new ServerThread(socket).start();
            
        }
    }
}