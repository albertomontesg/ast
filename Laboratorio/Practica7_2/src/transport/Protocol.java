package transport;

import channel.TSMessage;


/**
 * @author Alberto Montes
 * @date 20-nov-2013
 * @subject AST
 * @exercise Practica7: Implementacio de Protocols // Sessio 1.2 Implementation
 *           of a reliable data transmission protocol
 */
public interface Protocol {
	void connect();
	void accept();
	void sendData(byte[] data, int data_length);
	void processReceivedMessage(TSMessage message);
	void close();
    
	void wakeUp();  //wake up any Thread waiting within the FSM
	int SeqNum();
	int AckNum();
}
class STATES_of_FSM {

	// STATES_of_FSM:
	final static int  UNSET = 0,
					CLOSED = 1,
                    LISTEN = 2,
                    SYN_SENT_WAIT_ACK = 3,
                    SYN_RCVD_WAIT_ACK = 4,
                    ESTABLISHED = 5,
                    ESTABLISHED_ACK_RCVD = 6,
                    FIN_SENT_WAIT_ACK = 7,
                    FIN_RCVD_WAIT_ACK = 8;
  
	// LABELS_of_STATES_of_FSM:                          
	final static String[] statelabels = {"UNSET",
										"CLOSED",
										"LISTEN",
										"SYN_SENT_WAIT_ACK",
										"SYN_RCVD_WAIT_ACK",
										"ESTABLISHED",
										"ESTABLISHED_ACK_RCVD",
										"FIN_SENT_WAIT_ACK",
										"FIN_RCVD_WAIT_ACK"};
}
