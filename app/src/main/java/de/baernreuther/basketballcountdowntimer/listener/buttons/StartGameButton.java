package de.baernreuther.basketballcountdowntimer.listener.buttons;

import android.view.View;
import android.widget.EditText;

import de.baernreuther.basketballcountdowntimer.countdowntimer.AttackTimeCountdownTimer;
import de.baernreuther.basketballcountdowntimer.countdowntimer.GameTimeCountdownTimer;

/**
 * Created by René on 02.04.2017.
 */

public class StartGameButton implements View.OnClickListener {

    private EditText attackTime;
    private AttackTimeCountdownTimer attackTimeCountdownTimer;
    private GameTimeCountdownTimer gameTimeCountdownTimer;

    /**
     * Creates a button to start and pause the game.
     *
     * @param attackTime
     * @param attackTimeCountdownTimer
     * @param gameTimeCountdownTimer
     */
    public StartGameButton(EditText attackTime, AttackTimeCountdownTimer attackTimeCountdownTimer, GameTimeCountdownTimer gameTimeCountdownTimer) {
        this.attackTime = attackTime;
        this.attackTimeCountdownTimer = attackTimeCountdownTimer;
        this.gameTimeCountdownTimer = gameTimeCountdownTimer;
    }

    @Override
    public void onClick(View v) {
        if (attackTimeCountdownTimer.hasStoped()) {
            attackTimeCountdownTimer = AttackTimeCountdownTimer.AttackTimeCountdownFactory(24, 1000, attackTime, gameTimeCountdownTimer);
        }

        if (gameTimeCountdownTimer.isPaused()) {
            gameTimeCountdownTimer.start();
            attackTimeCountdownTimer.start();
        } else {
            gameTimeCountdownTimer.pause();
            attackTimeCountdownTimer.pause();
        }
    }
}
