package demo.app.controllers;

import demo.app.dtos.auth.AddRoleToUserDto;
import demo.app.dtos.auth.CreateRoleDto;
import demo.app.dtos.domain.CreateCategoryDto;
import demo.app.models.auth.Role;
import demo.app.models.domain.Category;
import demo.app.services.BookService;
import demo.app.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/v1/admin/")
public class AdminController {
    private final UserService userService;
    private final BookService bookService;

    public AdminController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @PostMapping("role")
    public ResponseEntity<Role> saveRole(@Valid @RequestBody CreateRoleDto roleDto){
        Role role = userService.saveRole(roleDto);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/admin/role").toUriString());
        return ResponseEntity.created(uri).body(role);
    }

    @PostMapping("user/role")
    public ResponseEntity<String> addRoleToUser(@Valid @RequestBody AddRoleToUserDto dto){
        userService.addRoleToUser(dto.username(), dto.roleName());
        return ResponseEntity.ok().body("Role has been added to user");
    }

    @PostMapping("category")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CreateCategoryDto categoryDto){
        Category category = bookService.createCategory(categoryDto);

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/v1/admin/category").toUriString());
        return ResponseEntity.created(uri).body("Category has been created");
    }
}
