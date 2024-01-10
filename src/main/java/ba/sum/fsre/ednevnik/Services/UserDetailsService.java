package ba.sum.fsre.ednevnik.Services;

import ba.sum.fsre.ednevnik.models.User;
import ba.sum.fsre.ednevnik.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class
UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);

        return new ba.sum.fsre.ednevnik.models.UserDetails(user);
    }

}