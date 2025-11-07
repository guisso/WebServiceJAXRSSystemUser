/* 
 * Material didático destinado ao curso
 * de Desenvolvimento Web do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.restfulwebservice;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Configura o contexto do <i>web service</i> na aplicação.
 *
 * @author Luis Guisso &lt;luis dot guisso at ifnmg dot edu dot br&gt;
 * @version 0.2, 07/11/2025
 */
@ApplicationPath("api")
public class JAXRSConfiguration extends Application {

}
