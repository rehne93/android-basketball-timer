package de.baernreuther.basketballcountdowntimer.countdowntimer;

import android.widget.EditText;
import android.widget.TextView;

import org.jetbrains.annotations.Contract;

import de.baernreuther.basketballcountdowntimer.time.TimeConverter;

/**
 * Created by René Bärnreuther on 02.04.2017.
 * Implements a Countdown for the attacking time, usually within 24 seconds or less.
 * Contains a GameTime Countdown to pause it when the attack time is over (which is in the current rules included).
 * If the timer stopped, we need to create a new one. If the timer is stopped, we can assume the game has paused. Therefore we
 * will create a new one if hasStoped is true.
 */

public class AttackTimeCountdownTimer extends PausableCountDownTimer {


    /**
     * Creates an instance of an Attack Countdowntimer.
     * @param seconds seconds left on the new clock
     * @param interval the interval in ms, usually 1000
     * @param attackTimeEditText the view where the attacktime is
     * @param gameTimeContdown the timer for the whole time.
     * @return an instance of AttackTimeCountdownTimer containing the new time.
     */
    @Contract("_, _, _, _ -> !null")
    public static AttackTimeCountdownTimer AttackTimeCountdownFactory(int seconds, long interval, EditText attackTimeEditText, PausableCountDownTimer gameTimeContdown){
        return new AttackTimeCountdownTimer(seconds*1000, interval, attackTimeEditText, gameTimeContdown);
    }



    private EditText attackTimeText;
    private PausableCountDownTimer gameTimeCountDownTimer;
    private boolean hasStoped = false;

    private AttackTimeCountdownTimer(long millisInFuture, long countDownInterval, EditText attackTimeEditText, PausableCountDownTimer gameTime) {
        super(millisInFuture, countDownInterval);
        this.attackTimeText = attackTimeEditText;
        this.gameTimeCountDownTimer = gameTime;
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
        this.hasStoped = true;
        gameTimeCountDownTimer.pause();
    }

    public boolean hasStoped(){
        return hasStoped;
    }
}
