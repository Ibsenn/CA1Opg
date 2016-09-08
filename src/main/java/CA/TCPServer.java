/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CA;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Mathias
 */
public class TCPServer
{

  static String ip = "localhost";
  static int portNum = 8080;
  Map<String, Client> clients = new HashMap();

  public void AddUser(String username, Client c)
  {
    clients.put(username, c);
    PrintClientList();
  }
  
  public void RemoveUser(String username, Client c)
  {
    clients.remove(username, c);
    PrintClientList();
  }

  public void PrintClientList()
  {
    String msg = "CLIENTLIST:";

    for (String name : clients.keySet())
    {
      msg += name + ",";
    }
    for (Client client : clients.values())
    {
      client.send(msg);
    }
  }
  
  public void SendMessage(String recievers, String message, String sender)
  {
    String msgRes = "MSGRES:" + sender + ":" + message;
    if (recievers.isEmpty())
    {
      for (Client client : clients.values())
      {
        client.send(msgRes);
      }
    } else
    {
      String[] recieversParts = recievers.split(",");
      for (Client client : clients.values())
      {
        for (int i = 0; i < recieversParts.length; i++)
        {
          if (client.equals(i))
          {
            client.send(msgRes);
          }
        }
      }
    }
  }

  public static void main(String[] args) throws IOException
  {
    System.out.println("server started");
    if (args.length == 2)
    {
      ip = args[0];
      portNum = Integer.parseInt(args[1]);
    }

    new TCPServer().StartServer();
  }

  public void StartServer() throws IOException
  {

    ServerSocket ss;
    ss = new ServerSocket();

    ss.bind(new InetSocketAddress(ip, portNum));
    System.out.println("Server started - listening on port " + portNum + " bound to ip " + ip);

    while (true)
    {
      Socket link = ss.accept();
      System.out.println("New client connection");

      Client c = new Client(link, this);
      c.start();
    }

  }
}
