package de.baernreuther.basketballcountdowntimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.baernreuther.basketballcountdowntimer.countdowntimer.GameTimeCountdownTimer;
import de.baernreuther.basketballcountdowntimer.countdowntimer.ShotClockCountdownTimer;
import de.baernreuther.basketballcountdowntimer.enums.ADD_SECOND;
import de.baernreuther.basketballcountdowntimer.listener.buttons.HelpListener;
import de.baernreuther.basketballcountdowntimer.listener.buttons.RefreshShotClockListener;
import de.baernreuther.basketballcountdowntimer.listener.buttons.StartStopGameListener;
import de.baernreuther.basketballcountdowntimer.listener.checkboxes.ChangeValuesCheckBox;
import de.baernreuther.basketballcountdowntimer.listener.editboxes.AttackTimeEditText;
import de.baernreuther.basketballcountdowntimer.listener.editboxes.GameTimeEditText;
import de.baernreuther.basketballcountdowntimer.listener.textviews.AddOrRemoveShotclockTime;


/**
 * Implements the basic start activity showing the Basketball Countdown Timer for a Quarter.
 *
 */
public class MainActivity extends AppCompatActivity {


    /*
    Time for a common offensive in Basketball. If the team doesnt shot after 24 seconds, they loose the ball.
     */
    public static final int SHOTCLOCK = 24;
    /*
    If the offensive team gets an the rebound after their shot, they get another 14 seconds (at least in germany)
     */
    public static final int SHOTCLOCK_OFFENSIVE_REBOUND = 14;
    /*
    The common game time in european basketball is 10 minutes which equals 600 seconds.
     */
    public static final int GAMETIME_SECONDS = 600;
    /*
    Media Player to play the game finish sounds.
     */
    private static MediaPlayer mediaPlayer;
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
    @BindView(R.id.addSeconds)
    TextView addShotClockSecondTextView;
    @BindView(R.id.removeSeconds)
    TextView removeShotClockSecondTextView;

    /*
    Returns a media player if it exists.
     */
    public static MediaPlayer getMediaPlayerInstance() {
        if (mediaPlayer == null) {
            throw new NullPointerException("Cannot get an uninitialized mediaplayer");
        }
        return mediaPlayer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);

        initializeMedia();
        initializeTimers();
        enableFields();
        initializeListeners();
    }


    /*
    Initializes Media. Has to be called before timers are initialized.
     */
    private void initializeMedia() {
        mediaPlayer = MediaPlayer.create(this.getApplicationContext(), R.raw.buzzer);
    }

    /*
    Initialize the timers with the default values 600seconsd (10:00 minutes) game time and 24 seconds for the shotclock
     */
    private void initializeTimers() {
        GameTimeCountdownTimer.createUniqueInstance(GAMETIME_SECONDS, 1000, minutesLeft, secondsLeft);
        ShotClockCountdownTimer.createUniqueInstance(SHOTCLOCK, 1000, shotClock, startPauseGameTimeButton);
    }

    /*
        Enables or disables all fields.
     */
    public void enableFields() {
        shotClock.setEnabled(!shotClock.isEnabled());
        minutesLeft.setEnabled(!minutesLeft.isEnabled());
        secondsLeft.setEnabled(!secondsLeft.isEnabled());
    }

    /*
    Initializes all listeners.
     */
    private void initializeListeners() {
        newShotClockButton.setOnClickListener(new RefreshShotClockListener(shotClock, SHOTCLOCK, startPauseGameTimeButton));
        newOffenseShotClockButton.setOnClickListener(new RefreshShotClockListener(shotClock, SHOTCLOCK_OFFENSIVE_REBOUND, startPauseGameTimeButton));
        startPauseGameTimeButton.setOnClickListener(new StartStopGameListener(shotClock, startPauseGameTimeButton, editFieldsCheckbox));
        helpButton.setOnClickListener(new HelpListener(this));

        shotClock.addTextChangedListener(new AttackTimeEditText(editFieldsCheckbox, shotClock, this));
        minutesLeft.addTextChangedListener(new GameTimeEditText(editFieldsCheckbox, minutesLeft, secondsLeft));
        secondsLeft.addTextChangedListener(new GameTimeEditText(editFieldsCheckbox, minutesLeft, secondsLeft));

        editFieldsCheckbox.setOnCheckedChangeListener(new ChangeValuesCheckBox(this, editFieldsCheckbox));

        addShotClockSecondTextView.setOnClickListener(new AddOrRemoveShotclockTime(shotClock, ADD_SECOND.ADD_MINUTE, startPauseGameTimeButton));
        removeShotClockSecondTextView.setOnClickListener(new AddOrRemoveShotclockTime(shotClock, ADD_SECOND.REMOVE_MINUTE, startPauseGameTimeButton));
    }


    public Button getStartPauseGameTimeButton() {
        return this.startPauseGameTimeButton;
    }

}
