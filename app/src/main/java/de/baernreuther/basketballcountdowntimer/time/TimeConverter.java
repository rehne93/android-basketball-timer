package de.baernreuther.basketballcountdowntimer.time;

import android.support.annotation.NonNull;


/**
 * Created by René Bärnreuther on 02.04.2017.
 * Converts time methods into a MM:SS or just SS format, depending on which time is used.
 */

public  class TimeConverter {

    /**
     * Converts the time into a human readable form MM:SS.
     * @param millisLeft time left
     * @return a string containing the time left in the MM:SS format.
     */
    @NonNull
    public static String getMinutesAndSecondsLeft(long millisLeft){
        StringBuilder timeLeft = new StringBuilder();
        int secondsLeft = (int) millisLeft/1000;

        int minutesLeft = 0;
        while(secondsLeft >= 60){
            minutesLeft++;
            secondsLeft-=60;
        }
        if(minutesLeft < 10) {
            timeLeft.append("0");
        }
        timeLeft.append(minutesLeft);
        timeLeft.append(":");
        if(secondsLeft < 10){
            timeLeft.append("0");
        }
        timeLeft.append(secondsLeft);
        return timeLeft.toString();
    }

    /**
     * Converts the attacktime left into a SS format.
     * @param millisLeft the number of milliseconds left
     * @return the attacktime left in a SS format.
     */
    @NonNull
    public static String getAttackTimeLeft(long millisLeft){
        StringBuilder timeLeft = new StringBuilder();
        int secondsLeft = (int) millisLeft/1000;
        if(secondsLeft < 10){
            timeLeft.append("0");
        }
        timeLeft.append(secondsLeft);
        return timeLeft.toString();
    }

    /**
     * Calculates the seconds, given in minute and second format.
     *
     * @param minutes minutes
     * @param seconds seconds
     * @return number of seconds
     */
    public static int getSeconds(int minutes, int seconds) {
        return minutes * 60 + seconds;
    }

}
