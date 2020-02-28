package com.bibliotheque.microservicemyusers.model;

import com.bibliotheque.microservicemyusers.security.BCryptManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Utilisateur implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String pseudo;
    @NotNull
    private String motDePasse;

    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<RoleEnum> roleEnums;

    public Utilisateur() {

        this.roleEnums = Collections.singletonList(RoleEnum.USER);
    }

    public Utilisateur(@NotNull String pseudo, @NotNull String motDePasse) {
        this.id = id;
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        if (!motDePasse.isEmpty()){
            this.motDePasse = BCryptManager.passwordEncoder().encode(motDePasse);
        }
        this.motDePasse = motDePasse;
    }

    public List<RoleEnum> getRoleEnums() {
        return roleEnums;
    }

    public void setRoleEnums(List<RoleEnum> roleEnums) {
        this.roleEnums = roleEnums;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        String roles = org.springframework.util.StringUtils.collectionToCommaDelimitedString(getRoleEnums().stream()
                .map(Enum::name).collect(Collectors.toList()));
        return AuthorityUtils.commaSeparatedStringToAuthorityList(String.valueOf(roleEnums));
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return pseudo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", roleEnums=" + roleEnums +
                '}';
    }
}
