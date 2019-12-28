package de.config;


import de.models.IEntity;

interface IConfig {
      void load();

      void save();

      <T> boolean dealsWith(Class<T> eventClass);

      boolean add(IEntity value);

      boolean set(IEntity key, IEntity newValue);

      IEntity get(IEntity key);
}
