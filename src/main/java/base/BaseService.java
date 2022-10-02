package base;

import entity.Account;

import java.util.List;
import java.util.Optional;

public interface BaseService<E extends BaseEntity> {

    BaseRepository<E> getRepository();

    default void saveOrUpdate(E entity){
        getRepository().saveOrUpdate(entity);
    }

    default Optional<E> loadById(Long id) {
        return getRepository().loadById(id);
    }

    default Optional<List<E>> loadAll() {
        return getRepository().loadAll();
    }

    default void delete(E entity) {
        getRepository().delete(entity);
    }

}
