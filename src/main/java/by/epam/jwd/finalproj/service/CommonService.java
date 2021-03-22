package by.epam.jwd.finalproj.service;

import java.util.List;
import java.util.Optional;

public interface CommonService<T> {
    Optional<List<T>> findAll();

    Optional<T> save(T dto);

    Optional<T> update(T dto);

    boolean delete(int id);
}
