
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class ServerThread extends Thread {
    Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
        this.start();
 
    }
   

    public synchronized void run() {
        if (!this.socket.isClosed() && this.socket.isConnected()) {
            String ipadress = "a client";
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                String message = null;
                while ((message = bufferedReader.readLine()) != null) {
                    ipadress = this.socket.getLocalAddress().toString();

                    // Write to server what people write
                    System.out.println(this.socket.getLocalAddress() + ": " + message);

                    // Post it to everyone
                    Server.broadcastMsg(message);
                }
                bufferedReader.close();
                this.socket.close();
                
            } catch (IOException ioe) {
                //ioe.printStackTrace();
                System.out.println(ipadress + " has disconnected");
            }
        }
    }
}