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

  public Client(Socket link)
  {
    this.link = link;
  }

  public void run()
  {

    try
    {
      PrintWriter prnt = new PrintWriter(link.getOutputStream(), true);
      Scanner scn = new Scanner(link.getInputStream());

      prnt.println("Welcome to Echo. Please type a username and press enter");
      String msg = "";
      msg = scn.nextLine();
      if (msg.contains("LOGIN:"))
      {
        String[] parts = msg.split(":");
        String username = parts[1];

        //if()
        {
        prnt.println("You are connected as: " + username);
          while(!msg.contains("LOGOUT:"))
          {
            
          }
        }
        
      }

    } catch (IOException ex)
    {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }

  }
}