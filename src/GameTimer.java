import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameTimer {
    protected static Timer timer1,timer2;
    protected static int second1, minute1, second2,minute2;
    protected static boolean isBlackPlayerTimeOver=false;
    protected static boolean isWhitePlayerTimeOver=false;
    protected static int totalTime= GameInfo.timerSlider.getValue()*2;
    protected static int remainingMinutes= minute1+minute2;
    protected static int remainingSeconds =second1+second2;
    protected static int timeElapsedMinutes,timeElapsedSeconds;


    GameTimer(){
        setInitialValues();
        countdownTimer();
    }
    public static void setInitialValues(){
        second1=0;
        minute1=GameInfo.timerSlider.getValue();
        second2=0;
        minute2=0;
        minute2=GameInfo.timerSlider.getValue();
        if (GameInfo.timerSlider.getValue()<10) {
            GameInfoPanel.counterLabel1.setText("0" + GameInfo.timerSlider.getValue() + ":00");
            GameInfoPanel.counterLabel2.setText("0" + GameInfo.timerSlider.getValue() + ":00");
        } else {
            GameInfoPanel.counterLabel1.setText(GameInfo.timerSlider.getValue() + ":00");
            GameInfoPanel.counterLabel2.setText(GameInfo.timerSlider.getValue() + ":00");
        }
    }

    public static void countdownTimer(){
        timer1 =new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second1--;
                if (second1 < 10 && minute1 < 10) {
                    GameInfoPanel.counterLabel1.setText("0" + minute1 + ":0" + second1);
                } else if (second1 < 10 && minute1 >= 10) GameInfoPanel.counterLabel1.setText(minute1 + ":0" + second1);
                else if (second1 >= 10 && minute1 < 10) GameInfoPanel.counterLabel1.setText("0" + minute1 + ":" + second1);
                else GameInfoPanel.counterLabel1.setText(minute1 + ":" + second1);
                if (second1 == -1) {
                    second1 = 59;
                    minute1--;
                    if (minute1<10) GameInfoPanel.counterLabel1.setText("0" + minute1 + ":" + second1);
                    else GameInfoPanel.counterLabel1.setText(minute1 + ":" + second1);
                }
                if (minute1 == 0 && second1 == 0) {
                    timer1.stop();
                    isBlackPlayerTimeOver=true;
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ChessGUI.gui);
                    frame.dispose();
                    new GameOverWindow();
                }
            }
        });
        timer2 =new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second2--;
                if (second2 < 10 && minute2 < 10) {
                    GameInfoPanel.counterLabel2.setText("0" + minute2 + ":0" + second2);
                } else if (second2 < 10 && minute2 >= 10) GameInfoPanel.counterLabel2.setText(minute2 + ":0" + second2);
                else if (second2 >= 10 && minute2 < 10) GameInfoPanel.counterLabel2.setText("0" + minute2 + ":" + second2);
                else GameInfoPanel.counterLabel2.setText(minute2 + ":" + second2);
                if (second2 == -1) {
                    second2 = 59;
                    minute2--;
                    if (minute2<10) GameInfoPanel.counterLabel2.setText("0" + minute2 + ":" + second2);
                    else GameInfoPanel.counterLabel2.setText(minute2 + ":" + second2);
                }
                if (minute2 == 0 && second2 == 0) {
                    timer2.stop();
                    isWhitePlayerTimeOver=true;
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(ChessGUI.gui);
                    frame.dispose();
                    new GameOverWindow();
                }
            }
        });
    }
    public static void TimeElapsedCalculator() {
        remainingMinutes=minute1+minute2;
        remainingSeconds=second1+second2;
        if (remainingSeconds == 60) {
            timeElapsedMinutes =totalTime-( remainingMinutes + 1);
        }
        else {
            if (remainingSeconds > 60) {
                remainingMinutes = remainingMinutes + 1;
                remainingSeconds = remainingSeconds - 60;
            }

            if (remainingSeconds == 0) {
                timeElapsedMinutes = totalTime - remainingMinutes;
            }   else {
                timeElapsedMinutes = totalTime - remainingMinutes - 1;
                timeElapsedSeconds = 60 - remainingSeconds;
            }
            System.out.println(remainingMinutes);
            System.out.println(remainingSeconds);
        }
    }

    public static String remainingTimeToString() {
        if(timeElapsedMinutes<10){
            if(timeElapsedSeconds<10){
                return "0"+timeElapsedMinutes +":0"+timeElapsedSeconds;
            }
            else return "0"+timeElapsedMinutes +":"+timeElapsedSeconds;
        }
        else{
            if(timeElapsedSeconds<10){
                return timeElapsedMinutes +":0"+timeElapsedSeconds;
            }
            else return timeElapsedMinutes +":"+timeElapsedSeconds;
        }


    }


}