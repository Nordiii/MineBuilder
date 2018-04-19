package de.config;


import java.util.Optional;

interface IConfig<V,K> {
      void load();
      void save();
      <T> Class<T> dealsWith();
      boolean add(V value);
      boolean set(K key,V newValue);
      Optional<V> get(K id);
}
