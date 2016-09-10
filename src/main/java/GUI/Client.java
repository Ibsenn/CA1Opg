/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Mathias
 */
public class Client
{

  private static Client instance = null;
  String ip;
  int port;
  BufferedReader br;
  PrintWriter prnt;
  private Socket socket;


  private Client()
  {

  }

  public static Client getInstance()
  {
    if (instance == null)
    {
      instance = new Client();
    }
    return instance;
  }

  public void connect(String ip, int port)
  {
    this.ip = ip;
    this.port = port;
    {
      try
      {
        socket = new Socket(ip, port);
      } catch (Exception ex)
      {
        System.out.println(ex);
      }
    }
  }
  
  public Socket getSocket()
  {
    return socket;
  }

  public PrintWriter getPrintWriter()
  {
    return prnt;
  }

  public BufferedReader getBr()
  {
    return br;
  }
}
