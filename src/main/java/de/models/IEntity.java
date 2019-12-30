package de.models;

public abstract class IEntity implements Exp {

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

    public abstract int getExp();

    public abstract void decrease();

    public abstract String getID();
}
