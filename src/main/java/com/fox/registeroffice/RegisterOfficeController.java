package com.fox.registeroffice;

import com.fox.registeroffice.dto.ClientDto;
import com.fox.registeroffice.dto.SearchDto;
import com.fox.registeroffice.dto.UserDto;
import com.fox.registeroffice.service.ClientServiceProxy;
import com.fox.registeroffice.service.UserServiceProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class RegisterOfficeController {

    private final ClientServiceProxy clientService;
    private final UserServiceProxy userService;

    @Autowired
    public RegisterOfficeController(ClientServiceProxy clientService, UserServiceProxy userService) {
        this.clientService = clientService;
        this.userService = userService;
    }

    @RequestMapping(value = {"/login" })
    public String mainPage() {
        return "login";
    }

    @RequestMapping(value = { "/", "/index", "/register_office" }, method = RequestMethod.GET)
    public String registerOffice(Map<String, Object> model) {
        model.put("search", new SearchDto());
        return "register_office";
    }

    @RequestMapping("/register_client")
    public String registerClient(Map<String, Object> model) {
        model.put("client", new ClientDto());

        return "register_client";
    }

    @RequestMapping(value = "/save_client", method = RequestMethod.POST)
    public String saveClient(Map<String, Object> model, @Valid @ModelAttribute("client")ClientDto clientDto) {
        return tryToSaveClient(model, clientDto, "register_client");
    }

    @RequestMapping(value = "/update_client", method = RequestMethod.POST)
    public String updateClient(Map<String, Object> model, @Valid @ModelAttribute("client")ClientDto clientDto) {
        return tryToSaveClient(model, clientDto, "edit_client");
    }

    @RequestMapping(value = "/{clientId}/edit_client")
    public String editClient(Map<String, Object> model, @PathVariable(value = "clientId") Long clientId) {
        model.put("client", clientService.findClient(clientId));
        return "edit_client";
    }

    @RequestMapping(value = "/{clientId}/delete_client")
    public String deleteClient(Map<String, Object> model, @PathVariable(value = "clientId") Long clientId) {
        clientService.deleteClient(clientId);
        return "redirect:/register_office";
    }

    @RequestMapping(value="/find_clients",  method = RequestMethod.POST)
    public String findClients(Map<String, Object> model, @ModelAttribute("search")SearchDto searchRequest) {
        if (searchRequest.getSearchString().isEmpty()) {
            model.put("errorMessage", "Введите телефон и/или ФИ для поиска");
            return "redirect:/register_office";
        }

        List<ClientDto> clients = clientService.findClients(searchRequest.getSearchString());
        model.put("clients", clients);
        return "clients_list";
    }

    @RequestMapping("/create_user")
    public String createUser(Map<String, Object> model) {
        model.put("user", new UserDto());
        return "create_user";
    }

    @RequestMapping(value = "/save_user", method = RequestMethod.POST)
    public String saveUser(Map<String, Object> model, @Valid @ModelAttribute("user")UserDto userDto) {
        return tryToSaveUser(model, userDto, "create_user");
    }

    @RequestMapping("/users_list")
    public String usersList(Map<String, Object> model) {
        List<UserDto> users = userService.getUsersList();
        model.put("users", users);
        return "users_list";
    }

    @RequestMapping(value = "/{userId}/remove_user")
    public String removeUser(Map<String, Object> model, @PathVariable(value = "userId") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/users_list";
    }

    @RequestMapping(value = "/{userId}/edit_user")
    public String editUser(Map<String, Object> model, @PathVariable(value = "userId") Long userId) {
        UserDto userDto = userService.findUser(userId);
        userDto.setPassword(null);
        model.put("user", userDto);
        return "create_user";
    }

    @RequestMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

    private String tryToSaveClient(Map<String, Object> model, ClientDto clientDto, String pageToRedirect) {
        try {
            clientService.saveClient(clientDto);
        } catch (IllegalArgumentException e) {
            model.put("errorMessage", e.getMessage());
            model.put("client", clientDto);
            return pageToRedirect;
        }
        return "redirect:/register_office";
    }

    private String tryToSaveUser(Map<String, Object> model, UserDto userDto, String pageToRedirect) {
        try {
            userService.saveUser(userDto);
        } catch (IllegalArgumentException e) {
            model.put("errorMessage", e.getMessage());
            model.put("user", userDto);
            return pageToRedirect;
        }
        return "redirect:/register_office";
    }

}
