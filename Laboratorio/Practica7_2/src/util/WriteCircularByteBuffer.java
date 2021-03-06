package util;

/**
 * @author Alberto Montes
 * @date 20-nov-2013
 * @subject AST
 * @exercise Practica7: Implementacio de Protocols // Sessio 1.2 Implementation
 *           of a reliable data transmission protocol
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
