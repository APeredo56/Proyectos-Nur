package dao;

public abstract class AbstractDao<T> {

    public abstract T get(int id);
}
