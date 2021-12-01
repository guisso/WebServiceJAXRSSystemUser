/* 
 * Material didático destinado ao curso
 * de Desenvolvimento Web do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.jakartaee8.restfulwebservice.user;

import javax.ejb.Local;

/**
 * Interface local para especificação de métodos para beans de sessão (EJBs)
 * que visem realizar transações no SGBDR.
 * 
 * @author Luis Guisso <luis dot guisso at ifnmg dot edu dot br>
 * @version 0.1, 26/11/2021
 */
@Local
public interface SystemUserServiceBeanLocal {

    public SystemUser findUserById(Long id);
    public void create(SystemUser systemUser);
    public void update(SystemUser systemUser);
    public void delete(SystemUser systemUser);
}
