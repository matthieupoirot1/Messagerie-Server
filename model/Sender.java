package model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Sender
  implements Runnable
{
  private ObjectOutputStream out;
  private Server server;
  private Utilisateur utilisateur;

  public Sender(ObjectOutputStream out, Server server, Utilisateur utilisateur)
  {
    this.out = out;
    this.server = server;
    this.utilisateur = utilisateur;
  }

  public void run()
  {
    boolean isConnected = true;
    while (isConnected)
      try {
        this.out.writeObject(new ArrayList(this.server.getUtilisateurs()));
        this.out.flush();
        Thread.sleep(500L);
      } catch (IOException e) {
        isConnected = false;
        this.server.removeUser(this.utilisateur);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
  }
}