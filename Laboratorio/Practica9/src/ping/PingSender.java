package ping;

import java.nio.ByteOrder;
import channel.TSMessage;
import channel.ChannelIP;
import java.io.IOException;

public class PingSender extends Thread {

    ChannelIP forward_channel;
    ChannelIP return_channel;
    int tries;

    PingSender(ChannelIP forward_channel, ChannelIP return_channel, int tries) {
       this.forward_channel=forward_channel;
       this.return_channel=return_channel;
       this.tries = tries;
    }

    public void run() {

       int data_length = 255;
         
       TSMessage snd_msg, rcv_msg;
       
       String data_text;

       int snd_flags=0, snd_seqNum=0, snd_ackNum=0;

       System.out.println("PingSender Started");
       


         for (snd_seqNum= 0; snd_seqNum<tries; snd_seqNum++){ 
            data_text= "MSG->" + snd_seqNum;
            if (snd_seqNum==(tries-1)) snd_flags=TSMessage.FIN;
            snd_msg = new TSMessage(snd_flags, snd_seqNum, snd_ackNum, data_text.getBytes(), data_text.length());
            System.out.println("PingSender: sends msg num = "+ snd_msg.seqnum + " Length = " + data_text.length());
            forward_channel.send(snd_msg);

            try {   
            wait(10000);
            }
             catch (Exception ex) {
		//System.out.println(ex.getMessage());
                System.out.println("\nWait Exception\n");
	    }

            
            try {   
            wait(10000);
            }
             catch (Exception ex) {
		//System.out.println(ex.getMessage());
                System.out.println("\nWait Exception 2\n");
	    }


            rcv_msg = (TSMessage) return_channel.receive();
            String text= new String(rcv_msg.data);
            System.out.println("PingSender: receives msg num = " + rcv_msg.seqnum + " Data text ="+ text+ " Length = " + data_text.length());
            
            if (rcv_msg.seqnum!=snd_seqNum) {
                System.out.println("incorrect reply of msg = "+ snd_seqNum + ", received = " + rcv_msg.seqnum + " Data text ="+ text + " Length = " + data_text.length());
            }
       } 

      System.out.println("PingSender Finished");
      return;
     } 
 
}


