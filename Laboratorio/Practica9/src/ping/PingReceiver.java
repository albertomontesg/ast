package ping;

import channel.TSMessage;
import channel.ChannelIP;


class PingReceiver extends Thread {

    ChannelIP forward_channel;
    ChannelIP return_channel;


    PingReceiver(ChannelIP forward_channel, ChannelIP return_channel) {
       this.forward_channel=forward_channel;
       this.return_channel=return_channel;
    }

    public void run() {
        TSMessage snd_msg, rcv_msg;

        System.out.println("PingReceiver Started");
        
        while(true){
            rcv_msg = (TSMessage) forward_channel.receive(); 	  
            System.out.println("PingReceiver: received msg num = " + rcv_msg.seqnum);
            return_channel.send(rcv_msg);      
            if(rcv_msg.fin) {
                System.out.println("PingReceiver: received FIN");
                break;
            }

    
         } 
      System.out.println("PingReceiver Finished");
 
       
    }
}
