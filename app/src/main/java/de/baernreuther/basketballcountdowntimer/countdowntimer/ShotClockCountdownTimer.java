package de.baernreuther.basketballcountdowntimer.countdowntimer;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.baernreuther.basketballcountdowntimer.R;
import de.baernreuther.basketballcountdowntimer.time.TimeConverter;

/**
 * Created by René Bärnreuther on 02.04.2017.
 * Implements a Countdown for the attacking time, usually within 24 seconds or less.
 * Contains a GameTime Countdown to pause it when the attack time is over (which is in the current rules included).
 * If the timer stopped, we need to create a new one. If the timer is stopped, we can assume the game has paused. Therefore we
 * will create a new one if stopped is true.
 */

public class ShotClockCountdownTimer extends PausableCountDownTimer {


    /*
    We need to only have access to one single instance of this. This instance can be replaced whenever we want, but still only one is allowed to exist.
     */
    private static ShotClockCountdownTimer uniqueInstance;
    /*
    The edittext for the offense time.
     */
    private EditText attackTimeText;
    /*
      If the timer has stopped or not.
     */
    private boolean stopped;
    /*

     */
    private Button startGameButton;

    private ShotClockCountdownTimer(long millisInFuture, long countDownInterval, EditText attackTimeEditText, Button startGameButton) {
        super(millisInFuture, countDownInterval);
        this.attackTimeText = attackTimeEditText;
        stopped = false;
        this.startGameButton = startGameButton;
    }

    /*
    Creates a unique Shotclock Countdowntimer. If there is already one, it will be overwritten.
     */
    public static ShotClockCountdownTimer createUniqueInstance(int seconds, long interval, EditText attackTimeText, Button startGameButton) {
        uniqueInstance = new ShotClockCountdownTimer(seconds * 1000, interval, attackTimeText, startGameButton);
        return uniqueInstance;
    }

    /*
    Returns the only existing instance of shotclockcountdown.
     */
    public static ShotClockCountdownTimer getUniqueInstance() {
        if (uniqueInstance == null) {
            throw new NullPointerException("Trying to get a  uninitialized ShotClockCountdownTimer .");
        }
        return uniqueInstance;
    }

    /**
     * Called every tick and sets new time left.
     * @param millisUntilFinished The amount of time until finished.
     */
    @Override
    public void onTick(long millisUntilFinished) {
            attackTimeText.setText(TimeConverter.getAttackTimeLeft(millisUntilFinished), TextView.BufferType.EDITABLE);
    }

    /**
     * TODO Implement a sound that shows the attack time is over
     */
    @Override
    public void onFinish() {
        attackTimeText.setText(String.valueOf(0));
        this.stopped = true;
        if (startGameButton != null) {
            startGameButton.setText("Start");
            startGameButton.setBackgroundResource(R.color.game_not_running);
        }

        GameTimeCountdownTimer.getUniqueInstance().pause();
    }

    public boolean hasStopped() {
        return stopped;
    }
}
