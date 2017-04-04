package de.baernreuther.basketballcountdowntimer.listener.buttons;

import android.view.View;

import de.baernreuther.basketballcountdowntimer.MainActivity;
import de.baernreuther.basketballcountdowntimer.dialogs.MessageBoxFragment;

/**
 * Created by René Bärnreuther on 03.04.2017.
 * Shows an help dialog.
 */

public class HelpListener implements View.OnClickListener {

    private MainActivity context;

    public HelpListener(MainActivity mainActivity) {
        this.context = mainActivity;
    }

    @Override
    public void onClick(View view) {
        MessageBoxFragment dia = new MessageBoxFragment();
        dia.setMessage("Use Change Times when game is paused to be allowed to edit the times by hand." +
                "Press Start to start the timer and then stop, to pause it." +
                "Written by René Bärnreuther");
        dia.show(context.getFragmentManager(), "Test");
    }
}
