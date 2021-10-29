package com.ileiwe.ileiwe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LearningParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    @NotBlank @NotNull
    private String email;
    @Column(nullable = false)
    @NotNull @NotBlank
    private String password;
    private boolean enabled;
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @OneToMany(cascade =CascadeType.PERSIST)
    private List<Authority> authorities;

    public LearningParty(String email, String password, Authority authority){
      //  if(email.isEmpty() || password.isEmpty()){
          //  throw new IllegalArgumentException("email and password cannot be empty");
      //  }
        this.email=email;
        this.password=password;
        addAuthority(authority);
        //Enables should be false at first as User is not active yet
        this.enabled = false;

    }

    //POJO Class
    private void addAuthority(Authority authority){
        if(this.authorities == null){
            this.authorities= new ArrayList<>();
        }
        this.authorities.add(authority);
    }

}