package com.eve.emphasoft.user.entity;

import com.eve.emphasoft.exchange.entity.Exchange;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Exchange> exchanges;

    public UserEntity(List<Exchange> operations) {
        this.exchanges = operations;
    }
}
