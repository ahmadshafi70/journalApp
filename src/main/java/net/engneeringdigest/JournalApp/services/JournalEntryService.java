package net.engneeringdigest.JournalApp.services;

import net.engneeringdigest.JournalApp.Entity.JournalEntry;
import net.engneeringdigest.JournalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    public List<JournalEntry> getAllJournalEntry(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getById(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    public boolean deleteById(ObjectId id){
        journalEntryRepository.deleteById(id);
        return true;
    }
    public boolean updateById(ObjectId id){
//        journalEntryRepository.save(id);
        return true;
    }
    public void saveJournalEntry(JournalEntry journalEntry){
         journalEntryRepository.save(journalEntry);
     }

}
