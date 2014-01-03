package ping;

import channel.ChannelIP;
import transport.Transport;


public class PingPrincipal {
    public static void main(String[] args){      

        ChannelIP fw = new ChannelIP(100,1);
        ChannelIP rtn = new ChannelIP(100,2);
        PingReceiver p_rcv = new PingReceiver(fw,rtn);
        PingSender p_snd = new PingSender(fw,rtn,5);      
 try {       
        p_rcv.start();
        p_snd.start();
    }
  catch (Exception ex) {
		System.out.println(ex.getMessage());
                System.out.println("\nThreads may be alive\n");
	}
  finally {
        try{
             p_rcv.join();
             p_snd.join();
        }catch(InterruptedException ignorar){}
  }
 
   System.out.println("\nPing Test Program Terminated\n");
  }

 } 

