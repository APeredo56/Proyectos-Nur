package Data;


import java.util.List;

public interface IDataBase<T> {
    public void save(T model) ;
    public List<T> getAll();
    public T getByCode(String code);
}
