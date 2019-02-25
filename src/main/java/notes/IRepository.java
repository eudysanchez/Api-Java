package notes;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {
    public Notes create(T element);
    public Optional<Notes> findById(Long id);
    public List<Notes> findAll();
    public List<Notes> findByBody(String body);
 }