package de.models;

public abstract class IEntity {

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

    public abstract int getExp();

    public abstract void decrease();
}
