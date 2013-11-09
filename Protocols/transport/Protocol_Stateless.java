package transport;

import channel.TSMessage;


/**
 *
 * @author juanluis
 */
public class Protocol_Stateless implements Protocol {
    
    Transport transport;
    
    public Protocol_Stateless(Transport transport){
        this.transport = transport;
    }
    public synchronized void connect() {
        //...
    }
    public synchronized void accept(){
        //...
    }
    public synchronized void sendData(byte[] data, int data_length) {
        //...
    }
    public synchronized void processReceivedMessage(TSMessage message) {
        //...
    }
    public synchronized void close() {
        //...
    }
    public synchronized void wakeUp(){
        notify();
    }
    public int SeqNum(){
        return 0;
    }
    public int AckNum(){
        return 0;
    }
}
