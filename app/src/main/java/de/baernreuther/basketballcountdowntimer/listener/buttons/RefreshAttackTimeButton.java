package de.baernreuther.basketballcountdowntimer.listener.buttons;

import android.view.View;
import android.widget.EditText;

import de.baernreuther.basketballcountdowntimer.countdowntimer.AttackTimeCountdownTimer;
import de.baernreuther.basketballcountdowntimer.countdowntimer.GameTimeCountdownTimer;

/**
 * Created by René on 02.04.2017.
 */

public class RefreshAttackTimeButton implements View.OnClickListener {

    private EditText attackTime;
    private AttackTimeCountdownTimer attackTimeCountdownTimer;
    private GameTimeCountdownTimer gameTimeCountdownTimer;
    private int timeLeft;

    /**
     * Creates a RefreshAttackTimeButton who is able to refresh the attack time to a certain amount of time.
     *
     * @param attackTime               the edittext with the current attack time
     * @param attackTimeCountdownTimer the countdowntimer for the attack time
     * @param gameTimeCountdownTimer   the countdowntimer for the gametime
     * @param timeLeft                 the time left in this attack.
     */
    public RefreshAttackTimeButton(EditText attackTime, AttackTimeCountdownTimer attackTimeCountdownTimer, GameTimeCountdownTimer gameTimeCountdownTimer, int timeLeft) {
        this.attackTime = attackTime;
        this.attackTimeCountdownTimer = attackTimeCountdownTimer;
        this.gameTimeCountdownTimer = gameTimeCountdownTimer;
        this.timeLeft = timeLeft;
    }

    /**
     * Stops the current Attackcountdowntimer and starts a new one with the given reset time. Depending on the current state, the attackcountdown starts right now or not.
     */
    @Override
    public void onClick(View v) {
        attackTimeCountdownTimer.cancel();
        attackTimeCountdownTimer = null;
        attackTimeCountdownTimer = AttackTimeCountdownTimer.AttackTimeCountdownFactory(timeLeft, 1000, attackTime, gameTimeCountdownTimer);
        if (!gameTimeCountdownTimer.isPaused()) {
            attackTimeCountdownTimer.start();
        }

    }
}
