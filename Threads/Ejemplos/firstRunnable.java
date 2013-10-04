import static java.lang.System.out;

class Child implements Runnable
{
  String msg;

  Child( String message )
  {
    msg = message;
  }

  public void run()
  {
    out.println("child: " + msg);
  }
}

class firstRunnable
{
  public static void main(String[] args) throws InterruptedException
  {
    Thread child = new Thread(new Child("I'm the child thread"));
    child.start();
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
