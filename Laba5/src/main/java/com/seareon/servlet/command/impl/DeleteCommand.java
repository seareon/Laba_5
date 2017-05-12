package com.seareon.servlet.command.impl;

import com.seareon.dao.StudentDAO;
import com.seareon.servlet.command.ActionCommand;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.ResultSet;

/**
 * Этот класс удаляет некоторого студента в БД
 *
 * @author Misha Ro
 * @version 1.0
 */
public class DeleteCommand extends ActionCommand {
    /**
     * Экземпляр логера
     */
    public static final Logger log = Logger.getLogger(DeleteCommand .class);

    /**
     * Метод достаёт из экземпляра http запроса значения {@link com.seareon.entity.Student#id} студетна и удаляет
     * студента по этому значению.
     *
     * @see ActionCommand#execute(HttpServletRequest)
     * @param request - экземпляр http запроса
     */
    @Override
    public void execute(HttpServletRequest request) {
        String id = request.getParameter("id");

        try {
            StudentDAO studentDAO = new StudentDAO();

            if(studentDAO.delete(Integer.parseInt(id))) {
                request.setAttribute("answerMessage", "Delete students - 1");
            } else {
                request.setAttribute("answerMessage", "Delete students - 0");
            }

            selectAll(request);

            request.setAttribute("statusPage", "200");
        } catch (IOException | ClassNotFoundException e) {
            log.error(e);
            request.setAttribute("statusPage", "500");
        } catch (Exception e) {
            log.error(e);
            request.setAttribute("answerMessage", "Delete students - 0");
            request.setAttribute("statusPage", "200");
        }
    }
}
