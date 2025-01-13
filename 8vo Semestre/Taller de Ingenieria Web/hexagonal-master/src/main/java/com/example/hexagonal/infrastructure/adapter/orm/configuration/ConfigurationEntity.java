package com.example.hexagonal.infrastructure.adapter.orm.configuration;


import com.example.hexagonal.domain.enums.ConfigurationKeyEnum;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "configurations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConfigurationEntity {

    @Id
    @Column
    private String idString;

    @Column
    private String data;

}
