package br.com.fiap.global.model.user;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(name = "GS_JV_PERFIL")
@Getter @Setter
public class Perfil {

    @Id
    @GeneratedValue
    @Column(name = "cd_role")
    private Long id;

    @Column(name = "nm_perfil", nullable = false, unique = true)
    private String name;

    @Column(name = "ds_label", nullable = false)
    private String label;

}