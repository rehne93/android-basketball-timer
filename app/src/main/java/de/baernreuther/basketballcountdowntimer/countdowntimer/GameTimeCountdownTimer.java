package de.baernreuther.basketballcountdowntimer.countdowntimer;

import android.widget.TextView;

import de.baernreuther.basketballcountdowntimer.time.TimeConverter;

/**
 * Created by René Bärnreuther on 02.04.2017.
 * Implements a GameTimeCountdownTimer which usually runs 10:00 minutes in a common game but can be changed accordingly.
 * Nothing special to mention here. Just ticks down and shows the time left in MM:SS format.
 */

public class GameTimeCountdownTimer extends PausableCountDownTimer {

    // TODO Implement singleton variante
    private TextView timeLeftTextView;

    public GameTimeCountdownTimer(long millisInFuture, long countDownInterval, TextView timeLeftTextView) {
        super(millisInFuture, countDownInterval);
        this.timeLeftTextView = timeLeftTextView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        this.timeLeftTextView.setText(TimeConverter.getMinutesAndSecondsLeft(millisUntilFinished));
    }

    @Override
    public void onFinish() {
        this.timeLeftTextView.setText("00:00");
    }
}
