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
    private GameTimeCountdownTimer gameTimeCountdownTimer;

    /**
     * Creates a button to start and pause the game.
     *
     * @param attackTime
     * @param gameTimeCountdownTimer
     */
    public StartGameButton(EditText attackTime, GameTimeCountdownTimer gameTimeCountdownTimer) {
        this.attackTime = attackTime;
        this.gameTimeCountdownTimer = gameTimeCountdownTimer;
    }

    @Override
    public void onClick(View v) {
        if (AttackTimeCountdownTimer.getUniqueInstance() != null) {
            if (AttackTimeCountdownTimer.getUniqueInstance().hasStoped()) {
                AttackTimeCountdownTimer.createUniqueInstance(24, 1000, attackTime, gameTimeCountdownTimer);
            }
        } else {
            AttackTimeCountdownTimer.createUniqueInstance(24, 1000, attackTime, gameTimeCountdownTimer);
        }

        if (gameTimeCountdownTimer.isPaused()) {
            gameTimeCountdownTimer.start();
            AttackTimeCountdownTimer.getUniqueInstance().start();
        } else {
            gameTimeCountdownTimer.pause();
            AttackTimeCountdownTimer.getUniqueInstance().pause();
        }
    }

}
