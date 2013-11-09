
package transport ;

import channel.TSMessage;

public class Thread_Receiver extends Thread {

  Transport transport ;
  boolean alive ;
  
  Thread_Receiver(Transport tr){
    alive = true ;
    this.transport = tr;
  }
  public void run() {
      //mentres sigui viu, esperar a rebre un paquet nou de la capa IP,
      //comunicar el paquet rebut a la capa de transport.
      //...
  }
  public void terminate() {
      //fer que el fil d'execucio de la classe mori
      //...
  }

}

