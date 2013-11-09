
package transport;

public class Thread_Transmitter extends Thread {
    
  static final int MIDA_DADES_A_TRANSMETRE = 5;

  Transport transport;
  boolean alive;
  byte[] data;
 	
  Thread_Transmitter(Transport tr){
    alive = true ;
    this.transport = tr;
    data = new byte[MIDA_DADES_A_TRANSMETRE];
  }
  public void run() {
      //mentres sigui viu, esperar a tenir dades del outputstream del socket per
      //transmetre, comunicar aquestes dades a la capa de transport perque inicii
      //la transmissio
      //...
  }
  public void terminate() {
      //fer que el fil d'execucio de la classe mori
      //...
      //...
  }

}

