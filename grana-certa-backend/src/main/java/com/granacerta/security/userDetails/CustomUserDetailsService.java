package com.granacerta.security.userDetails;


import com.granacerta.modules.authentication.domain.entity.UserCredential;
import com.granacerta.modules.authentication.domain.repository.UserCredentialRepository;
import com.granacerta.modules.authentication.infra.persistence.entity.UserCredentialEntity;
import com.granacerta.modules.user.domain.entity.User;
import com.granacerta.modules.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        User user = userRepository.findByEmailAndActiveTrue(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found."));

        return new CustomUserDetails(
                user.getId(),
                user.getEmail(),
                user.getUserRole()
        );
    }
}