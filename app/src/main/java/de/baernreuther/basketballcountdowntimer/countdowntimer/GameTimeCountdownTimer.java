package de.baernreuther.basketballcountdowntimer.countdowntimer;

import android.widget.EditText;

import java.util.UnknownFormatConversionException;

import de.baernreuther.basketballcountdowntimer.time.TimeConverter;

/**
 * Created by René Bärnreuther on 02.04.2017.
 * Implements a GameTimeCountdownTimer which usually runs 10:00 minutes in a common game but can be changed accordingly.
 * Nothing special to mention here. Just ticks down and shows the time left in MM:SS format.
 */

public class GameTimeCountdownTimer extends PausableCountDownTimer {


    // TODO Think about how to remove redundancy

    /*
       We need to only have access to one single instance of this. This instance can be replaced whenever we want, but still only one is allowed to exist.
   */
    private static GameTimeCountdownTimer uniqueInstance = null;

    private EditText minutesLeft;
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
            throw new NullPointerException("Trying to get a GameTimCountdownTimer uninitialized.");
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


    @Override
    public void onFinish() {
        minutesLeft.setText("0");
        secondsLeft.setText("0");
    }
}
