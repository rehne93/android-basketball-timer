package de.baernreuther.basketballcountdowntimer.listener.checkboxes;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import de.baernreuther.basketballcountdowntimer.MainActivity;
import de.baernreuther.basketballcountdowntimer.countdowntimer.GameTimeCountdownTimer;

/**
 * Created by René Bärnreuther on 10.04.2017.
 * Implements the checkbox which enables the edittext fields with the seconds to change.
 */

public class ChangeValuesCheckBox implements CheckBox.OnCheckedChangeListener {

    /*
    Instance of the mainActivity needed because we need a) the context b) enableFields method
     */
    private MainActivity mainActivity;

    /*
    The checkBox the listener is on
     */
    private CheckBox editFieldsCheckbox;

    public ChangeValuesCheckBox(MainActivity mainActivity, CheckBox editFieldsCheckbox) {
        this.mainActivity = mainActivity;
        this.editFieldsCheckbox = editFieldsCheckbox;
    }

    /**
     * Depending on the GameTimeCountdownTimerState we enable the fields or show an error message.
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                /* Only allow to change fields when game is paused */
        if (GameTimeCountdownTimer.getUniqueInstance().isPaused()) {
            mainActivity.enableFields();
        } else {
            Toast.makeText(mainActivity.getApplicationContext(), "You cannot change times when the game is running.", Toast.LENGTH_LONG).show();
            editFieldsCheckbox.setChecked(false);

        }
    }
}
