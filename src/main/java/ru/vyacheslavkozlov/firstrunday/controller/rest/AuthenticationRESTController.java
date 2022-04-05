package ru.vyacheslavkozlov.firstrunday.controller.rest;

import cz.kacirekj.connector.DataConnector;
import cz.kacirekj.connector.LoginConnector;
import cz.kacirekj.domain.GarminSession;
import cz.kacirekj.exception.HttpErrorGarminException;
import cz.kacirekj.exception.LoginGarminException;
import cz.kacirekj.exception.TooManyRequestsGarminException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import ru.vyacheslavkozlov.firstrunday.config.security.JwtTokenProvider;
import ru.vyacheslavkozlov.firstrunday.dto.AuthRequestDTO;
import ru.vyacheslavkozlov.firstrunday.dto.RegistrationRequestDTO;
import ru.vyacheslavkozlov.firstrunday.entity.Account;
import ru.vyacheslavkozlov.firstrunday.service.impl.AccountDetailsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/runday")
public class AuthenticationRESTController {

    private final AuthenticationManager authenticationManager;
    private final AccountDetailsServiceImpl accountDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/login")
    public String getLoginPage(){
        try{
            LoginConnector loginConnector = new LoginConnector();
            GarminSession garminSession = loginConnector.login("vyacheslavkozlof@gmail.com", "Valentina181019");

            DataConnector dataConnector = new DataConnector(garminSession);
            Map<String,Object> userSummary = dataConnector.getUserSummary(LocalDate.now().minusDays(1));
            Map<String,Object> heartRates = dataConnector.getHeartRates(LocalDate.now().minusDays(1));

            System.out.println(userSummary);
            System.out.println(heartRates);
            System.out.println(garminSession);
        }  catch (TooManyRequestsGarminException e) {
            e.printStackTrace();
        } catch (LoginGarminException e) {
            e.printStackTrace();
        } catch (HttpErrorGarminException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequestDTO authRequestDTO){
        System.out.println("authenticationRESTController - " + authRequestDTO);
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getPassword()));
            Account account = accountDetailsService.findByEmail(authRequestDTO.getEmail());
            String token = jwtTokenProvider.createToken(authRequestDTO.getEmail(), account.getRole().name());
            System.out.println("authenticationRESTController token - " + token);
            Map<Object, Object> response = new HashMap<>();
            response.put("email", authRequestDTO.getEmail());
            response.put("token", token);
            return ResponseEntity.ok(response);
        }
        catch (AuthenticationException e){
            return new ResponseEntity<>("Invalid email/password!", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAuthority('account:read')")
    public void logout(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(httpRequest, httpResponse, null);
    }

    @GetMapping("/registration")
    public String getRegistrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registrationAccount(@RequestBody RegistrationRequestDTO registrationRequestDTO){
        boolean checkAccount = accountDetailsService.checkAccount(registrationRequestDTO.getEmail());
        if (checkAccount) {
            return ResponseEntity.ok("Такой аккаунт уже существует!");
        }

        Account accountFromDB = accountDetailsService.createAccount(registrationRequestDTO);
        return ResponseEntity.ok(accountFromDB);
    }
}
