package net.engneeringdigest.JournalApp.Controller;

import net.engneeringdigest.JournalApp.Entity.JournalEntry;
import net.engneeringdigest.JournalApp.services.JournalEntryService;
import net.engneeringdigest.JournalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.*;


@RestController
@RequestMapping("/journal")

public class JournalEntryControllerV2 {
   @Autowired
   private JournalEntryService journalEntryService;
   @Autowired
   private UserService userService;
   private Map<String, JournalEntry> journalEntries = new HashMap<>();

   @GetMapping ("/getAll")
   public  ResponseEntity<?>  getAll() {
       List<JournalEntry> all=journalEntryService.getAllJournalEntry();
       if (all != null && !all.isEmpty()) {
           return new ResponseEntity<>(all, HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);


   }
   @PostMapping ("postData")
   public boolean postData(@RequestBody JournalEntry myEntry) {

       myEntry.setDate(LocalDateTime.now());
       journalEntryService.saveJournalEntry(myEntry);
       
        return true;
   }
    @GetMapping("id/{myId}")
    public ResponseEntity<?> getById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.getById(myId);

        // Check if the journal entry exists
        if (journalEntry.isPresent()) {
            return ResponseEntity.ok(journalEntry.get()); // Return 200 OK with the entry
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 Not Found
        }
    }
    @DeleteMapping("id/{myId}")
    public boolean deleteById(@PathVariable ObjectId myId) {
        journalEntryService.deleteById(myId);
        return true;
    }
    @PutMapping("id/{Id}")
    public JournalEntry updateById(@PathVariable ObjectId Id, @RequestBody JournalEntry newEntry) {
        JournalEntry old = journalEntryService.getById(Id).orElse(null);
        if (old != null) {
            if (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
                old.setTitle(newEntry.getTitle());
            }
            if (newEntry.getDescription() != null && !newEntry.getDescription().isEmpty()) {
                old.setDescription(newEntry.getDescription());
            }
            journalEntryService.saveJournalEntry(old);
        }
        return old; // Return null if not found or updated entry
    }


}
