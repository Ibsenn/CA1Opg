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
import java.util.Scanner;

/**
 *
 * @author Mathias
 */
public class TCPServer
{

  static String ip;
  static int portNum;
  static ArrayList<User> users = new ArrayList<>();
  static ServerSocket ss;

  public static void main(String[] args) throws IOException
  {
    if (args.length == 2)
    {
      ip = args[0];
      portNum = Integer.parseInt(args[1]);
    }
    ss = new ServerSocket();
    ss.bind(new InetSocketAddress(ip, portNum));
    System.out.println("Server started - listening on port " + portNum + " bound to ip " + ip);
    while (true)
    {
      Socket link = ss.accept();
      System.out.println("New client connection");

      PrintWriter prnt = new PrintWriter(link.getOutputStream(), true);
      Scanner scn = new Scanner(link.getInputStream());

      Client c = new Client(link);
      c.start();
    }

//  public static void getTime(Socket s)
//  {
//    try
//    {
//      PrintWriter prnt = new PrintWriter(s.getOutputStream(), true);
//
//      SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
//      Date date = new Date();
//      prnt.println("Current time: " + dateFormat.format(date));
//
//      s.close();
//      prnt.close();
//    } catch (IOException ex)
//    {
//      Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
//    }
//
//  }
  }
}