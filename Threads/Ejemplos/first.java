import static java.lang.System.out;

class Child extends Thread
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

class first extends Thread
{
  public static void main(String[] args) throws InterruptedException
  {
    Thread child = new Child("I'm the child thread");
    child.start();
    /*Thread.*/sleep(10);
    out.println("parent: I'm the parent thread");
//    child.join();
  }
}
