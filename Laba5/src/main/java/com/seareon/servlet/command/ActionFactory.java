package com.seareon.servlet.command;

import com.seareon.servlet.command.client.CommandEnum;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 * Класс - фабрика команд
 *
 * @author Misha Ro
 * @version 1.0
 */
public class ActionFactory {
    /**
     * Метод достаёт из объекта запроса имя команды и возвращает по этому имени экземпляр соответствующей команды
     *
     * @param request
     * @return
     */
    public ActionCommand defineCommand(HttpServletRequest request) {
        /**
         * Экземпляр логера
         */
        final Logger log = Logger.getLogger(ActionCommand.class);

        ActionCommand current;
        String action = request.getParameter("command");

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            log.error(e);
            current = CommandEnum.WRONG.getCurrentCommand();
        }

        return current;
    }
}
