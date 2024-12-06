package net.engneeringdigest.JournalApp.repository;

import net.engneeringdigest.JournalApp.Entity.JournalEntry;
import net.engneeringdigest.JournalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);
    void deleteByUserName(String username);
}
