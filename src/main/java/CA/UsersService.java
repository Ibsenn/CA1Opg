/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 *
 * @author Mathias
 */
public class UsersService extends Observable implements Runnable
{

  private ArrayList<Observer> observers;
  public int usersOnline;

  public UsersService()
  {
    observers = new ArrayList<Observer>();
  }

  public void run()
  {
    while (true)
    {
      if (usersOnline != TCPServer.users.size())
      {
        notifyObserver();
        usersOnline = TCPServer.users.size();

      }
    }

  }


  public void register(Observer o)
  {
    observers.add(o);
  }

  public void unregister(Observer deleteObserver)
  {
    System.out.println("Observer" + (deleteObserver) + "deleted");
    observers.remove(deleteObserver);
  }

  public void notifyObserver()
  {
    for (Observer observer : observers)
    {
      observer.update(this, observers);
    }

  }
}
