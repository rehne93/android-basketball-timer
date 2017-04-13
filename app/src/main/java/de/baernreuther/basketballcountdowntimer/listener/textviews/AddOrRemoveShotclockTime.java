package de.baernreuther.basketballcountdowntimer.listener.textviews;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import de.baernreuther.basketballcountdowntimer.countdowntimer.GameTimeCountdownTimer;
import de.baernreuther.basketballcountdowntimer.countdowntimer.ShotClockCountdownTimer;
import de.baernreuther.basketballcountdowntimer.enums.ADD_OR_REMOVE;

/**
 * Created by René Bärnreuther on 10.04.2017.
 * Implements a button that adds (or removes) one second from the shotclock
 */

public class AddOrRemoveShotclockTime implements View.OnClickListener {


    /*
    The Shotclock text showing the remaining time in seconds
     */
    private EditText shotClock;

    /*
    Button for Start and Stop, needed to create a new instance of Shotclock
     */
    private Button startGameButton;

    /*
    Tells me, if we have to add or remove a second.
     */
    private ADD_OR_REMOVE whatToDo;


    public AddOrRemoveShotclockTime(EditText shotClock, ADD_OR_REMOVE whatToDo, Button startGameButton) {
        this.shotClock = shotClock;
        this.startGameButton = startGameButton;
        this.whatToDo = whatToDo;
    }

    /*
    Changes the time on the shotclock.
     */
    private void changeTime() {
        if (whatToDo == ADD_OR_REMOVE.ADD) {
            int time = Integer.valueOf(shotClock.getText().toString());
            time++;
            if (time > 30)
                time = 30;
            shotClock.setText(String.valueOf(time));
            createNewShotClockInstance(time);
        } else if (whatToDo == ADD_OR_REMOVE.REMOVE) {
            int time = Integer.valueOf(shotClock.getText().toString());
            time--;
            if (time < 1)
                time = 1;
            // It actually makes senese, but it shouldnt exactly work like that, the game should immediatly stop again.
            shotClock.setText(String.valueOf(time));
            createNewShotClockInstance(time);
        }
    }


    /*
    Creates a new ShotclockInstace which is needed when we set a new time.
     */
    private void createNewShotClockInstance(int seconds) {
        ShotClockCountdownTimer.createUniqueInstance(seconds, 1000, shotClock, startGameButton);
    }


    @Override
    public void onClick(View v) {
        if (GameTimeCountdownTimer.getUniqueInstance().isPaused())
            changeTime();
        Log.d("AddMinute", "Adding a minute to the minute timer");
    }
}
