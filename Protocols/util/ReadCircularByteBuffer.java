package util;


public class ReadCircularByteBuffer {
    
    byte[] readbuffer;
    int readoffset, readcount;
    
    public ReadCircularByteBuffer(int cp){
        readbuffer = new byte[cp];
        readoffset = 0 ;
        readcount  = 0 ;
    }
    public synchronized int get( byte[] buffer , int offset , int count ) {
        
        int num_bytes_written_on_buffer = 0;
        
        //while buffer is empty:
        while(readcount==0){
            try {wait(); if(readcount==0) return -1;}
            catch( InterruptedException e) {e.printStackTrace();}
        }
        
        while(count>0 && readcount>0){
            buffer[offset] = readbuffer[readoffset%readbuffer.length];
            ++offset;
            ++readoffset;
            --readcount;
            --count;
            num_bytes_written_on_buffer++;
        }
        
        return num_bytes_written_on_buffer;
    }
    public synchronized void put( byte[] buffer , int offset , int count ) {
        for(int i=0; i<count; i++)
            readbuffer[(readoffset+readcount+i)%readbuffer.length] = buffer[offset+i];
        readcount+=count;
        notify();
    }
    public synchronized void wakeUpToClose(){
        notify();
    }
    public synchronized int available(){
        return readcount;
    }
}
