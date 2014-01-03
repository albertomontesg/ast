package util;

/**
 * @author Alberto Montes
 * @date 2-dec-2013
 * @subject AST
 * @exercise Practica8: Implementacio de Protocols // Part 2
 * 			Reliable transmission over a totally reliable channel
 */
public class WriteCircularByteBuffer {
    
    byte[] writebuffer;
    int writestart;
    int writeend;
    boolean exit;
    
    public WriteCircularByteBuffer(int cp){
        writebuffer = new byte[cp];
        writestart = 0;
        writeend = 0;
        exit = false;
    }
    
    public synchronized void put(byte[] buffer , int offset , int count) {
        
        for(int i=0; i<count; i++){
            writebuffer[writeend++] = buffer[offset++];
            writeend %= writebuffer.length ;
            if( writeend == writestart )
                System.out.println("WriteBuffer overflow!!!!!");
        }
        notify();
    }
    
    public synchronized int get(byte[] buffer , int offset , int count) {
        
        //while buffer is empty:
        while(writestart==writeend)
            try{wait(); if(exit) return -1;}
            catch(Exception e){e.printStackTrace();}
        
        int pointer = writestart;
        for(int i=0; i<count; i++){
            buffer[offset++] = writebuffer[pointer ++];
            pointer %= writebuffer.length;
            if( pointer == writeend ){
                return i+1;
            }
        }
        return count;
    }
    
    public synchronized void wakeUpToClose(){
        exit = true;
        notify();
    }
    public synchronized void forward(int count){
        writestart = (writestart+count)%writebuffer.length;
    }
}
