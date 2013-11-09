package echo;

import channel.ChannelIP;
import transport.Transport;

/**
 *
 * @author juanluis
 */
public class EchoPrincipal {
    public static void main(String[] args){
        
        ChannelIP c1 = new ChannelIP(100);
        ChannelIP c2 = new ChannelIP(100);
        Transport t1 = new Transport(c1,c2);
        Transport t2 = new Transport(c2,c1);
        
        new EchoServer(t1).start();
        new EchoClient(t2).start();
    }
}
