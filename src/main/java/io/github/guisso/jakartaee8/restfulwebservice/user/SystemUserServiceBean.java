/* 
 * Material didático destinado ao curso
 * de Desenvolvimento Web do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.jakartaee8.restfulwebservice.user;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Bean de sessão (EJB) com operações concretas a serem realizadas
 * na entidade usuário do sistema.
 * 
 * @author Luis Guisso <luis dot guisso at ifnmg dot edu dot br>
 * @version 0.1, 26/11/2021
 */
@Stateless
public class SystemUserServiceBean 
        implements SystemUserServiceBeanLocal {
    
    @PersistenceContext(name = "SistemaPU")
    private EntityManager em;

    @Override
    public SystemUser findUserById(Long id) {
        return em.find(SystemUser.class, id);
    }

    @Override
    public void create(SystemUser systemUser) {
        em.persist(systemUser);
    }

    @Override
    public void update(SystemUser systemUser) {
        em.merge(systemUser);
    }

    @Override
    public void delete(SystemUser systemUser) {
        // Mesclagem requerida para recuperação da entidade
        // no contexto da transação, caso já não esteja nele
        em.remove(em.contains(systemUser) 
                ? systemUser 
                : em.merge(systemUser));
    }
    
}
