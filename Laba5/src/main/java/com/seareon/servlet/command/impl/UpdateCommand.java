package com.seareon.servlet.command.impl;

import com.seareon.dao.StudentDAO;
import com.seareon.servlet.command.ActionCommand;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Этот класс обновдяет поля некоторой записи в БД
 *
 * @author Misha Ro
 * @version 1.0
 */
public class UpdateCommand extends ActionCommand {
    /**
     * Экземпляр логера
     */
    public static final Logger log = Logger.getLogger(UpdateCommand.class);

    /**
     * Метод достаёт из экземпляра http запроса значения, по которорым создаёт новый объект студента. Далее по id
     * обновляет уже существующую запись.
     *
     * @see ActionCommand#execute(HttpServletRequest)
     * @param request - экземпляр http запроса
     */
    @Override
    public void execute(HttpServletRequest request) {
        Integer id;

        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception e) {
            log.equals(e.getMessage());
            request.setAttribute("answerMessage", "Update students - 0");
            request.setAttribute("statusPage", "200");
            return;
        }

        Enumeration<String> paramsNames = request.getParameterNames();
        Map<String, String> params = new HashMap();

        while (paramsNames.hasMoreElements()) {
            String paramName = paramsNames.nextElement();

            if(!paramName.equals("command") && request.getParameter(paramName).length() > 0
                    && !paramName.equals("id")) {

                params.put(paramName, request.getParameter(paramName));
            }
        }

        try {
            StudentDAO studentDAO = new StudentDAO();

            if(studentDAO.update(params, id)) {
                request.setAttribute("answerMessage", "Update students - 1");
            } else {
                request.setAttribute("answerMessage", "Update students - 0");
            }

            selectAll(request);

            request.setAttribute("statusPage", "200");
        } catch (IOException | ClassNotFoundException e) {
            log.error(e);
            request.setAttribute("statusPage", "500");
        } catch (SQLException e) {
            log.error(e);
        }
    }
}
