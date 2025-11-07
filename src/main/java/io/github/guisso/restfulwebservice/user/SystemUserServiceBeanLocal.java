/* 
 * Material didático destinado ao curso
 * de Desenvolvimento Web do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.restfulwebservice.user;

import jakarta.ejb.Local;

/**
 * Interface local para especificação de métodos para beans de sessão (EJBs) que
 * visem realizar transações no SGBDR.
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.2, 07/11/2025
 */
@Local
public interface SystemUserServiceBeanLocal {

    public SystemUser findUserById(Long id);

    public void create(SystemUser systemUser);

    public void update(SystemUser systemUser);

    public void delete(SystemUser systemUser);
}
