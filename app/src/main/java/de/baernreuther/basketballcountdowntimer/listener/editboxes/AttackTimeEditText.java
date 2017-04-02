package de.baernreuther.basketballcountdowntimer.listener.editboxes;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;

import de.baernreuther.basketballcountdowntimer.countdowntimer.AttackTimeCountdownTimer;
import de.baernreuther.basketballcountdowntimer.countdowntimer.GameTimeCountdownTimer;

/**
 * Created by René on 02.04.2017.
 */

public class AttackTimeEditText implements TextWatcher {


    private CheckBox allowEditingCheckBox;
    private EditText attackTime;
    private GameTimeCountdownTimer gameTimeCountdownTimer;
    private AttackTimeCountdownTimer attackTimeCountdownTimer;

    public AttackTimeEditText(CheckBox allowEditingCheckBox, EditText attackTime, GameTimeCountdownTimer gameTimeCountdownTimer, AttackTimeCountdownTimer attackTimeCountdownTimer) {
        this.allowEditingCheckBox = allowEditingCheckBox;
        this.attackTime = attackTime;
        this.gameTimeCountdownTimer = gameTimeCountdownTimer;
        this.attackTimeCountdownTimer = attackTimeCountdownTimer;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //Not necessary
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // Not necessary
    }

    @Override
    public void afterTextChanged(Editable s) {
        //We need to check whether the field is empty because it throws an error when it is empty. We put in zero if there is no string inside to prevent this.
        if (allowEditingCheckBox.isChecked() && gameTimeCountdownTimer.isPaused()) {
            String time = attackTime.getText().toString();
            if (time.equals("")) {
                time = "0"; //Small workaround
            }
            attackTimeCountdownTimer.cancel();
            attackTimeCountdownTimer = AttackTimeCountdownTimer.AttackTimeCountdownFactory(Integer.valueOf(time), 1000, attackTime, gameTimeCountdownTimer);
        }
    }
}
