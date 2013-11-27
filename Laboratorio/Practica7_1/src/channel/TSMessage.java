package channel;

/**
 * @author Alberto Montes
 * @date 20-nov-2013
 * @subject AST
 * @exercise Practica7: Implementacio de Protocols // Sessio 1.1 Implementation
 *           of a reliable data transmission protocol
 */
public class TSMessage {

	// FLAGS_of_MESSAGE:
	public final static byte FIN = 0x01, SYN = 0x02, RST = 0x04, PSH = 0x08,
			ACK = 0x10;
	public boolean fin;
	public boolean syn;
	public boolean rst;
	public boolean psh;
	public boolean ack;
	public int seqnum;
	public int acknum;
	public int checksum;
	public byte[] data;

	public TSMessage(int flags, int seqnum, int acknum, byte[] data,
			int data_length) {
		// Set all the flags
		if ((flags & TSMessage.FIN) > 0)
			fin = true;
		if ((flags & TSMessage.SYN) > 0)
			syn = true;
		if ((flags & TSMessage.RST) > 0)
			rst = true;
		if ((flags & TSMessage.PSH) > 0)
			psh = true;
		if ((flags & TSMessage.ACK) > 0)
			ack = true;

		this.seqnum = seqnum;
		this.acknum = acknum;

		if (data_length != 0) {
			this.data = new byte[data_length];
			System.arraycopy(data, 0, this.data, 0, data_length);
		}
	}

	public void setError() {
		checksum = 1;
	}

	// Method to print a trace with the information of the packet
	@Override
	public String toString() {
		String st = "Flags: ";

		st += (ack) ? "1" : "0";
		st += (psh) ? "1" : "0";
		st += (rst) ? "1" : "0";
		st += (syn) ? "1" : "0";
		st += (fin) ? "1" : "0";

		st += " SeqNum: " + seqnum + " AckNum: " + acknum + " Checksum: "
				+ checksum;
		if (data != null) {
			try {
				st += " Data: " + (new String(data, "UTF-8"));
			} catch (Exception e) {
			}
			st += " Data size: " + data.length + " bytes";
		}

		return st;
	}

}