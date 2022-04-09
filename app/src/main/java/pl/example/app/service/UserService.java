package pl.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.example.app.model.CustomUser;
import pl.example.app.model.UserInfo;
import pl.example.app.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomUser getUser(int id) {
        return userRepository.findUserById(id);
    }

    public UserInfo getUserInfo(int id) {
        return userRepository.findUserInfoById(id);
    }

    public CustomUser addNewUser(CustomUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public CustomUser findUser(CustomUser user) throws UsernameNotFoundException {
        CustomUser user1 = userRepository.findUserByUsername(user.getUsername());
        if (user1 == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return user1;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final CustomUser user = userRepository.findUserByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        user.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role))});
        authorities.add(new SimpleGrantedAuthority("USER"));
        System.out.printf("%s\n", username);
        return User.withUsername(user.getUsername()).password(user.getPassword()).authorities(authorities).build();
    }
}
