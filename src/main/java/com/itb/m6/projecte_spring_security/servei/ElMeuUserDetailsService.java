package com.itb.m6.projecte_spring_security.servei;

import com.itb.m6.projecte_spring_security.model.Usuari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ElMeuUserDetailsService implements UserDetailsService {


    @Autowired
    private ServeiUsuaris serveiUsuaris;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuari u= serveiUsuaris.consultaPerNom(s);
        User.UserBuilder builder=null;
        if(u!=null){
            builder=User.withUsername(s);
            builder.disabled(false);
            builder.password(u.getPassword());
            builder.roles(u.getRol()); //ADMIN o USER
        }
        else throw new UsernameNotFoundException("Usuari no trobat");
        return builder.build();
    }

}
