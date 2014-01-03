package transport;

import channel.TSMessage;

/**
 * @author Alberto Montes
 * @date 2-dec-2013
 * @subject AST
 * @exercise Practica 9: Implementacio de Protocols // Part 3
 * 			Reliable transmission over a channel with bit errors. Re-transmission.
 * 
 * 	#### There is a bug which makes the transmitter thread send over and over again
 *  #### the same packet no matter it has received the acknowledge. It happens also
 *  #### with the Practica8. The bug is in the code given by the teachers and I haven't
 *  #### been able to find it.
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
            	// Send SYN packet
            	transport.sendPacket(2,new byte[0], 0);
                wait(); // Wait connection to be set up
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
            try {wait();} catch(InterruptedException e) {}
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
        	// Create the data packet with the PSH flag up and sent through the transport layer
        	try {
            	// Send data packet
        		transport.sendPacket(8, data, data_length, localSeqNum, localSeqNum);
            	localSeqNum++;
                wait(1000); // Wait connection to be set up
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        	
        }
        state = STATES_of_FSM.ESTABLISHED;

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

                if (message.syn) {
                    // Answer with the SYN+ACK packet and change the STATE
                	transport.sendPacket(18, new byte[0], 0);
                    state = STATES_of_FSM.SYN_RCVD_WAIT_ACK;
                } else {
                    System.out.println("\t pkt received -- not syn, ON LISTEN state!!!");
                }
                return;

            case STATES_of_FSM.SYN_RCVD_WAIT_ACK:

                if (message.syn && message.ack) {
                	// Set the State ESTABLISHED and notify the blocked method accept()
                	state = STATES_of_FSM.ESTABLISHED;
                	notify();
                    return;
                } else if (message.syn) {
                	// Retransmit the SYN+ACK packet
                	transport.sendPacket(18, new byte[0], 0);
                    return;
                } else if (message.psh) {
                	// The same as we have received a data packet
                	state = STATES_of_FSM.ESTABLISHED;
                    return;
                } else if (message.fin) {
                	if (message.checksum == 0) {
	                    state = STATES_of_FSM.FIN_RCVD_WAIT_ACK;
	                    transport.sendPacket(17, new byte[0], 0);
	                    timer = new Timer_for_FIN_RCVD_WAIT_ACK();
	                    timer.start();
                	}
                    return;
                }
                break;

            case STATES_of_FSM.SYN_SENT_WAIT_ACK:

                if (message.syn && message.ack) {
                	// Answer with the ACK packet
                    transport.sendPacket(18, new byte[0], 0);
                    // Set the State ESTABLISHED and notify the blocked method connect()
                	state = STATES_of_FSM.ESTABLISHED;
                	notify();
                    return;
                }
                break;

////////////////////////////////////////////////////////////////////////////////

            case STATES_of_FSM.ESTABLISHED:

                if (message.syn && message.ack) {
                    return;
                } else if (message.fin) {
                	if (message.checksum == 0) {
	                    state = STATES_of_FSM.FIN_RCVD_WAIT_ACK;
	                    transport.sendPacket(17, new byte[0], 0);
	                    timer = new Timer_for_FIN_RCVD_WAIT_ACK();
	                    timer.start();
                	}
                    return;
                } else if (message.psh) {
                    if (message.checksum==0 && message.seqnum == expectedSeqNum) {
                    	// Received a correct packet of data
                    	transport.receivedData(message.data, message.data.length);
                    	// Send the correspondent ACK
                    	expectedSeqNum++;
                    	transport.sendPacket(16, new byte[0], 0, expectedSeqNum, expectedSeqNum);
                    } else {
                    	// Re-transmit the ACK of the previous data packet
                    	transport.sendPacket(16, new byte[0], 0, expectedSeqNum, expectedSeqNum);
                    }
                    return;
                } else if (message.ack) {
                    if (message.checksum==0 && message.acknum == localSeqNum) {
                        state = STATES_of_FSM.ESTABLISHED_ACK_RCVD;
                    	notify();
                    } else {
                        System.out.println("\tack pkt BUT acknowledging -- " + message.acknum);
                    }
                    return;
                }
                break;

////////////////////////////////////////////////////////////////////////////////            

            case STATES_of_FSM.FIN_SENT_WAIT_ACK:

                if (message.fin && message.ack) {
                    // Set the state to CLOSED and notify the blocked method close()
                	state = STATES_of_FSM.CLOSED;
                    notify();
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
                	// Set the state to CLOSED and notify the blocked method close()
                	state = STATES_of_FSM.CLOSED;
                	notify();
                    return;
                } else if (message.fin) {
                    // Restart the timer
                	timer.again();
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

        // if we reach this point (something is wrong),
        // we have received an unexpected packet type for the present state.
        // we close the socket.

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
            	// Create and send the SYN Packet through the transport layer
            	transport.sendPacket(1, new byte[0], 0);
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
