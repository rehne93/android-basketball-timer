package de.baernreuther.basketballcountdowntimer.countdowntimer;

import android.widget.EditText;

import java.util.UnknownFormatConversionException;

import de.baernreuther.basketballcountdowntimer.MainActivity;
import de.baernreuther.basketballcountdowntimer.time.TimeConverter;

/**
 * Created by René Bärnreuther on 02.04.2017.
 * Implements a GameTimeCountdownTimer which usually runs 10:00 minutes in a common game but can be changed accordingly.
 * Nothing special to mention here. Just ticks down and shows the time left in MM:SS format.
 */

public class GameTimeCountdownTimer extends PausableCountDownTimer {


    /*
       We need to only have access to one single instance of this. This instance can be replaced whenever we want, but still only one is allowed to exist.
   */
    private static GameTimeCountdownTimer uniqueInstance = null;
    /*
    The minutes left in the game
     */
    private EditText minutesLeft;
    /*
    The seconds left in the game
     */
    private EditText secondsLeft;
    private GameTimeCountdownTimer(long millisInFuture, long countDownInterval, EditText minutesLeft, EditText secondsLeft) {
        super(millisInFuture, countDownInterval);
        this.minutesLeft = minutesLeft;
        this.secondsLeft = secondsLeft;
    }

    /**
     * Returns the unique instance of the GameTimeCountdownTimer. Throws an exception if not initialized.
     *
     * @return the unique instance of the countdown timer.
     */
    public static GameTimeCountdownTimer getUniqueInstance() {
        if (uniqueInstance == null) {
            throw new NullPointerException("Trying to get a uninitialized GameTimCountdownTimer .");
        }
        return uniqueInstance;
    }

    /**
     * Create a unique instance of the GameTimeCountdownTimer.
     *
     * @param seconds  seconds to play
     * @param interval the interval the countdowntimer ticks down
     * @return the instance created.
     */
    public static GameTimeCountdownTimer createUniqueInstance(int seconds, long interval, EditText minutesLeft, EditText secondsLeft) {
        if (uniqueInstance != null)
            uniqueInstance = null;

        uniqueInstance = new GameTimeCountdownTimer(seconds * 1000, interval, minutesLeft, secondsLeft);
        return uniqueInstance;
    }

    /*
     * Set new time on click
     *
     */
    @Override
    public void onTick(long millisUntilFinished) {
        String time = TimeConverter.getMinutesAndSecondsLeft(millisUntilFinished);
        String[] minutesAndSeconds = time.split(":");
        if (minutesAndSeconds.length != 2) {
            throw new UnknownFormatConversionException("Minutes and seconds got wrong format.");
        }
        minutesLeft.setText(minutesAndSeconds[0]);
        secondsLeft.setText(minutesAndSeconds[1]);
    }

    /*
    Sets everything to zero when the time is over
     */
    @Override
    public void onFinish() {
        MainActivity.getMediaPlayerInstance().start();

        minutesLeft.setText("00");
        secondsLeft.setText("00");
    }

    public int getSecondsLeft() {
        return Integer.valueOf(secondsLeft.getText().toString());
    }

    public int getTimeInSecondsLeft() {
        return Integer.valueOf(secondsLeft.getText().toString()) + 60 * Integer.valueOf(minutesLeft.getText().toString());
    }
}
