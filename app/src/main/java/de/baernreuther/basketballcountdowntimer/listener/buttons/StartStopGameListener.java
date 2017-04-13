package de.baernreuther.basketballcountdowntimer.listener.buttons;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import de.baernreuther.basketballcountdowntimer.MainActivity;
import de.baernreuther.basketballcountdowntimer.countdowntimer.GameTimeCountdownTimer;
import de.baernreuther.basketballcountdowntimer.countdowntimer.ShotClockCountdownTimer;

/**
 * Created by René Bärnreuther on 02.04.2017.
 * Implements a button who gets pressed to start or pause the game.
 *
 */

public class StartStopGameListener implements View.OnClickListener {

    /*
    The edittext to show the time for an offense.
     */
    private EditText attackTime;
    /*
        The Button itself.
    */
    private Button startGameButton;

    /*
    To remove the checked if the button is pressed.
     */
    private CheckBox editFieldsCheckbox;


    /**
     * Creates a button to start and pause the game.
     * @param attackTime the Edittext which shows the attacking time
     */
    public StartStopGameListener(EditText attackTime, Button startGameButton, CheckBox editFieldsCheckbox) {
        this.attackTime = attackTime;
        this.startGameButton = startGameButton;
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
        if (this.startGameButton.getText().equals("Start")) {
            this.startGameButton.setText("Stop");
        } else {
            this.startGameButton.setText("Start");
        }

        if (ShotClockCountdownTimer.getUniqueInstance() != null) {
            if (ShotClockCountdownTimer.getUniqueInstance().hasStopped()) {
                ShotClockCountdownTimer.createUniqueInstance(MainActivity.SHOTCLOCK, 1000, attackTime, startGameButton);
            }
        } else {
            ShotClockCountdownTimer.createUniqueInstance(MainActivity.SHOTCLOCK, 1000, attackTime, startGameButton);
        }

        if (GameTimeCountdownTimer.getUniqueInstance().isPaused()) {
            GameTimeCountdownTimer.getUniqueInstance().start();
            ShotClockCountdownTimer.getUniqueInstance().start();
        } else {
            GameTimeCountdownTimer.getUniqueInstance().pause();
            ShotClockCountdownTimer.getUniqueInstance().pause();
        }
    }

}
