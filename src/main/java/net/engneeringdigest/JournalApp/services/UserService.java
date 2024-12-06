package net.engneeringdigest.JournalApp.services;

import net.engneeringdigest.JournalApp.Entity.User;
import net.engneeringdigest.JournalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public Optional<User> getById(ObjectId id){
        return userRepository.findById(id);
    }
    public boolean deleteById(ObjectId id){
        userRepository.deleteById(id);
        return true;
    }
    public boolean updateById(ObjectId id){
//        UserRepository.save(id);
        return true;
    }
    public void saveUser(User user){
        userRepository.save(user);
     }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
