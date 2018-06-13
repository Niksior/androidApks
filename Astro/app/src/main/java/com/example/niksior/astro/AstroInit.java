package com.example.niksior.astro;

import android.content.SharedPreferences;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class AstroInit {
    private AstroCalculator astroCalculator;
    private AstroCalculator.Location location;
    private AstroDateTime astroDateTime;


    public AstroInit(SharedPreferences sharedPreferences) {

        Calendar calendar = Calendar.getInstance();

        astroDateTime = new AstroDateTime(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND),
                getTimeZone(),
                false
        );

        location = new AstroCalculator.Location(
                Double.valueOf(sharedPreferences.getString("sze", "1")),
                Double.valueOf(sharedPreferences.getString("wys", "1"))
        );

        astroCalculator = new AstroCalculator(astroDateTime, location);
    }

    private int getTimeZone(){
        Calendar c = Calendar.getInstance();

        TimeZone z = c.getTimeZone();
        int offset = z.getRawOffset();

        if (z.inDaylightTime(new Date())) {
            offset = offset + z.getDSTSavings();
        }
        return offset / 1000 / 60 / 60;
    }

    public AstroCalculator getAstroCalculator() {
        return astroCalculator;
    }


}
