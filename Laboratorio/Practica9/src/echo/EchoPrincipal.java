package echo;

import channel.ChannelIP;
import transport.Transport;

/**
 * @author Alberto Montes
 * @date 2-dec-2013
 * @subject AST
 * @exercise Practica8: Implementacio de Protocols // Part 2
 * 			Reliable transmission over a totally reliable channel
 */
public class EchoPrincipal {
    public static void main(String[] args){
        
        ChannelIP c1 = new ChannelIP(100);
        ChannelIP c2 = new ChannelIP(100);
        Transport t1 = new Transport(c1,c2);
        Transport t2 = new Transport(c2,c1);
        
        EchoServer server = new EchoServer(t1);
        EchoClient client = new EchoClient(t2);
        // Set name to the threads to print the trace
        server.setName("SERVER");
        client.setName("CLIENT");
        // Run threads
        client.start();
        server.start();
    }
}
