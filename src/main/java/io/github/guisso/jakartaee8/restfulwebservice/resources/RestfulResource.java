/* 
 * Material didático destinado ao curso
 * de Desenvolvimento Web do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.jakartaee8.restfulwebservice.resources;

import io.github.guisso.jakartaee8.restfulwebservice.user.SystemUser;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import io.github.guisso.jakartaee8.restfulwebservice.user.SystemUserServiceBeanLocal;
import javax.ws.rs.DELETE;

/**
 * Caminhos com ações a serem executadas pelo <i>web service</i>.
 *
 * <p>
 * Referência para
 * <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Status">definição
 * dos códigos HTTP</a>.</p>
 *
 * <p>
 * Referência para
 * <a href="https://github.com/for-GET/http-decision-diagram">escolha do código
 * HTTP de resposta</a>.</p>
 *
 * @author Luis Guisso <luis dot guisso at ifnmg dot edu dot br>
 * @version 0.1, 26/11/2021
 */
@Path("resources")
public class RestfulResource {

    /**
     * Atributo para testes com verbos PUT e POST.
     */
    private static String message;

    /**
     * Fonte de dados para consulta do sistema.
     */
    @Inject
    private SystemUserServiceBeanLocal uSrv;

    /**
     * Teste de escuta do <i>web service</i> por parte do servidor.
     *
     * @return Texto plano como resposta do acesso ao recurso.
     */
    @GET
    @Path("ping")
    @Produces("text/plain")
    public String getPing() {
        return "Ping!";
    }

    /**
     * Especificação de uma mensagem disponível a outro recurso.
     *
     * @param message Definição de mensagem de teste.
     */
    @POST
    @PUT
    @Path("messages/{message}")
    @Consumes("text/plain")
    public void setMessage(
            @PathParam("message") String message) {
        System.out.println(">> setMessage:" + message);
        RestfulResource.message = message;
    }

    /**
     * Recuperação de mensagem de teste previamente definida.
     *
     * @return Retorno de previamente definida por aceso a outro recurso
     */
    @GET
    @Path("messages")
    @Produces("text/html")
    public String getMessage() {
        System.out.println(">> getMessage:" + message);
        return "<html><body><h1>"
                + RestfulResource.message
                + "</h1></body></html>";
    }

    /**
     * Retorna conteúdo HTML básico a partir de uma mensagem recebida.
     *
     * @param msg Mensagem a ser formatada
     * @return Conteúdo HTML contendo a mensagem formatada.
     */
    @GET
    @Path("htmlMessages/{msg}")
    @Produces("text/html")
    public String formatMessage(
            @PathParam("msg") String msg) {
        return "<html><body><h1>"
                + msg
                + "</h1></body></html>";
    }

    /**
     * Retorna conteúdo HTML básico a partir de uma mensagem e de uma <i>tag</i>
     * que a envolverá.
     *
     * @param tag Tag HTML que envolverá a mensagem recebida.
     * @param msg Conteúdo a ser formatado.
     * @return Conteúdo HTML com mensagem embutida em <i>tag</i> recebida.
     */
    @GET
    @Path("customHtmlMessages/{msg}")
    @Produces(MediaType.TEXT_HTML)
    public String formatMessage(
            @DefaultValue("p")
            @QueryParam("tag") String tag,
            @PathParam("msg") String msg) {
        return "<html><body><" + tag + ">"
                + msg
                + "</" + tag + "></body></html>";
    }

    /**
     * Recupera um usuário do sistema no SGBDR.
     *
     * @param id Identificação do usuário do sistema a ser recuperado.
     * @return Usuário do sistema em formato JSON (padrão), XML ou texto plano
     * segundo a solicitação recebida.
     */
    @GET
    @Path("users/{id}")
    @Produces({MediaType.APPLICATION_JSON,
        MediaType.APPLICATION_XML,
        MediaType.TEXT_PLAIN})
    public Response getUser(@PathParam("id") Long id) {
        // Montagem manual para testes sem fonte de dados
//        SystemUser user = new SystemUser(
//                id,
//                "Guisso",
//                "luis.guisso@ifnmg.edu.br",
//                "asdf1234!"
//        );

        SystemUser user = uSrv.findUserById(id);

        return Response
                .status(Response.Status.OK)
                .entity(user)
                .build();
    }

    /**
     * Recupera um usuário do sistema no SGBDR em formato XML.
     *
     * @param id Identificação do usuário do sistema a ser recuperado.
     * @return Usuário do sistema em formato XML.
     */
    @GET
    @Path("users/{id}/xml")
    @Produces(MediaType.APPLICATION_XML)
    public Response getUserXml(@PathParam("id") Long id) {

        SystemUser user = uSrv.findUserById(id);

        return Response
                .status(Response.Status.OK)
                .entity(user)
                .build();
    }

    /**
     * Cria um novo usuário no SGBDR.
     *
     * @param name Nome do usuário.
     * @param email <i>Email</i> do usuário.
     * @param plainPassword Senha do usuário para acesso ao sistema.
     * @return Indicação de sucesso da operação.
     */
    @POST
    @Path("users/{name}/{email}/{plainPassword}")
    public Response createUser(
            @PathParam("name") String name,
            @PathParam("email") String email,
            @PathParam("plainPassword") String plainPassword
    ) {

        uSrv.create(new SystemUser(null, name, email, plainPassword));

        // TODO Verificar condições que podem gerar exceção e devolver resposta adequada
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    /**
     * Atualiza dados de um usuário pré-existente no SGBDR.
     *
     * @param id Identificação do usuário do sistema a ser atualizado.
     * @param name Nome do usuário.
     * @param email <i>Email</i> do usuário.
     * @param plainPassword Senha do usuário para acesso ao sistema.
     * @return Indicação de sucesso da operação.
     */
    @PUT
    @Path("users/{id}/{name}/{email}/{plainPassword}")
    public Response updateUser(
            @PathParam("id") Long id,
            @PathParam("name") String name,
            @PathParam("email") String email,
            @PathParam("plainPassword") String plainPassword
    ) {

        uSrv.update(new SystemUser(id, name, email, plainPassword));

        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    /**
     * Exclui um usuário do sistema no SGBDR.
     *
     * @param id Identificação do usuário do sistema a ser excluído.
     * @return Indicação de sucesso da operação.
     */
    @DELETE
    @Path("users/{id}")
    public Response removeUser(@PathParam("id") Long id) {

        try {
            uSrv.delete(new SystemUser(id, null, null, null));
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();

        } catch (Exception ex) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

    }

}
