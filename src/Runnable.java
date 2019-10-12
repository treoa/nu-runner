public class Runnable implements java.lang.Runnable {
    public NURunner nuRunner;

    Runnable (NURunner runner){
        super();
        this.nuRunner = runner;
    }

    @Override
    public void run() {
        nuRunner.window.setVisible(true);
    }
}
