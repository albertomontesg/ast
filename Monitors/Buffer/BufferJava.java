class BufferJava
  {
    private Queue q;

    public BufferJava(int s)
    {
      q = new Queue(s);
    }

    public synchronized void put(Object value)
    {
      while (q.isFull())
        try
          {
            wait();
          }
        catch(InterruptedException e)
          {
          }
      q.put(value);
      notifyAll();
    }

    public synchronized Object get()
    {
      while (q.isEmpty())
        try
          {
            wait();
          }
        catch(InterruptedException e)
          {
          }
      Object result = q.get();
      notifyAll();
      return result;
    }
  }