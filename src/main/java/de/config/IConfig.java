package de.config;


import de.events.AbsEvent;
import de.models.IEntity;


interface IConfig {
      void load();

      void save();

      Class<? extends AbsEvent> dealsWith();

      void add(IEntity value);

      boolean set(String key, IEntity newValue);

      IEntity get(IEntity key);

      void printConfig();
}
