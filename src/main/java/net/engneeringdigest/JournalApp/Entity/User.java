package net.engneeringdigest.JournalApp.Entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import net.engneeringdigest.JournalApp.Entity.JournalEntry;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")


@Data
@NoArgsConstructor
public class User {

    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    public String userName;
    @NonNull
    public String password;
    @DBRef
    private List<JournalEntry> journalEntries= new ArrayList<>();
    private List<String> roles;
    public void setDate(LocalDateTime now) {
    }
}
