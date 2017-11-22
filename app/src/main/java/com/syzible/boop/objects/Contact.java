package com.syzible.boop.objects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ed on 22/11/2017.
 */

public class Contact {
    private String forename, surname, number;
    private long lastTimeRung;

    public Contact(JSONObject o) throws JSONException {
        this.forename = o.getString("forename");
        this.surname = o.getString("surname");
        this.number = o.getString("number");
    }

    public Contact(String forename, String surname, String number) {
        this.forename = forename;
        this.surname = surname;
        this.number = number;
    }

    public void setLastTimeRung(long lastTimeRung) {
        this.lastTimeRung = lastTimeRung;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public String getNumber() {
        return number;
    }

    public long getLastTimeRung() {
        return lastTimeRung;
    }
}
