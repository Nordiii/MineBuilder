package de.models;

import de.events.AbsEvent;
import de.events.EventsDAO;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerWrapper {
    private final HashMap<Class<? extends AbsEvent>, ArrayList<Exp>> configs = new HashMap<>();
    private String storedMaterial;

    public String getStoredMaterial() {
        return storedMaterial;
    }

    public boolean isAddFlag() {
        if (addFlag) {
            toggleFlag();
            return true;
        }
        return false;
    }

    public void toggleFlag() {
        this.addFlag = !addFlag;
    }

    private boolean addFlag;

    public void setStoredMaterial(String storedMaterial) {
        this.storedMaterial = storedMaterial;
    }

    public PlayerWrapper() {
        this.addFlag = false;
        EventsDAO.getInstance().getPluginEvents()
                .forEach(event -> configs.put(event.getClass(), new ArrayList<>()));
    }

    public void updateItem(Class<? extends AbsEvent> event, Exp item) {
        configs.get(event).remove(item);
        configs.get(event).add(item);
    }

    public <C> int getExp(Class<C> event, Exp item) {
        ArrayList<Exp> entities = configs.get(event);

        if (!entities.contains(item))
            entities.add(item);

        return entities.stream()
                .filter(item::equals)
                .mapToInt(Exp::getExp)
                .findAny()
                .orElse(0);
    }

}
