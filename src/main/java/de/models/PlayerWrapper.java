package de.models;

import de.events.EventsDAO;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

class PlayerWrapper {
    private Player player;

    public boolean isAddFlag() {
        if(addFlag)
        {
            toggleFlag();
            return true;
        }
        return false;
    }

    public void toggleFlag() {
        this.addFlag = !addFlag;
    }

    private boolean addFlag;
    private HashMap<Class,ArrayList<Exp>> configs = new HashMap<>();

    public void updateItem(Class event,Exp item){
        configs.get(event).remove(item);
        configs.get(event).add(item);
    }

    public PlayerWrapper(Player player) {
        this.player = player;
        this.addFlag = false;
        EventsDAO.getInstance().getPluginEvents()
                .forEach(event->configs.put(event.getClass(),new ArrayList<>()));
    }

    public boolean matchPlayer(PlayerWrapper player) {
        return this.player.getUniqueId() == player.player.getUniqueId();

    }

    public <C> int getExp(Class<C> event,Exp item)
    {
        ArrayList<Exp> entities = configs.get(event);

        if(!entities.contains(item))
            entities.add(item);
        return entities.stream()
                .filter(item::equals)
                .mapToInt(Exp::getExp)
                .findAny()
                .orElse(0);
    }

}
