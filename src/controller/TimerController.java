package controller;

public class TimerController {
    private FarkleController farkleController;
    private int timer ;

    public TimerController(FarkleController farkleController) {
        this.farkleController = farkleController;
        timer = 30  ;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

}
