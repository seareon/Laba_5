package com.seareon.servlet.command;

import com.seareon.dao.StudentDAO;
import com.seareon.entity.Student;
import com.seareon.servlet.command.impl.SelectCommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Этот класс является абстрактным и наследуется конкретными классами команд
 *
 * @author Misha Ro
 * @version 1.0
 */
public abstract class ActionCommand {
    /**
     * Экземпляр логера
     */
    public static final Logger log = Logger.getLogger(SelectCommand.class);

    /**
     * абстрактный метод, который, при реализации,
     *
     * @param request
     */
    public abstract void execute(HttpServletRequest request);

    /**
     * Находим всех студентов
     *
     * @param request
     */
    protected void selectAll(HttpServletRequest request) {
        Enumeration<String> paramsNames = request.getParameterNames();
        Map<String, String> params = new HashMap();

        doSelect(request, params);
    }

    /**
     * Находим студентов по параметрам. Если их нет, то в ответе возвращаем сообщение о их отсутствии, иначе массив
     * студентов
     *
     * @param request
     * @param params
     */
    protected void doSelect(HttpServletRequest request, Map<String, String> params) {
        try {
            StudentDAO studentDAO = new StudentDAO();

            List<Student> students = (List<Student>) studentDAO.select(params);
            if(students.isEmpty()) {
                request.setAttribute("answerMessage", "Not found records");
            } else {
                request.setAttribute("students", students);
            }

            request.setAttribute("statusPage", "200");
        } catch (IOException |ClassNotFoundException e) {
            log.error(e);
            request.setAttribute("statusPage", "500");
        } catch (SQLException e) {
            log.error(e);
        }
    }

    /**
     * Переопределение метода из класса {@link Object}
     * @return
     */
    @Override
    public int hashCode() {
        return 58723;
    }

    /**
     * Переопределение метода из класса {@link Object}
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        return true;
    }
}

