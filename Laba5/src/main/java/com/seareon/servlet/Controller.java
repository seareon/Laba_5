package com.seareon.servlet;

import com.seareon.servlet.command.ActionCommand;
import com.seareon.servlet.command.ActionFactory;
import com.seareon.util.ReaderProperties;
import org.apache.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс-контроллер. Берёт экземпляр {@link ActionCommand} и передаёт ему {@link HttpServletRequest}. Перенаправляет
 * ответ клиенту.
 *
 * @author Misha Ro
 * @version 1.0
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
    /**
     * Экземпляр логера
     */
    public static final Logger log = Logger.getLogger(Controller.class);

    /**
     * Метод обрабатывает get запросы
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.info("get\r\n" + request + "\r\n\r\n\r\n");
        processRequest(request, response);
    }

    /**
     * Метод обрабатывает post запросы
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.info("post\r\n" + request + "\r\n\r\n\r\n");
        processRequest(request, response);
    }

    /**
     * Метод берёт экземпляр {@link ActionCommand} и передаёт ему {@link HttpServletRequest}. Перенаправляет
     * ответ клиенту.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        command.execute(request);

        if(!(request.getAttribute("statusPage")).equals("200")) {
            ReaderProperties.load("messages.properties");
            request.setAttribute("errorMessage",
                    ReaderProperties.getProperty("error.code." + request.getAttribute("statusPage")));

            log.info(request.getAttribute("statusPage") + "\r\n" +
                    ReaderProperties.getProperty("error.code." + request.getAttribute("statusPage")) + "\r\n\r\n\r\n");
            response.sendError(Integer.parseInt((String) request.getAttribute("statusPage")));
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");

            log.info("200\r\n"+ request +"\r\n\r\n\r\n");
            dispatcher.forward(request, response);
        }
    }
}
