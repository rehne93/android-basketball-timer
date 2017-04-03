package de.baernreuther.basketballcountdowntimer.listener.buttons;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import de.baernreuther.basketballcountdowntimer.countdowntimer.AttackTimeCountdownTimer;
import de.baernreuther.basketballcountdowntimer.countdowntimer.GameTimeCountdownTimer;

/**
 * Created by René Bärnreuther on 02.04.2017.
 * Implements a button who gets pressed to start or pause the game.
 *
 */

public class StartGameButton implements View.OnClickListener {

    /*
    The edittext to show the time for an offense.
     */
    private EditText attackTime;
    /*
        The Button itself.
    */
    private Button myself;

    /*
    To remove the checked if the button is pressed.
     */
    private CheckBox editFieldsCheckbox;

    /**
     * Creates a button to start and pause the game.
     * @param attackTime the Edittext which shows the attacking time
     */
    public StartGameButton(EditText attackTime, Button myself, CheckBox editFieldsCheckbox) {
        this.attackTime = attackTime;
        this.myself = myself;
        this.editFieldsCheckbox = editFieldsCheckbox;
    }

    /**
     * Depending on the current situation, the game gets paused or resumed.
     * In case the attacktimer has stopped, we will create a new instance of it because the game needs one.
     * The Button gets renamed fitting to the current situation.
     */
    @Override
    public void onClick(View v) {
        editFieldsCheckbox.setChecked(false);
        if (this.myself.getText().equals("Start")) {
            this.myself.setText("Stop");
        } else {
            this.myself.setText("Start");
        }
        if (AttackTimeCountdownTimer.getUniqueInstance() != null) {

            if (AttackTimeCountdownTimer.getUniqueInstance().hasStopped()) {
                AttackTimeCountdownTimer.createUniqueInstance(24, 1000, attackTime);
            }
            // Do Nothing, TODO Refactore me.
        } else {
            AttackTimeCountdownTimer.createUniqueInstance(24, 1000, attackTime);
        }

        if (GameTimeCountdownTimer.getUniqueInstance().isPaused()) {
            GameTimeCountdownTimer.getUniqueInstance().start();
            AttackTimeCountdownTimer.getUniqueInstance().start();
        } else {
            GameTimeCountdownTimer.getUniqueInstance().pause();
            AttackTimeCountdownTimer.getUniqueInstance().pause();
        }
    }

}
