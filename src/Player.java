public class Player {
    Player(){
    }
    public static void timerChange(){
        if (Button_Handler.whiteTurn) {
            GameTimer.timer1.start();
            GameTimer.timer2.stop();
        } else {
            GameTimer.timer1.stop();
            GameTimer.timer2.start();
        }
    }
}
