package de.baernreuther.basketballcountdowntimer.listener.buttons;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import de.baernreuther.basketballcountdowntimer.countdowntimer.GameTimeCountdownTimer;
import de.baernreuther.basketballcountdowntimer.countdowntimer.ShotClockCountdownTimer;

/**
 * Created by René Bärnreuther on 02.04.2017.
 * Restarts the Shotclock
 */

public class RefreshShotClockListener implements View.OnClickListener {

    private EditText attackTime;
    private int timeLeft;
    private Button startGameButton;
    /**
     * Creates a RefreshShotClockListener who is able to refresh the attack time to a certain amount of time.
     *
     * @param attackTime               the edittext with the current attack time
     * @param timeLeft                 the time left in this attack.
     */
    public RefreshShotClockListener(EditText attackTime, int timeLeft, Button startGameButton) {
        this.attackTime = attackTime;
        this.timeLeft = timeLeft;
        this.startGameButton = startGameButton;
    }

    /**
     * Stops the current Attackcountdowntimer and starts a new one with the given reset time. Depending on the current state, the attackcountdown starts right now or not.
     */
    @Override
    public void onClick(View v) {
        ShotClockCountdownTimer.getUniqueInstance().cancel();
        ShotClockCountdownTimer.createUniqueInstance(timeLeft, 1000, attackTime, startGameButton);
        attackTime.setText(String.valueOf(timeLeft));
        if (!GameTimeCountdownTimer.getUniqueInstance().isPaused()) {
            ShotClockCountdownTimer.getUniqueInstance().start();
        }

    }
}
