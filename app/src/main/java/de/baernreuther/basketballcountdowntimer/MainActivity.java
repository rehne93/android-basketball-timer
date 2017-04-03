package de.baernreuther.basketballcountdowntimer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.baernreuther.basketballcountdowntimer.countdowntimer.AttackTimeCountdownTimer;
import de.baernreuther.basketballcountdowntimer.countdowntimer.GameTimeCountdownTimer;
import de.baernreuther.basketballcountdowntimer.listener.buttons.RefreshAttackTimeButton;
import de.baernreuther.basketballcountdowntimer.listener.buttons.StartGameButton;
import de.baernreuther.basketballcountdowntimer.listener.editboxes.AttackTimeEditText;
import de.baernreuther.basketballcountdowntimer.listener.editboxes.GameTimeEditText;

public class MainActivity extends AppCompatActivity {


    //TODO Rename button on click
    @BindView(R.id.startGameButton)
    Button startGameButton;

    @BindView(R.id.refreshAttackTime)
    Button refreshAttackTimeButton;

    @BindView(R.id.offensiveReboundButton)
    Button offensiveReboundButton;

    @BindView(R.id.minutesLeft)
    EditText minutesLeft;

    @BindView(R.id.secondsLeft)
    EditText secondsLeft;

    @BindView(R.id.attackTime)
    EditText attackTime;

    @BindView(R.id.editFieldsCheckbox)
    CheckBox editFieldsCheckbox;



    //TODO Throw exception when attacktime lower 1 ore greater than 30.

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);

        initializeTimers();
        initializeFields();
        initializeListeners();
        editFieldsCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                initializeFields();
            }
        });


    }


    private void initializeTimers() {
        GameTimeCountdownTimer.createUniqueInstance(100, 1000, minutesLeft, secondsLeft);
        AttackTimeCountdownTimer.createUniqueInstance(24, 1000, attackTime);
    }
    /*
        Initializes all the fields.
     */
    private void initializeFields(){

        attackTime.setEnabled(!attackTime.isEnabled());
        minutesLeft.setEnabled(!minutesLeft.isEnabled());
        secondsLeft.setEnabled(!secondsLeft.isEnabled());
    }

    /*
    Initializes all listeners.
     */
    private void initializeListeners() {
        refreshAttackTimeButton.setOnClickListener(new RefreshAttackTimeButton(attackTime, 24));
        offensiveReboundButton.setOnClickListener(new RefreshAttackTimeButton(attackTime, 14));
        startGameButton.setOnClickListener(new StartGameButton(attackTime));

        attackTime.addTextChangedListener(new AttackTimeEditText(editFieldsCheckbox, attackTime));
        minutesLeft.addTextChangedListener(new GameTimeEditText(editFieldsCheckbox, minutesLeft, secondsLeft));
        secondsLeft.addTextChangedListener(new GameTimeEditText(editFieldsCheckbox, minutesLeft, secondsLeft));
    }


}
