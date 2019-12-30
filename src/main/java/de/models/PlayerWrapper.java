package de.models;

import de.events.AbsEvent;
import de.events.EventsDAO;

import java.util.HashMap;
import java.util.Map;

public class PlayerWrapper {
    private final HashMap<Class<? extends AbsEvent>, HashMap<String, IEntity>> playerSpecificConfig = new HashMap<>();
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
                .forEach(event -> playerSpecificConfig.put(event.getClass(), new HashMap<>()));
    }

    public void updateItem(Class<? extends AbsEvent> event, IEntity item) {
        playerSpecificConfig.get(event).put(item.getID(), item);
    }

    public int getExp(Class<? extends AbsEvent> event, IEntity item) {
        Map<String, IEntity> entities = playerSpecificConfig.putIfAbsent(event, new HashMap<>());

        entities.putIfAbsent(item.getID(), item);

        return entities.get(item.getID()).getExp();
    }

}
