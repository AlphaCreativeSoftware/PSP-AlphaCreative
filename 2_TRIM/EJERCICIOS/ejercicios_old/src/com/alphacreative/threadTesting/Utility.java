package com.alphacreative.threadTesting;

public class Utility {

    public static void sleeep(int milisec)
    {
        try
        {
            Thread.sleep(milisec);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static void WaitForEnd(Thread t)
    {
        try
        {
            t.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}
