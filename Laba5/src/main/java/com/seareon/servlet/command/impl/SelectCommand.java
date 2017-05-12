package com.seareon.servlet.command.impl;

import com.seareon.servlet.command.ActionCommand;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Этот класс выбирает из БД студентов
 *
 * @author Misha Ro
 * @version 1.0
 */
public class SelectCommand extends ActionCommand {
    /**
     * Экземпляр логера
     */
    public static final Logger log = Logger.getLogger(SelectCommand.class);

    /**
     * Метод достаёт из экземпляра http запроса значения по которым производится поиск студетнов.
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

            if(!paramName.equals("command") && request.getParameter(paramName).length() > 0) {
                params.put(paramName, request.getParameter(paramName));
            }
        }

        doSelect(request, params);
    }
}
