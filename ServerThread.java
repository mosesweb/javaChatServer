import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class ServerThread extends Thread {
    Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public synchronized void run() {
        if (!this.socket.isClosed() && this.socket.isConnected()) {
            String ipadress = "a client";
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = null;
                while ((message = bufferedReader.readLine()) != null) {
                    ipadress = this.socket.getLocalAddress().toString();
                    System.out.println(this.socket.getLocalAddress() + ": " + message);
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