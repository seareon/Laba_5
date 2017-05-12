package com.seareon.servlet.command.impl;

import com.seareon.servlet.command.ActionCommand;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Misha Ro on 18.03.2017.
 */
public class IndexCommand extends ActionCommand {
    @Override
    public void execute(HttpServletRequest request) {
        request.setAttribute("statusPage", "200");
    }
}
