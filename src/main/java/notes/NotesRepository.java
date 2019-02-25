package notes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class NotesRepository implements IRepository<Notes>{
    private List<Notes> notes = Collections.synchronizedList(new ArrayList<>());
    public Notes create(Notes note) {
        notes.add(note);
        note.setId(note.getId());
        return note;
    }
    public boolean delete(Long id) {
        return notes.removeIf(note -> note.getId().equals(id));
    }
    public List<Notes> findAll() {
        return notes;
    }
    public Optional<Notes> findById(Long id) {
        return notes.stream().filter(e -> e.getId().equals(id)).findFirst();
    }
    public List<Notes> findByBody(String body) {
        List<Notes> foundNotes = notes.stream()
        .filter(note -> note.getContent().toLowerCase().contains(body.toLowerCase()))
        .collect(Collectors.toList());
        return foundNotes;
    }
    public int getCount() {
        return notes.size();
    }
    public void clear() {
        notes.clear();
    }
}