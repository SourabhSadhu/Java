/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 
 * @author souravn
 */
public class ListnerServerDFS extends Thread
{
    private ServerSocket serverSocket;
    private int port;
    private boolean running = false;

    public ListnerServerDFS( int port )
    {
        this.port = port;
    }

    public void startServer()
    {
        try
        {
            serverSocket = new ServerSocket( port );
            this.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void stopServer()
    {
        running = false;
        this.interrupt();
    }

    @Override
    public void run()
    {
        running = true;
        while( running )
        {
            try
            {
                System.out.println( "Listening for a connection" );

                // Call accept() to receive the next connection
                Socket socket = serverSocket.accept();

                // Pass the socket to the RequestHandler thread for processing
                RequestHandler requestHandler = new RequestHandler( socket );
                requestHandler.start();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main( String[] args )
    {
        
        int port = Integer.parseInt( "8181" );
        System.out.println( "Start server on port: " + port );

        ListnerServerDFS server = new ListnerServerDFS( port );
        server.startServer();

        // Automatically shutdown in 1 minute
        try
        {
            Thread.sleep( 6000000 );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }

        server.stopServer();
    }
}

