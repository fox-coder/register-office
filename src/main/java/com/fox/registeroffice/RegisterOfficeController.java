package com.fox.registeroffice;

import com.fox.registeroffice.dto.ClientDto;
import com.fox.registeroffice.dto.SearchDto;
import com.fox.registeroffice.service.ClientService;
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

    private final ClientService clientService;

    @Autowired
    public RegisterOfficeController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(value = { "/", "/index", "/register_office" }, method = RequestMethod.GET)
    public String mainPage(Map<String, Object> model) {
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
        try {
            clientService.saveClient(clientDto);
        } catch (IllegalArgumentException e) {
            model.put("errorMessage", e.getMessage());
            model.put("client", clientDto);
            return "register_client";
        }

        return "redirect:/register_office";
    }

    @RequestMapping(value = "/{clientId}/edit_client")
    public String editClient(Map<String, Object> model, @PathVariable(value = "clientId") Long clientId) {
        model.put("client", clientService.findClient(clientId));
        return "edit_client";
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


}
