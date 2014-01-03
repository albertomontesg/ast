package transport;

import channel.TSMessage;
import java.util.ArrayList;
import util.Modular;

/**
 * @author Alberto Montes
 * @date 2-dec-2013
 * @subject AST
 * @exercise Practica8: Implementacio de Protocols // Part 2
 * 			Reliable transmission over a totally reliable channel
 */
public class Protocol_GoBackN implements Protocol {

    int state;
    Transport transport;
    int localSeqNum, expectedSeqNum, acknowledgedSeqNum = -1;
    Timer_for_FIN_RCVD_WAIT_ACK timer;
    Buffer_Timer_for_Retransmitions timerACK;
    int base, windowSize;
    int id;

    public Protocol_GoBackN(Transport socket, int windowSize, int id) {
        state = STATES_of_FSM.CLOSED;
        this.transport = socket;
        this.windowSize = windowSize;
        Modular.n = windowSize + 1;
        this.id = id;
        timerACK = new Buffer_Timer_for_Retransmitions();
        timerACK.start();
    }

    public synchronized void connect() {
        if (state != STATES_of_FSM.CLOSED) {
            System.out.println("Active Open only permitted from CLOSED state");
            return;
        }

        state = STATES_of_FSM.SYN_SENT_WAIT_ACK;

        while (state != STATES_of_FSM.ESTABLISHED) {
            try {
                //...
                wait(1000); //wait connection to be set up
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public synchronized void accept() {
        if (state != STATES_of_FSM.CLOSED) {
            System.out.println("Passive Open only permitted from CLOSED state");
            return;
        }

        state = STATES_of_FSM.LISTEN;

        while (state != STATES_of_FSM.ESTABLISHED) {
            //...
        }
    }

    public synchronized void sendData(byte[] data, int data_length) {

        while (state == STATES_of_FSM.ESTABLISHED /* && ... */) {
           //..
        }
        if (state != STATES_of_FSM.ESTABLISHED) {
            return;
        }
        //...
        
        timerACK.put(new BufferData(data, data_length, localSeqNum, acknowledgedSeqNum));
        if (base == localSeqNum) {
            //...
        }
        //...
    }

    public synchronized void processReceivedMessage(TSMessage message) {
        switch (state) {

            case STATES_of_FSM.CLOSED:
                break;

////////////////////////////////////////////////////////////////////////////////            

            case STATES_of_FSM.LISTEN:

                if (message.syn) {
                    //...
                } else {
                    System.out.println("\t pkt received -- not syn, ON LISTEN state!!!");
                }
                return;

            case STATES_of_FSM.SYN_RCVD_WAIT_ACK:

                if (message.syn && message.ack) {
                    //...
                    return;
                } else if (message.syn) {
                    //...
                    return;
                } else if (message.psh) {
                    //...
                    return;
                } else if (message.fin) {
                    //...
                    return;
                }
                break;

            case STATES_of_FSM.SYN_SENT_WAIT_ACK:

                if (message.syn && message.ack) {
                    //...
                    return;
                }
                break;

////////////////////////////////////////////////////////////////////////////////

            case STATES_of_FSM.ESTABLISHED:

                if (message.syn && message.ack) {
                    return;
                } else if (message.fin) {
                    //...
                    return;
                } else if (message.psh) {
                    if (message.checksum==0 && message.seqnum == expectedSeqNum) {
                        //...
                    } else {
                        //...
                    }
                    return;
                } else if (message.ack) {
                    //...
                    return;
                }
                break;

////////////////////////////////////////////////////////////////////////////////            

            case STATES_of_FSM.FIN_SENT_WAIT_ACK:

                if (message.fin && message.ack) {
                    //...
                    return;
                } else if (message.psh) {
                    System.out.println("\tdata pkt received -- " + message.seqnum + " discarded we are closing...");
                    return;
                } else if (message.ack) {
                    System.out.println("\tack pkt received -- " + message.acknum + " discarded we are closing...");
                    return;
                }
                break;

            case STATES_of_FSM.FIN_RCVD_WAIT_ACK:

                if (message.fin && message.ack) {
                    //...
                    return;
                } else if (message.fin) {
                    //...
                    return;
                } else if (message.psh) {
                    System.out.println("\tdata pkt received -- " + message.seqnum + " discarded we are closing...");
                    return;
                } else if (message.ack) {
                    System.out.println("\tack pkt received -- " + message.acknum + " discarded we are closing...");
                    return;
                } else if (message.syn && message.ack) {
                    state = STATES_of_FSM.CLOSED;
                    transport.close();
                    return;
                } else if (message.syn) {
                    state = STATES_of_FSM.CLOSED;
                    transport.close();
                    return;
                }
                break;

        }

////////////////////////////////////////////////////////////////////////////////    

        //if we reach this point (something is wrong),
        //we have received an unexpected packet type for the present state.
        //we close the socket.

        if (!(message.rst)) {
            System.out.println("ReSeT from state: " + STATES_of_FSM.statelabels[state]);
            System.out.println("rst pkt sent -- ");
            System.out.println("Connection reset by me");
            transport.sendPacket(TSMessage.RST, null, 0);
        } else {
            System.out.println("\trst pkt received -- closing the socket...");
            System.out.println("Connection reset by peer");
        }

        state = STATES_of_FSM.CLOSED;
        transport.close();
    }

    public synchronized void close() {
        if (state != STATES_of_FSM.ESTABLISHED) {
            System.out.println("close action only permitted from ESTABLISHED state");
            return;
        }

        state = STATES_of_FSM.FIN_SENT_WAIT_ACK;

        while (state != STATES_of_FSM.CLOSED) {
            try {
                //...
                wait(1000); //wait connection to be closed
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }

        transport.close();
    }

    public synchronized void wakeUp() {
        timerACK.stopTimer();
        notify();
    }

    public int SeqNum() {
        return localSeqNum;
    }

    public int AckNum() {
        return acknowledgedSeqNum;
    }

    class BufferData {

        byte[] data;
        int data_length;
        int seqNum;
        int ackNum;

        public BufferData(byte[] data, int data_length, int seqNum, int ackNum) {
            this.data = copy(data);
            this.data_length = data_length;
            this.seqNum = seqNum;
            this.ackNum = ackNum;
        }

        public byte[] copy(byte[] data_tmp) {
            return data_tmp.clone();
        }
    }

    class Timer_for_FIN_RCVD_WAIT_ACK extends Thread {

        boolean again;

        public void run() {
            waitAndClose();
        }

        private synchronized void waitAndClose() {
            do {
                again = false;
                try {
                    wait(5000);
                } catch (Exception e) {
                }
            } while (again);
            state = STATES_of_FSM.CLOSED;
            transport.close();
        }

        public synchronized void again() {
            again = true;
        }
    }

    class Buffer_Timer_for_Retransmitions extends Thread {

        boolean stop = false;
        boolean retransmetre = true;
        ArrayList<BufferData> buffer = new ArrayList<BufferData>();

        @Override
        public synchronized void run() {
            while (!stop) {
                retransmetre = true;
                esperar();
                if (retransmetre) {
                    retransmit();
                }
            }

        }

        private synchronized void esperar() {

            try {
                if (buffer.isEmpty()) {
                    wait();
                } else {
                    wait(100 * windowSize);
                }

            } catch (Exception e) {
            }
        }

        public synchronized void put(BufferData o) {
            buffer.add(o);
        }

        public synchronized BufferData get() {
            return (BufferData) buffer.get(0);
        }

        public synchronized BufferData remove() {
            return (BufferData) buffer.remove(0);
        }

        public synchronized void retransmit() {
            for (int i = 0; i < buffer.size(); i++) {

                BufferData bd = buffer.get(i);
                if (state != STATES_of_FSM.CLOSED
                        && state != STATES_of_FSM.FIN_RCVD_WAIT_ACK
                        && state != STATES_of_FSM.FIN_SENT_WAIT_ACK) {
                    transport.sendPacket(TSMessage.PSH, bd.data, bd.data_length, bd.seqNum, bd.ackNum);
                }
            }
        }

        public synchronized void again() {
            retransmetre = false;
            notify();
        }

        public synchronized void stopTimer() {
            retransmetre = false;
            stop = true;
            buffer.clear();
            notify();
        }
    }
}
