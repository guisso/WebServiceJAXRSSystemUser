/* 
 * Material didático destinado ao curso
 * de Desenvolvimento Web do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.jakartaee8.restfulwebservice;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configura o contexto do <i>web service</i> na aplicação.
 *
 * @author Luis Guisso <luis dot guisso at ifnmg dot edu dot br>
 * @version 0.1, 26/11/2021
 */
@ApplicationPath("api")
public class JAXRSConfiguration extends Application {

}
