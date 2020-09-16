package hiberL4;

public class ThreadUtil {

    public static void uncheckedSleep(long seconds){
        try {
            System.out.println("Pause " + seconds + " sec");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
