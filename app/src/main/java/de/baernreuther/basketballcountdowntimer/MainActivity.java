package de.baernreuther.basketballcountdowntimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.ToggleButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.baernreuther.basketballcountdowntimer.countdowntimer.AttackTimeCountdownTimer;
import de.baernreuther.basketballcountdowntimer.countdowntimer.GameTimeCountdownTimer;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.gameTimeTextView)
    TextView gameTimeTextView;

    @BindView(R.id.startGameButton)
    ToggleButton startGameButton;

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

        //TODO Refactor and remove the onclick listener implementations here
        startGameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(attackTimeCountdownTimer.hasStoped()){
                    attackTimeCountdownTimer = AttackTimeCountdownTimer.AttackTimeCountdownFactory(24, 1000, attackTime, gameTimeCountdownTimer);
                }

                if(gameTimeCountdownTimer.isPaused()){
                   gameTimeCountdownTimer.start();
                   attackTimeCountdownTimer.start();
               }else{
                   gameTimeCountdownTimer.pause();
                   attackTimeCountdownTimer.pause();
               }

            }
        });

        refreshAttackTimeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                attackTimeCountdownTimer.cancel();
                attackTimeCountdownTimer = AttackTimeCountdownTimer.AttackTimeCountdownFactory(24,1000,attackTime,gameTimeCountdownTimer);
                attackTimeCountdownTimer.start();
            }
        });

        offensiveReboundButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                attackTimeCountdownTimer.cancel();
                attackTimeCountdownTimer = AttackTimeCountdownTimer.AttackTimeCountdownFactory(14,1000,attackTime,gameTimeCountdownTimer);
                attackTimeCountdownTimer.start();
            }
        });

        editFieldsCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                initializeFields();
            }
        });

        attackTime.addTextChangedListener(new TextWatcher() {
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
                if(editFieldsCheckbox.isChecked() && gameTimeCountdownTimer.isPaused()) {
                    String time = attackTime.getText().toString();
                    if(time.equals("")){
                        time = "0";
                    }
                    attackTimeCountdownTimer.cancel();
                    attackTimeCountdownTimer = AttackTimeCountdownTimer.AttackTimeCountdownFactory(Integer.valueOf(time), 1000, attackTime, gameTimeCountdownTimer);
                }
            }
        });
    }

    /*
        Initializes all the fields.
     */
    private void initializeFields(){
        attackTime.setEnabled(!attackTime.isEnabled());
    }



}
