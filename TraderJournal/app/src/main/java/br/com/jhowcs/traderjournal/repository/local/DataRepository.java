package br.com.jhowcs.traderjournal.repository.local;

import java.util.List;

public interface DataRepository<T> {

    List<T> getAll();

    T getById(String id);

    long insert(T type);

    int delete(String id);

    void update(T type);

}
