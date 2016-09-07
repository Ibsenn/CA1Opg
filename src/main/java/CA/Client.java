package CA;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
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
public class Client extends Thread implements IObserver
{

  Socket link;

  static PrintWriter prnt;

  public Client(Socket link)
  {
    this.link = link;
  }

  public void run()
  {

    try
    {

      prnt = new PrintWriter(link.getOutputStream(), true);
      Scanner scn = new Scanner(link.getInputStream());

      prnt.println("Welcome to Echo. Please type a username and press enter");
      String msg = "";
      msg = scn.nextLine();
      if (msg.contains("LOGIN:"))
      {
        String[] parts = msg.split(":");
        String username = parts[1];
        UsersService.users.add(new User(username));
        UsersService.userLogin();

        //if()
        {
          prnt.println("You are connected as: " + username);
          while (!msg.contains("LOGOUT:"))
          {

          }
        }
      }

    } catch (IOException ex)
    {
      Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

  public void update(Observable o, Object arg)
  {

    String users = "CLIENTLIST:";

    for (int i = 0; i < UsersService.users.size(); i++)
    {

      users += UsersService.users.get(i).getUsername();

    }

    prnt.println(users);
  }

  @Override
  public void update(ArrayList<User> o)
  {
    String users = "CLIENTLIST:";
    
    for (int i = 0; i <UsersService.users.size(); i++)
    {
      users += UsersService.users.get(i).getUsername() + ", " ;

    }

    prnt.println(users);
  
  }
}
