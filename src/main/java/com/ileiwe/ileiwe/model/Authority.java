package com.ileiwe.ileiwe.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Authority {

    @Id
    @GeneratedValue
    private UUID id; //UUID generates random string of unique value and is more secure
    @Enumerated(EnumType.STRING)
    private Role authority;

    public Authority(Role role){
        this.authority=role;
    }



}
