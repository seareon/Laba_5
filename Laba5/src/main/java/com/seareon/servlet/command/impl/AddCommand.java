package com.seareon.servlet.command.impl;

import com.seareon.dao.StudentDAO;
import com.seareon.servlet.command.ActionCommand;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Этот класс добавляет некоторого студента в БД
 *
 * @author Misha Ro
 * @version 1.0
 *
 */
public class AddCommand extends ActionCommand {
    /**
     * Экземпляр логера
     */
    public static final Logger log = Logger.getLogger(AddCommand.class);

    /**
     * Метод достаёт из экземпляра http запроса значения, устанавливает их в обект
     * {@link com.seareon.entity.Student#Student(ResultSet)}. Созданный объект добавляется в БД.
     *
     * @see ActionCommand#execute(HttpServletRequest)
     * @param request - экземпляр http запроса
     */
    @Override
    public void execute(HttpServletRequest request) {
        Enumeration<String> paramsNames = request.getParameterNames();
        Map<String, String> params = new HashMap();

        while (paramsNames.hasMoreElements()) {
            String paramName = paramsNames.nextElement();

            if(!paramName.equals("command")) {
                params.put(paramName, request.getParameter(paramName));
            }
        }

        try {
            StudentDAO studentDAO = new StudentDAO();

            if(studentDAO.insert(params)) {
                request.setAttribute("answerMessage", "Insert students - 1");
            } else {
                request.setAttribute("answerMessage", "Insert students - 0");
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
