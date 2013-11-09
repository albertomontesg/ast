package channel ;

import java.nio.ByteBuffer;

public class TSMessage {
    
	// FLAGS_of_MESSAGE:
	public final static byte FIN = 0x01,
			SYN = 0x02 ,
			RST = 0x04 ,
			PSH = 0x08 ,
			ACK = 0x10;
	public byte fin;
	public byte syn;
	public byte rst;
	public byte psh;
	public byte ack;
	public int seqnum;
	public int acknum;
	public int checksum;
	public byte[] data; 
  
	public TSMessage(int flags, int seqnum, int acknum, byte[] data, int data_length){
		// Set all the flags
	    if( ( flags & TSMessage.FIN ) > 0 ) fin = 1;
	    if( ( flags & TSMessage.SYN ) > 0 ) syn = 1;
	    if( ( flags & TSMessage.RST ) > 0 ) rst = 1;
	    if( ( flags & TSMessage.PSH ) > 0 ) psh = 1;
	    if( ( flags & TSMessage.ACK ) > 0 ) ack = 1;
    
	    this.seqnum = seqnum;
	    this.acknum = acknum;

	    if(data_length!=0)
	    	System.arraycopy(data, 0, this.data, 0, data_length);
	}

	public void setError() {
		checksum = 1;
	}
	
}