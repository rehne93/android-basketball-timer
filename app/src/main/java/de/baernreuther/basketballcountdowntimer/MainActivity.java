package de.baernreuther.basketballcountdowntimer;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.baernreuther.basketballcountdowntimer.countdowntimer.GameTimeCountdownTimer;
import de.baernreuther.basketballcountdowntimer.countdowntimer.ShotClockCountdownTimer;
import de.baernreuther.basketballcountdowntimer.listener.buttons.HelpListener;
import de.baernreuther.basketballcountdowntimer.listener.buttons.RefreshShotClockListener;
import de.baernreuther.basketballcountdowntimer.listener.buttons.StartStopGameListener;
import de.baernreuther.basketballcountdowntimer.listener.editboxes.AttackTimeEditText;
import de.baernreuther.basketballcountdowntimer.listener.editboxes.GameTimeEditText;


/**
 * Implements the basic start activity showing the Basketball Countdown Timer for a Quarter.
 * TODO Make StartStopButton change when shotclock is over
 */
public class MainActivity extends AppCompatActivity {


    public static final int SHOTCLOCK = 24;
    public static final int SHORTCLOCK_REBOUND = 14;
    public static final int GAMETIME = 600;

    @BindView(R.id.startGameButton)
    Button startPauseGameTimeButton;

    @BindView(R.id.refreshAttackTime)
    Button newShotClockButton;

    @BindView(R.id.offensiveReboundButton)
    Button newOffenseShotClockButton;

    @BindView(R.id.helpButton)
    Button helpButton;

    @BindView(R.id.minutesLeft)
    EditText minutesLeft;

    @BindView(R.id.secondsLeft)
    EditText secondsLeft;

    @BindView(R.id.shotClock)
    EditText shotClock;

    @BindView(R.id.editFieldsCheckbox)
    CheckBox editFieldsCheckbox;

    private Context myContext;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
        myContext = this.getBaseContext();

        initializeTimers();
        enableFields();
        initializeListeners();

        // TODO make me my own listener
        editFieldsCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                /* Only allow to change fields when game is paused */
                if (GameTimeCountdownTimer.getUniqueInstance().isPaused()) {
                    enableFields();
                } else {
                    Toast.makeText(myContext, "You cannot change times when the game is running.", Toast.LENGTH_LONG).show();
                    editFieldsCheckbox.setChecked(false);

                }
            }
        });


    }


    /*
    Initialize the timers with the default values 600seconsd (10:00 minutes) game time and 24 seconds for the shotclock
     */
    private void initializeTimers() {
        GameTimeCountdownTimer.createUniqueInstance(GAMETIME, 1000, minutesLeft, secondsLeft);
        ShotClockCountdownTimer.createUniqueInstance(SHOTCLOCK, 1000, shotClock, startPauseGameTimeButton);
    }
    /*
        Enables or disables all fields.
     */
    private void enableFields() {

        shotClock.setEnabled(!shotClock.isEnabled());
        minutesLeft.setEnabled(!minutesLeft.isEnabled());
        secondsLeft.setEnabled(!secondsLeft.isEnabled());
    }

    /*
    Initializes all listeners.
     */
    private void initializeListeners() {
        newShotClockButton.setOnClickListener(new RefreshShotClockListener(shotClock, SHOTCLOCK, startPauseGameTimeButton));
        newOffenseShotClockButton.setOnClickListener(new RefreshShotClockListener(shotClock, SHORTCLOCK_REBOUND, startPauseGameTimeButton));
        startPauseGameTimeButton.setOnClickListener(new StartStopGameListener(shotClock, startPauseGameTimeButton, editFieldsCheckbox));
        helpButton.setOnClickListener(new HelpListener(this));

        shotClock.addTextChangedListener(new AttackTimeEditText(editFieldsCheckbox, shotClock, this));
        minutesLeft.addTextChangedListener(new GameTimeEditText(editFieldsCheckbox, minutesLeft, secondsLeft));
        secondsLeft.addTextChangedListener(new GameTimeEditText(editFieldsCheckbox, minutesLeft, secondsLeft));
    }

    public Button getStartPauseGameTimeButton() {
        return this.startPauseGameTimeButton;
    }

}
