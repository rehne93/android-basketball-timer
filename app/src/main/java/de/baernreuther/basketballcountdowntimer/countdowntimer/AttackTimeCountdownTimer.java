package de.baernreuther.basketballcountdowntimer.countdowntimer;

import android.widget.EditText;
import android.widget.TextView;

import de.baernreuther.basketballcountdowntimer.time.TimeConverter;

/**
 * Created by René Bärnreuther on 02.04.2017.
 * Implements a Countdown for the attacking time, usually within 24 seconds or less.
 * Contains a GameTime Countdown to pause it when the attack time is over (which is in the current rules included).
 * If the timer stopped, we need to create a new one. If the timer is stopped, we can assume the game has paused. Therefore we
 * will create a new one if stopped is true.
 */

public class AttackTimeCountdownTimer extends PausableCountDownTimer {


    /*
    We need to only have access to one single instance of this. This instance can be replaced whenever we want, but still only one is allowed to exist.
     */
    private static AttackTimeCountdownTimer uniqueInstance;
    /*
    The edittext for the offense time.
     */
    private EditText attackTimeText;
    /*
      If the timer has stopped or not.
     */
    private boolean stopped;


    private AttackTimeCountdownTimer(long millisInFuture, long countDownInterval, EditText attackTimeEditText) {
        super(millisInFuture, countDownInterval);
        this.attackTimeText = attackTimeEditText;
        stopped = false;
    }

    public static AttackTimeCountdownTimer createUniqueInstance(int seconds, long interval, EditText attackTimeText) {
        uniqueInstance = new AttackTimeCountdownTimer(seconds * 1000, interval, attackTimeText);
        return uniqueInstance;
    }

    public static AttackTimeCountdownTimer getUniqueInstance() {
        if (uniqueInstance == null) {
            throw new NullPointerException("Trying to get a AttackTimeCountdownTimer uninitialized.");
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
        // TODO Set timepicker to zerop
        attackTimeText.setText(String.valueOf(0));
        this.stopped = true;
        GameTimeCountdownTimer.getUniqueInstance().pause();
    }

    public boolean hasStopped() {
        return stopped;
    }
}
