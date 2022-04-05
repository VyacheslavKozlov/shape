package ru.vyacheslavkozlov.firstrunday.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vyacheslavkozlov.firstrunday.config.security.SecurityAccount;
import ru.vyacheslavkozlov.firstrunday.dto.RegistrationRequestDTO;
import ru.vyacheslavkozlov.firstrunday.entity.Account;
import ru.vyacheslavkozlov.firstrunday.entity.Role;
import ru.vyacheslavkozlov.firstrunday.entity.Status;
import ru.vyacheslavkozlov.firstrunday.repository.AccountRepository;

import java.util.List;

@Service("accountDetailsServiceImpl")
@AllArgsConstructor
public class AccountDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("AccountDetailsServiceImpl loadUserByUsername email = " + email);
        Account account = accountRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("Account not found!"));
        return SecurityAccount.fromUser(account);
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account createAccount(RegistrationRequestDTO registrationRequestDTO){
        Account account = new Account();
        account.setFirstName(registrationRequestDTO.getFirstName());
        account.setLastName(registrationRequestDTO.getLastName());
        account.setEmail(registrationRequestDTO.getEmail());
        account.setPassword(new BCryptPasswordEncoder(12).encode(registrationRequestDTO.getPassword()));
        account.setStatus(Status.ACTIVE);
        account.setRole(Role.USER);
        account.setEnable(true);
        return accountRepository.save(account);
    }

    public Account findByEmail(String email){
        return accountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    public boolean checkAccount(String email) {
        return accountRepository.findByEmail(email).isPresent();
    }
}
