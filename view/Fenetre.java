package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Fenetre extends JFrame
{
  public Fenetre()
  {
    setTitle("Serveur");
    setSize(700, 500);
    setLocationRelativeTo(null);
    setResizable(false);
    setDefaultCloseOperation(3);
    setContentPane(buildContentPane());
    setVisible(true);
  }

  private Container buildContentPane() {
    Container panel = new Container();
    panel.setLayout(new FlowLayout());
    panel.setBackground(Color.white);

    String ip = "<html>";

    Box boite = Box.createVerticalBox();

    Enumeration e = null;
    try {
      e = NetworkInterface.getNetworkInterfaces();
    } catch (SocketException e1) {
      e1.printStackTrace();
    }

    while (e.hasMoreElements())
    {
      NetworkInterface n = (NetworkInterface)e.nextElement();
      Enumeration ee = n.getInetAddresses();
      while (ee.hasMoreElements())
      {
        InetAddress i = (InetAddress)ee.nextElement();
        JLabel label = new JLabel("<html>" + i.getHostAddress() + "</br></html>");
        boite.add(label);
      }
    }

    boite.add(Box.createVerticalGlue());
    setLocationRelativeTo(getParent());
    panel.add(boite);

    return panel;
  }
}