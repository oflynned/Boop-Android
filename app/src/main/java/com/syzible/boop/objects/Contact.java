package com.syzible.boop.objects;

/**
 * Created by ed on 22/11/2017.
 */

public class Contact {
    private String forename, surname, number;
    private long lastTimeRung;

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
