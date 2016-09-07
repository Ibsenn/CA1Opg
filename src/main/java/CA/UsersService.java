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

  static ArrayList<User> users = new ArrayList<>();
  private static ArrayList<IObserver> observers;
  public int usersOnline = 0;
  boolean b = true;

  public UsersService()
  {
    observers = new ArrayList<>();
  }

  public void run()
  {
    while (true)
    {
      if (usersOnline != users.size())
      //if(b)
      {
        System.out.println("inde");
        notifyObserver();
        usersOnline = users.size();
        //b = false;
      }
    }
  }

  public static void userLogin()
  {
    notifyObserver();
  }
  
  public void register(IObserver o)
  {
    observers.add(o);
  }

  public void unregister(IObserver deleteObserver)
  {
    System.out.println("Observer" + (deleteObserver) + "deleted");
    observers.remove(deleteObserver);
  }

  public static void notifyObserver()
  {
    for (IObserver observer : observers)
    {
      observer.update(users);
    }

  }
}
