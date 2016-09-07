/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA;

import javafx.beans.Observable;

/**
 *
 * @author Mathias
 */
public class UsersService extends Thread implements Observable
{
  
  public int usersOnline;
  
  public void run()
  {
    while(true)
    {
      TCPServer.users.size();
    }
  }
  
  
}