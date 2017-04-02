package de.baernreuther.basketballcountdowntimer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.baernreuther.basketballcountdowntimer.countdowntimer.AttackTimeCountdownTimer;
import de.baernreuther.basketballcountdowntimer.countdowntimer.GameTimeCountdownTimer;
import de.baernreuther.basketballcountdowntimer.listener.buttons.RefreshAttackTimeButton;
import de.baernreuther.basketballcountdowntimer.listener.buttons.StartGameButton;
import de.baernreuther.basketballcountdowntimer.listener.editboxes.AttackTimeEditText;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.gameTimeTextView)
    TextView gameTimeTextView;

    @BindView(R.id.startGameButton)
    Button startGameButton;

    @BindView(R.id.refreshAttackTime)
    Button refreshAttackTimeButton;

    @BindView(R.id.attackTime)
    EditText attackTime;

    @BindView(R.id.offensiveReboundButton)
    Button offensiveReboundButton;

    @BindView(R.id.editFieldsCheckbox)
    CheckBox editFieldsCheckbox;

    GameTimeCountdownTimer gameTimeCountdownTimer = null;
    AttackTimeCountdownTimer attackTimeCountdownTimer = null;


    //TODO Throw exception when attacktime lower 1 ore greater than 30.

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
        gameTimeCountdownTimer = new GameTimeCountdownTimer(100000,1000, gameTimeTextView);
        attackTimeCountdownTimer =  AttackTimeCountdownTimer.AttackTimeCountdownFactory(24, 1000, attackTime, gameTimeCountdownTimer);

        initializeFields();
        initializeListeners();
        editFieldsCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                initializeFields();
            }
        });


    }

    /*
        Initializes all the fields.
     */
    private void initializeFields(){
        attackTime.setEnabled(!attackTime.isEnabled());
    }

    /*
    Initializes all listeners.
     */
    private void initializeListeners() {
        refreshAttackTimeButton.setOnClickListener(new RefreshAttackTimeButton(attackTime, attackTimeCountdownTimer, gameTimeCountdownTimer, 24));
        offensiveReboundButton.setOnClickListener(new RefreshAttackTimeButton(attackTime, attackTimeCountdownTimer, gameTimeCountdownTimer, 14));
        startGameButton.setOnClickListener(new StartGameButton(attackTime, attackTimeCountdownTimer, gameTimeCountdownTimer));

        attackTime.addTextChangedListener(new AttackTimeEditText(editFieldsCheckbox, attackTime, gameTimeCountdownTimer, attackTimeCountdownTimer));
    }


}
