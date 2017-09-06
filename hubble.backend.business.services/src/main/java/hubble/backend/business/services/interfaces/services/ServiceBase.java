package hubble.backend.business.services.interfaces.services;

import java.util.List;

public interface ServiceBase<T> {

    public T findById(String id);

    public List<T> findLast10Minutes();

    public List<T> findLastHour();

    public List<T> findLastDay();

    public List<T> findLastMonth();

    public List<T> findAll();

}
