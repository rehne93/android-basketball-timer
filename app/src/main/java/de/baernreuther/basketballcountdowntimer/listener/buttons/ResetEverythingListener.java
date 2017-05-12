package de.baernreuther.basketballcountdowntimer.listener.buttons;

/**
 * Created by Tim on 10.05.2017.
 */

import android.view.View;
import android.widget.Button;

import de.baernreuther.basketballcountdowntimer.MainActivity;

public class ResetEverythingListener implements View.OnClickListener {

    /*
        The MainActivity to edit all fields.
     */
    private MainActivity mainActivity;
    /*
        The Button itself.
    */
    private Button ResetEverythingButton;

    /**
     * Creates a button reset game time and points.
     * @param mainActivity MainActivity object to edit all fields.
     * @param ResetEverythingButton the button itself.
     */
    public ResetEverythingListener(MainActivity mainActivity, Button ResetEverythingButton) {
        this.mainActivity = mainActivity;
        this.ResetEverythingButton = ResetEverythingButton;
    }

    /**
     * Depending on the current situation, the game gets paused or resumed.
     * In case the attacktimer has stopped, we will create a new instance of it because the game needs one.
     * The Button gets renamed fitting to the current situation.
     */
    @Override
    public void onClick(View v) {
    }

}

