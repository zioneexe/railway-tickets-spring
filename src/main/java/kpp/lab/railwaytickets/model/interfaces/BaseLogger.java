package kpp.lab.railwaytickets.model.interfaces;

import java.util.List;

public interface BaseLogger<T> {

    void write(T object);

    List<T> readAll();
}
