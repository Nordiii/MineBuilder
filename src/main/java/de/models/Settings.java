package de.models;

class Settings {
    private boolean expDropEnabled;
    private boolean moneyDropEnabled;

    public Settings(boolean exp,boolean money)
    {
        expDropEnabled=exp;
        moneyDropEnabled = money;
    }

    public boolean isExpDropEnabled() {
        return expDropEnabled;
    }

    public void setExpDropEnabled(boolean expDropEnabled) {
        this.expDropEnabled = expDropEnabled;
    }



    public boolean isMoneyDropEnabled() {
        return moneyDropEnabled;
    }

    public void setMoneyDropEnabled(boolean moneyDropEnabled) {
        this.moneyDropEnabled = moneyDropEnabled;
    }




}
