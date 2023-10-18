package com.example.lab05.Repository;

public interface Repository<K> {
    public void create(K k);

    public K read(Long id);

    public void update(K k);

    public void delete(K k);

}
