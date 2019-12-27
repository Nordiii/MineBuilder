package de.config;


import de.models.IEntity;

interface IConfig<V,K> {
      void load();

      void save();

      <T> boolean dealsWith(Class<T> eventClass);

      boolean add(V value);

      boolean set(K key, V newValue);

      IEntity get(K id);
}
