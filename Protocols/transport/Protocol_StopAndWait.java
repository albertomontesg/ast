package transport;

import channel.TSMessage;

/**
 *
 * @author juanluis
 */
public class Protocol_StopAndWait implements Protocol {

    int state;
    Transport transport;
    int localSeqNum, expectedSeqNum, acknowledgedSeqNum;
    Timer_for_FIN_RCVD_WAIT_ACK timer;

    public Protocol_StopAndWait(Transport socket) {
        state = STATES_of_FSM.CLOSED;
        this.transport = socket;
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

        //Presently the state is STATES_of_FSM.ESTABLISHED,
        //while state != STATES_of_FSM.ESTABLISHED_ACK_RCVD the data is
        //sent but the ack has not been received, we do retry:

        while (state != STATES_of_FSM.ESTABLISHED_ACK_RCVD
                && state != STATES_of_FSM.CLOSED
                && state != STATES_of_FSM.FIN_RCVD_WAIT_ACK
                && state != STATES_of_FSM.FIN_SENT_WAIT_ACK) {
            //...
        }

        //we've got out of the while due to the socket closing...
        //so we get out of here:
        if (state == STATES_of_FSM.CLOSED
                || state == STATES_of_FSM.FIN_RCVD_WAIT_ACK
                || state == STATES_of_FSM.FIN_SENT_WAIT_ACK) {

            return;
        }

        state = STATES_of_FSM.ESTABLISHED;
    }

    public synchronized void processReceivedMessage(TSMessage message) {

        switch (state) {

            case STATES_of_FSM.CLOSED:
                break;

////////////////////////////////////////////////////////////////////////////////            

            case STATES_of_FSM.LISTEN:

                if (message.syn == 1) {
                    //...
                } else {
                    System.out.println("\t pkt received -- not syn, ON LISTEN state!!!");
                }
                return;

            case STATES_of_FSM.SYN_RCVD_WAIT_ACK:

                if (message.syn == 1 && message.ack == 1) {
                    //...
                    return;
                } else if (message.syn == 1) {
                    //...
                    return;
                } else if (message.psh == 1) {
                    //...
                    return;
                } else if (message.fin == 1) {
                    //...
                    return;
                }
                break;

            case STATES_of_FSM.SYN_SENT_WAIT_ACK:

                if (message.syn == 1 && message.ack == 1) {
                    //...
                    return;
                }
                break;

////////////////////////////////////////////////////////////////////////////////

            case STATES_of_FSM.ESTABLISHED:
            case STATES_of_FSM.ESTABLISHED_ACK_RCVD:

                if (message.syn == 1 && message.ack == 1) {
                    return;
                } else if (message.fin == 1) {
                    //...
                    return;
                } else if (message.psh == 1) {
                    if (message.checksum==0 && message.seqnum == expectedSeqNum) {
                        //...
                    } else {
                        //...
                    }
                    return;
                } else if (message.ack == 1) {
                    if (message.checksum==0 && message.acknum == localSeqNum) {
                        //...
                    } else {
                        System.out.println("\tack pkt BUT acknowledging -- " + message.acknum);
                    }
                    return;
                }
                break;

////////////////////////////////////////////////////////////////////////////////            

            case STATES_of_FSM.FIN_SENT_WAIT_ACK:

                if (message.fin == 1 && message.ack == 1) {
                    //...
                    return;
                } else if (message.psh == 1) {
                    System.out.println("\tdata pkt received -- " + message.seqnum + " discarded we are closing...");
                    return;
                } else if (message.ack == 1) {
                    System.out.println("\tack pkt received -- " + message.acknum + " discarded we are closing...");
                    return;
                }
                break;

            case STATES_of_FSM.FIN_RCVD_WAIT_ACK:

                if (message.fin == 1 && message.ack == 1) {
                    //...
                    return;
                } else if (message.fin == 1) {
                    //...
                    return;
                } else if (message.psh == 1) {
                    System.out.println("\tdata pkt received -- " + message.seqnum + " discarded we are closing...");
                    return;
                } else if (message.ack == 1) {
                    System.out.println("\tack pkt received -- " + message.acknum + " discarded we are closing...");
                    return;
                } else if (message.syn == 1 && message.ack == 1) {
                    state = STATES_of_FSM.CLOSED;
                    transport.close();
                    return;
                } else if (message.syn == 1) {
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

        if (!(message.rst == 1)) {
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
        notify();
    }

    public int SeqNum() {
        return localSeqNum;
    }

    public int AckNum() {
        return acknowledgedSeqNum;
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
}
