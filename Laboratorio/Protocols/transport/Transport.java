/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package transport;

import channel.ChannelIP;
import channel.TSMessage;

/**
 *
 * @author PCprofe
 */
public class Transport {

    boolean serverSocket;
    MySocket socket;
    ChannelIP tx_channel, rx_channel;
    Thread_Transmitter threadTX;
    Thread_Receiver threadRX;
    Protocol protocol;

    public Transport(ChannelIP tx_channel, ChannelIP rx_channel) {

        this.tx_channel = tx_channel;
        this.rx_channel = rx_channel;

        protocol = new Protocol_Stateless(this);
//        protocol = new Protocol_StopAndWait(this);
    }

    void start_TX_RX_threads() {
        threadTX = new Thread_Transmitter(this);
        threadTX.start();
        threadRX = new Thread_Receiver(this);
        threadRX.start();
    }

     void sendPacket(int flags, byte[] data, int data_length) {
        tx_channel.send(new TSMessage(flags, protocol.SeqNum(), protocol.AckNum(), data, data_length));
    }

    void sendPacket(int flags, byte[] data, int data_length, int seqNum, int ackNum) {
        tx_channel.send(new TSMessage(flags, seqNum, ackNum, data, data_length));
    }

    void receivedPacket(TSMessage tsmessage) {
        // if I'm waiting for new connections but it's not a syn pkt, return
        if (serverSocket && tsmessage.syn != 1) {
            return;
        }
        // if I'm waiting for new connections and it's a syn pkt, perfect!!!
        if (serverSocket && tsmessage.syn == 1) {
            serverSocket = false;
            System.out.println("from now on, serverSocket NOT AVAILABLE for new connections!!!");
        }
        protocol.processReceivedMessage(tsmessage);
    }

    void sendData(byte[] data, int data_length) {
        protocol.sendData(data, data_length);
        socket.outputStream.forward(data_length);
    }

    void receivedData(byte[] data, int data_length) {
        socket.inputStream.write(data, 0, data.length);
    }

    void close() {
        threadRX.terminate();
        threadTX.terminate();
        socket.inputStream.wakeUpToClose();
        protocol.wakeUp();
        System.out.println("transport closed");
    }
}
