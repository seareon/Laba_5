package com.seareon.servlet.command.impl;

import com.seareon.servlet.command.ActionCommand;
import javax.servlet.http.HttpServletRequest;

/**
 * Этот класс возвращает ошибку, так как команда не была найдена.
 *
 * @author Misha Ro
 * @version 1.0
 */
public class WrongAction extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request) {
        request.setAttribute("statusPage", "500");
    }
}
