package net.engneeringdigest.JournalApp.Controller;

import net.engneeringdigest.JournalApp.Entity.JournalEntry;
import net.engneeringdigest.JournalApp.Entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import net.engneeringdigest.JournalApp.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/user")

public class UserController {
   @Autowired
   private UserService userService;
   private Map<String, User> user = new HashMap<>();

   @GetMapping ("/getAll")
   public ResponseEntity<?> getAll() {
       List<User> all=userService.getAllUser();
       if (all != null && !all.isEmpty()) {
           return new ResponseEntity<>(all, HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);

   }


    @PostMapping ("postData")
   public boolean postData(@RequestBody User myUser) {
       myUser.setDate(LocalDateTime.now());
       userService.saveUser(myUser);
        return true;
   }
    @GetMapping("id/{myId}")
    public ResponseEntity<?> getById(@PathVariable ObjectId myId) {
        Optional<User> User = userService.getById(myId);

        // Check if the journal entry exists
        if (User.isPresent()) {
            return ResponseEntity.ok(User.get()); // Return 200 OK with the entry
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 Not Found
        }
    }
    @DeleteMapping("id/{myId}")
    public boolean deleteById(@PathVariable ObjectId myId) {
        userService.deleteById(myId);
        return true;
    }
    @PutMapping("id/{Id}")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
