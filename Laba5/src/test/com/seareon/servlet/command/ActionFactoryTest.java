package com.seareon.servlet.command;

import com.seareon.dao.StudentDAO;
import com.seareon.entity.Student;
import com.seareon.servlet.command.impl.*;
import com.seareon.util.ReaderProperties;
import com.seareon.util.Util;
import org.junit.Assert;
import org.junit.Test;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by Misha Ro on 18.03.2017.
 */


public class ActionFactoryTest {
    @Test
    public void defineCommand() throws Exception {
        ActionFactory actionFactory = new ActionFactory();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("command")).thenReturn("index");
        ActionCommand actionCommand = actionFactory.defineCommand(request);

        ActionCommand test = new IndexCommand();

        Assert.assertEquals(actionCommand, test);
    }

    @Test
    public void addCommand() throws Exception {
        ActionFactory actionFactory = new ActionFactory();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("command")).thenReturn("add");
        ActionCommand actionCommand = actionFactory.defineCommand(request);

        ActionCommand test = new AddCommand();

        Assert.assertEquals(actionCommand, test);
    }

    @Test
    public void deleteCommand() throws Exception {
        ActionFactory actionFactory = new ActionFactory();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("command")).thenReturn("delete");
        ActionCommand actionCommand = actionFactory.defineCommand(request);

        ActionCommand test = new DeleteCommand();

        Assert.assertEquals(actionCommand, test);
    }

    @Test
    public void getStudent() throws Exception {
        Student student = new Student();
        Map<String, String> params = new HashMap<>();
        StudentDAO studentDAO = mock(StudentDAO.class);
        when(studentDAO.select(params)).thenReturn(student);

        Student test = (Student) studentDAO.select(params);

        Assert.assertEquals(student, test);
    }

    @Test
    public void selectCommand() throws Exception {
        ActionFactory actionFactory = new ActionFactory();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("command")).thenReturn("select");
        ActionCommand actionCommand = actionFactory.defineCommand(request);

        ActionCommand test = new SelectCommand();

        Assert.assertEquals(actionCommand, test);
    }

    @Test
    public void updateCommand() throws Exception {
        ActionFactory actionFactory = new ActionFactory();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("command")).thenReturn("update");
        ActionCommand actionCommand = actionFactory.defineCommand(request);

        ActionCommand test = new UpdateCommand();

        Assert.assertEquals(actionCommand, test);
    }

    @Test
    public void testProperty() throws Exception {
        ReaderProperties.load("/jdbc/config.properties");
        String test = ReaderProperties.getProperty("db.login");
        String str = "root";

        Assert.assertEquals(str, test);
    }

    @Test
    public void getSelect() throws Exception {
        String[] params = {"id", "city", "address"};
        String test = Util.getSelect(params);
        String str = " where id = ? and city = ? and address = ?";

        Assert.assertEquals(str, test);
    }
}