package de.models;

import de.events.AbsEvent;
import de.events.EventsDAO;

import java.util.HashMap;

public class PlayerWrapper {
    private final HashMap<Class<? extends AbsEvent>, HashMap<String, IEntity>> configs = new HashMap<>();
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
                .forEach(event -> configs.put(event.getClass(), new HashMap<>()));
    }

    public void updateItem(Class<? extends AbsEvent> event, IEntity item) {
        configs.get(event).put(item.getID(), item);
    }

    public int getExp(Class<? extends AbsEvent> event, IEntity item) {
        System.out.println(configs);
        configs.putIfAbsent(event, new HashMap<>());

        HashMap<String, IEntity> entities = configs.get(event);

        entities.putIfAbsent(item.getID(), item);

        return entities.get(item.getID()).getExp();
    }

}
