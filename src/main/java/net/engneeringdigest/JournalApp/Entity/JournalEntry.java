package net.engneeringdigest.JournalApp.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
@Document("journal_entries")
@Data
public class JournalEntry {

    @Id
    private ObjectId id;
    public String title;
    public String description;
    public LocalDateTime date;

}
