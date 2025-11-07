/* 
 * Material didático destinado ao curso
 * de Desenvolvimento Web do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.restfulwebservice.user;

import java.io.Serializable;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Especificação de um usuário do sistema.
 * 
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.2, 07/11/2025
 */
@Entity
// Introduz a capacidade de processamento do objeto com formato XML
@XmlRootElement
public class SystemUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;

    @Column(length = 65, nullable = false)
    private String name;

    @Column(length = 255, unique = true, nullable = false)
    private String email;

    @Column(length = 18, nullable = false)
    private String plainPassword;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public SystemUser() {
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
    }

    public SystemUser(Long id, String name, String email, String plainPassword) {
        this();
        this.id = id;
        this.name = name;
        this.email = email;
        this.plainPassword = plainPassword;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="hashCode/equals/toString">
    @Override
    public int hashCode() {
        int hash = 7;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SystemUser)) {
            return false;
        }

        SystemUser other = (SystemUser) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "io.github.guisso.restfulwebservice.user.SystemUser[ id=" + id + ", email=" + email + " ]";
    }
    //</editor-fold>

}
