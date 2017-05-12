package com.seareon.dao;

import com.seareon.entity.Student;
import com.seareon.util.ReaderProperties;
import com.seareon.util.Util;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.sql.*;
import java.util.*;
/**
 * Класс взамодействует с БД.
 *
 * @author Misha Ro
 * @version 1.0
 */
public class StudentDAO {
    /**
     * Экземпляр логера
     */
    final Logger log = Logger.getLogger(StudentDAO.class);

    private Connection connection;
    private static final String GENERIC_SQL_SELECT = "select * from students";
    private static final String SQL_DELETE = "delete from students where id = ?";
    private static final String GENERIC_SQL_INSERT = "insert into students";
    private static final String GENERIC_SQL_UPDATE = "update students set";

    /**
     * Конструктор - создание нового объекта
     */
    public StudentDAO() throws IOException, ClassNotFoundException, SQLException {
        ReaderProperties.load("/jdbc/config.properties");

        Class.forName("com.mysql.jdbc.Driver");

        connection = DriverManager.getConnection(ReaderProperties.getProperty("db.host"),
                ReaderProperties.getProperty("db.login"), ReaderProperties.getProperty("db.password"));
    }

    /**
     * Метод находит по параметрам студентов и возвращает их список
     *
     * @param params - параметры поиска
     * @return
     * @throws SQLException
     */
    public Object select(Map<String, String> params) throws SQLException {
        Set<String> setKeyParams = params.keySet();
        Object[] keyParams = setKeyParams.toArray();
        String query = GENERIC_SQL_SELECT + Util.getSelect(keyParams);

        try (PreparedStatement pSt = connection.prepareStatement(query)) {
            for(int indexParams = 0; indexParams < keyParams.length; indexParams++) {
                setValueToPreparedStatement(pSt, indexParams + 1, params.get(keyParams[indexParams]));
            }

            List<Student> list = new ArrayList<>();
            ResultSet resultSet = pSt.executeQuery();

            while (resultSet.next()) {
                Student student = new Student(resultSet);

                if(student != null) {
                    list.add(student);
                }
            }

            return list;
        }
    }

    /**
     * Метод обнавляет поля студента по id
     *
     * @param params
     * @param id
     * @return
     */
    public boolean update(Map<String, String> params, int id) {
        boolean answer;
        Set<String> setKeyParams = params.keySet();
        Object[] keyParams = setKeyParams.toArray();
        String query = GENERIC_SQL_UPDATE + Util.getUpdate(keyParams);

        try (PreparedStatement pSt = connection.prepareStatement(query)) {
            int indexParams;
            for (indexParams = 0; indexParams < keyParams.length; indexParams++) {
                setValueToPreparedStatement(pSt, indexParams + 1, params.get(keyParams[indexParams]));
            }

            pSt.setInt(indexParams + 1, id);

            if(pSt.executeUpdate() > 0) {
                answer =  true;
            } else {
                answer = false;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            answer = false;
        }

        return answer;
    }

    /**
     * Метод удаляет студента по id
     *
     * @param id
     * @return
     */
    public boolean delete(int id) {
        boolean answer;

        try (PreparedStatement pSt = connection.prepareStatement(SQL_DELETE)) {
            pSt.setInt(1, id);

            if (pSt.executeUpdate() > 0) {
                answer = true;
            } else {
                answer = false;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            answer = false;
        }

        return answer;
    }

    /**
     * Метод добавляет запись студента в БД.
     *
     * @param params
     * @return
     */
    public boolean insert(Map<String, String> params) {
        boolean answer;
        String query = GENERIC_SQL_INSERT + Util.getInsert(params);

        try (PreparedStatement pSt = connection.prepareStatement(query)) {
            int indexParams = 1;
            Iterator<String> it = params.keySet().iterator();

            while (it.hasNext()) {
                setValueToPreparedStatement(pSt, indexParams++, params.get(it.next()));
            }

            if(pSt.executeUpdate() > 0) {
                answer = true;
            } else {
                answer = false;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            answer = false;
        }

        return answer;
    }

    /**
     * Происходит установка переданного значения в SQL запрос.
     *
     * @param pSt
     * @param index
     * @param value
     * @throws SQLException
     */
    protected void setValueToPreparedStatement(PreparedStatement pSt, int index, String value) throws SQLException {
       if(Util.isInteger(value)) {
            pSt.setInt(index, Integer.parseInt(value));
        } else if(Util.isDouble(value)) {
            pSt.setDouble(index, Double.parseDouble(value));
        } else {
            pSt.setString(index, value);
        }
    }
}
