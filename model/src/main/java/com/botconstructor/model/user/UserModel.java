package com.botconstructor.model.user;

import com.botconstructor.model.BotModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Entity
@Table(
        name = "user_model",
        indexes = @Index(name = "idx_username", columnList = "user_name")  // Создаёт индекс на столбце owner_id
)
public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;

    @Column(name = "user_name", unique = true)
    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @OneToMany(mappedBy = "owner")
    private List<BotModel> bots;

    public UserModel(String userName, String password) {
        this.password = password;
        this.userName = userName;
    }

    protected UserModel() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    public void setUserName(@NotEmpty String userName) {
        this.userName = userName;
    }

    public void setPassword(@NotEmpty String password) {
        this.password = password;
    }
}
