/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soketserver;


import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


class RequestHandler extends Thread
{
    private Socket socket;
    RequestHandler( Socket socket )
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        {
            System.out.println( "Received a connection" );
            DataInputStream is = null;
            // Get input and output streams
            BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
            PrintWriter out = new PrintWriter( socket.getOutputStream() );
            is = new DataInputStream(socket.getInputStream());
            // Write out our header to the client
           // out.println( "Echo Server 1.0" );
           // out.flush();

            // Echo lines back to the client until the client closes the connection or we receive an empty line
            String thisLine;
            System.out.println("Message Received 8181: ");
            while ((thisLine = in.readLine()) != null) {
            System.out.println(thisLine);
         } 
            

            // Close our connection
            in.close();
            out.close();
            socket.close();

            System.out.println( "Connection closed" );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}