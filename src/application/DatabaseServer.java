package application;

import java.net.*;
import application.ServerThread;

public class DatabaseServer 
{
    public static void main(String[] args) 
    {
        new DatabaseServer();
    }

    public DatabaseServer() 
    {
        try 
        {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8888);
            
            // transfer clients' requests to a thread - to allow multiple clients
            while(true)
            {
                Socket socket = serverSocket.accept();
                ServerThread runnable = new ServerThread(socket);                
                Thread thread = new Thread(runnable);
                thread.start();
            }           
            
        }
        catch (Exception ex) 
        {
            ex.printStackTrace();
        } 
    }
}
