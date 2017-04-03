package de.baernreuther.basketballcountdowntimer.listener.editboxes;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;

import de.baernreuther.basketballcountdowntimer.MainActivity;
import de.baernreuther.basketballcountdowntimer.countdowntimer.AttackTimeCountdownTimer;
import de.baernreuther.basketballcountdowntimer.countdowntimer.GameTimeCountdownTimer;
import de.baernreuther.basketballcountdowntimer.dialogs.MessageBoxFragment;

/**
 * Created by René on 02.04.2017.
 */

public class AttackTimeEditText implements TextWatcher {


    private CheckBox allowEditingCheckBox;
    private EditText attackTime;
    private MainActivity mainActivity;

    public AttackTimeEditText(CheckBox allowEditingCheckBox, EditText attackTime, MainActivity mainActivity) {
        this.allowEditingCheckBox = allowEditingCheckBox;
        this.attackTime = attackTime;
        this.mainActivity = mainActivity;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //Not necessary
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // Not necessary
    }

    /**
     * We will change the value of the shot clock time accordingly to the users entry.
     * In case the entry is invalid, that is smaller than zero or larger than 30 (college shot clock),
     * we will show an error
     *
     * @param s
     */
    @Override
    public void afterTextChanged(Editable s) {
        //We need to check whether the field is empty because it throws an error when it is empty. We put in zero if there is no string inside to prevent this.
        if (allowEditingCheckBox.isChecked() && GameTimeCountdownTimer.getUniqueInstance().isPaused()) {
            String time = attackTime.getText().toString();
            if (time.equals("")) {
                time = "0"; //Small workaround
            }
            AttackTimeCountdownTimer.getUniqueInstance().cancel();

            int shotClockLeft = Integer.valueOf(time);
            if (shotClockLeft < 0 || shotClockLeft >= 30) {
                MessageBoxFragment messageBoxFragment = new MessageBoxFragment();
                messageBoxFragment.setMessage("You cannot set a shot clock time larger than 30 seconds or smaller than zero.");
                messageBoxFragment.show(mainActivity.getFragmentManager(), "Invalid shot clock time");
                return;
            }
            AttackTimeCountdownTimer.createUniqueInstance(Integer.valueOf(time), 1000, attackTime);

        }
    }
}
