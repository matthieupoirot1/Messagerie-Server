package model;

import java.util.ArrayList;

public class Server
{
  private ArrayList<Utilisateur> utilisateurs;

  public Server()
  {
    this.utilisateurs = new ArrayList();

    waitForConnectionOnPort(2043);
  }

  public ArrayList<Utilisateur> getUtilisateurs() {
    return this.utilisateurs;
  }

  public void addUser(Utilisateur utilisateur) {
    this.utilisateurs.add(utilisateur);
  }

  public void removeUser(Utilisateur utilisateur) {
    this.utilisateurs.remove(utilisateur);
  }

  public boolean isConnected(Utilisateur utilisateur) {
    for (Utilisateur user : this.utilisateurs) {
      if ((utilisateur.getIpAdrr().equals(user.getIpAdrr())) || (utilisateur.getLogin().equals(user.getLogin()))) {
        return true;
      }
    }
    return false;
  }

  private void waitForConnectionOnPort(int port) {
    new Thread(new PortListener(port, this)).start();
  }
}