package hbv501g.ProjectOne.Services.Implementation;

import hbv501g.ProjectOne.Entities.User;
import hbv501g.ProjectOne.Repositories.UserRepository;
import hbv501g.ProjectOne.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User login(User user) {
        User doesExist = findByUsername(user.getUsername());
        if(doesExist != null){
            if(doesExist.getPassword().equals(user.getPassword())){
                return doesExist;
            }
        }
        return null;
    }

    @Override
    public HashSet<Long> addToFavorites(User user, Long id) {
        user.addToFavoriteRecipes(id);
        save(user);

        for (Long i:user.getFavoriteRecipes()) {
            System.out.println(i);
        }
        return null;
    }



    @Override
    public Boolean isFavorited(User user, Long id) {
        HashSet<Long> fav = user.getFavoriteRecipes();
        if(fav == null){
            return false;
        }
        return fav.contains(id);
    }


}
