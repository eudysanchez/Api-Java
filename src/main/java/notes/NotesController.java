package notes;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/notes", produces = "application/json")
public class NotesController {

    @Autowired
    //@Qualifier(value="notesRepository")
    private IRepository<Notes> repository;

    // @RequestMapping("/greeting")
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Notes> createnotes(@RequestBody Notes note) {
        List<Notes> notes = repository.findAll();
        boolean idExists = notes.stream().filter(n -> n.getId().equals(note.getId())).findFirst().isPresent();
        if(idExists){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        else{
            Notes createdNote = repository.create(note);
            return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Notes> findOrderById(@PathVariable("id") Long id) {
        Optional<Notes> order = repository.findById(id);
        if (order.isPresent()) {
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Notes>> findAllNotes() {
        List<Notes> notes = repository.findAll();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @RequestMapping
    public ResponseEntity<Collection<Notes>> findOrderByBody(@RequestParam("query")String body) {
        List<Notes> orders = repository.findByBody(body);
        if (!orders.isEmpty()) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}