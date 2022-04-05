package ru.vyacheslavkozlov.firstrunday.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.vyacheslavkozlov.firstrunday.entity.Account;
import ru.vyacheslavkozlov.firstrunday.service.impl.AccountDetailsServiceImpl;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/runday")
public class AccountController {

    private final AccountDetailsServiceImpl accountDetailsService;

    @GetMapping("/accounts")
    @PreAuthorize("hasAuthority('account:write')")
    public List<Account> getAllAccounts(){
        return accountDetailsService.getAllAccounts();
    }

    @GetMapping("/profile")
    public String getProfilePage(){
        return "profile";
    }
}
