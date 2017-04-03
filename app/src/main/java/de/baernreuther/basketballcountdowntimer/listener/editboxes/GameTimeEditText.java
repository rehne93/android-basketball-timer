package de.baernreuther.basketballcountdowntimer.listener.editboxes;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

import de.baernreuther.basketballcountdowntimer.countdowntimer.GameTimeCountdownTimer;
import de.baernreuther.basketballcountdowntimer.time.TimeConverter;

/**
 * Created by René on 03.04.2017.
 */

public class GameTimeEditText implements TextWatcher {

    private CheckBox allowEditingCheckbox;
    private EditText minutesLeft;
    private EditText secondsLeft;

    public GameTimeEditText(CheckBox allowEditingCheckbox, EditText minutes, EditText seconds) {
        super();
        this.allowEditingCheckbox = allowEditingCheckbox;
        this.minutesLeft = minutes;
        this.secondsLeft = seconds;

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        //We need to check whether the field is empty because it throws an error when it is empty. We put in zero if there is no string inside to prevent this.
        if (allowEditingCheckbox.isChecked() && GameTimeCountdownTimer.getUniqueInstance().isPaused()) {
            String minutes = minutesLeft.getText().toString();
            if (minutes.equals("")) {
                minutes = "0"; //Small workaround
            }
            String seconds = secondsLeft.getText().toString();
            if (seconds.equals("")) {
                seconds = "0";
            }
            GameTimeCountdownTimer.getUniqueInstance().cancel();
            int time = TimeConverter.getSeconds(Integer.parseInt(minutes), Integer.parseInt(seconds));
            Log.d("Time", "Calculated time: " + time);
            GameTimeCountdownTimer.createUniqueInstance(time, 1000, minutesLeft, secondsLeft);

        }
    }
}
