package com.goit.finalproject.user;

import com.goit.finalproject.role.Role;
import com.goit.finalproject.role.RoleRepository;
import com.goit.finalproject.role.RoleService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto getUserById(Long id) {
        return userMapper.mapEntityToDto(userRepository.findById(id).orElseThrow());
    }

    public void createUser(User user) {
        Role userRole = roleService.getRoleByName("USER");
        user.setRoles(new HashSet<Role>(Collections.singletonList(userRole)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void updateUser(User user) {
        setPassword(user, user.getPassword());
        userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void setPassword(User user, String password) {
        String passwordHash = passwordEncoder.encode(password);
        user.setPassword(passwordHash);
    }

    public String getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public Long getUserId() {
        return userRepository.findUserByUsername(getUsername()).getId();
    }

    @Override
    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =
                userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("There is no such user");
        }
        return userMapper.mapEntityToDto(user);
    }
}
