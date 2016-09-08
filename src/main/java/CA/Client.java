package CA;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mathias
 */
public class Client extends Thread
{

  Socket link;

  PrintWriter prnt;
  String username;
  TCPServer server;

  public void send(String msg)
  {
    prnt.println(msg);
  }

  public Client(Socket link, TCPServer server)
  {
    this.server = server;
    this.link = link;
  }

  public void run()
  {

    try
    {

      prnt = new PrintWriter(link.getOutputStream(), true);
      Scanner scn = new Scanner(link.getInputStream());

      String msg = "";
      msg = scn.nextLine();
      if (msg.contains("LOGIN:"))
      {
        String[] parts = msg.split(":");
        username = parts[1];
        server.AddUser(username, this);

        while (!msg.contains("LOGOUT:"))
        {
          msg = scn.nextLine();
          
          if(msg.contains("MSG:"))
          {
            String[] msgParts = msg.split(":");
            String recievers = msgParts[1];
            String message = msgParts[2];
            server.SendMessage(recievers, message, username);
          }
        }
        server.RemoveUser(username, this);
        System.out.println("logged out");
        link.close();

      }

    } catch (IOException ex)
    {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }

  }
  

}
