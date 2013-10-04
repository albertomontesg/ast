import static java.lang.System.out;

class firstAnonymous
{
  public static void main(String[] args) throws InterruptedException
  {
    final String msg = "I'm the child thread";
    
    new Thread()
    {
      /*String msg;

      Child( String message )
      {
        msg = message;
      }*/

      public void run()
      {
        out.println("child: " + msg);
      }
    }.start();

    //Thread child = new Child(/*"I'm the child thread"*/);
    //child.start();
    Thread.sleep(10);
    out.println("parent: I'm the parent thread");
    /*try
      {
        child.join();
      }
   catch (InterruptedException e)
     {
     }*/
  }
}
