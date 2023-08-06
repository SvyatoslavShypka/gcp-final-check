package com.goit.finalproject.user;

import com.goit.finalproject.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Secured("ADMIN")
    @GetMapping("/users")
    public String showAllUsers(Model model) {
        System.out.println("userService.getUsername() = " + userService.getUsername());
        System.out.println("userService.getUserId() = " + userService.getUserId());
        User user = userService.getUserMapper()
                .mapDtoToEntity(userService.getUserById(userService.getUserId()));
        System.out.println("roleService.getAllRoles() = " + roleService.getAllRoles());
        System.out.println("user.getRoles() = " + user.getRoles());
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @Secured("ADMIN")
    @GetMapping("/user/edit")
    public String showAddUserForm(UserDto userDto) {
        return "add-user";
    }

    @Secured("ADMIN")
    @PostMapping("/user/edit")
    public String addUser(UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            return "add-user";
        }
        User user = userService.getUserMapper().mapDtoToEntity(userDto);
        userService.createUser(user);
        return "redirect:/users";
    }

    @Secured("ADMIN")
    @GetMapping("/user/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        UserDto userDto = userService.getUserById(id);
        model.addAttribute("user", userDto);
        return "update-user";
    }

    @Secured("ADMIN")
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Long id, UserDto userDto, BindingResult result) {
        if (result.hasErrors()) {
            userDto.setId(id);
            return "update-user";
        }
        userService.updateUser(userService.getUserMapper().mapDtoToEntity(userDto));
        return "redirect:/users";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
