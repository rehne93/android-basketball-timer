package de.baernreuther.basketballcountdowntimer.listener.buttons;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.baernreuther.basketballcountdowntimer.R;
import de.baernreuther.basketballcountdowntimer.countdowntimer.GameTimeCountdownTimer;
import de.baernreuther.basketballcountdowntimer.countdowntimer.ShotClockCountdownTimer;
import de.baernreuther.basketballcountdowntimer.enums.ADD_OR_REMOVE;

/**
 * Created by René Bärnreuther on 11.04.2017.
 * Adds or removes a point for the team.
 */

public class AddOrRemovePointsListener implements View.OnClickListener {

    /*
    The textview showing the points
     */
    private TextView currentPoints;

    /*
    What to do: add or remove one point
     */
    private ADD_OR_REMOVE whatToDo;

    /*
    The Button to start the game
    TODO Make this button an instance for everyone.
     */
    private Button startGameButton;

    public AddOrRemovePointsListener(TextView currentPoints, ADD_OR_REMOVE add_or_remove, Button startGameButton) {
        this.currentPoints = currentPoints;
        this.whatToDo = add_or_remove;
        this.startGameButton = startGameButton;
    }

    /**
     * Changes the result depending on the click
     *
     * @param view
     */
    public void onClick(View view) {
        int currPoints = Integer.valueOf(currentPoints.getText().toString());
        if (whatToDo == ADD_OR_REMOVE.ADD) {
            currPoints++;
        } else if (whatToDo == ADD_OR_REMOVE.REMOVE) {
            if (currPoints > 0)
                currPoints--;
        }

        currentPoints.setText(String.valueOf(currPoints));

        // Everytime someone scores the time has to stop.
        if (!GameTimeCountdownTimer.getUniqueInstance().isPaused())
            GameTimeCountdownTimer.getUniqueInstance().pause();
        if (!ShotClockCountdownTimer.getUniqueInstance().isPaused())
            ShotClockCountdownTimer.getUniqueInstance().pause();

        if (startGameButton != null) {
            startGameButton.setText("Start");
            startGameButton.setBackgroundResource(R.color.game_not_running);
        }


    }
}
