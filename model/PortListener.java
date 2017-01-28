package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PortListener
  implements Runnable
{
  private ServerSocket serverSocket;
  private Socket socket;
  private Server server;

  public PortListener(int port, Server server)
  {
    try
    {
      this.serverSocket = new ServerSocket(port);
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.server = server;
  }

  public void run()
  {
    while (true)
      try {
        this.socket = this.serverSocket.accept();
        ObjectOutputStream out = new ObjectOutputStream(this.socket.getOutputStream());
        out.flush();
        ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());

        Utilisateur utilisateur = (Utilisateur)in.readObject();
        utilisateur.setIpAdrr(this.socket.getRemoteSocketAddress().toString().split(":")[0].substring(1));

        if (!this.server.isConnected(utilisateur)) {
          this.server.addUser(utilisateur);
          new Thread(new Sender(out, this.server, utilisateur)).start();
        }
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
  }
}