/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Mathias
 */
interface IObserver
{
  public void update(ArrayList<User> o);
}
