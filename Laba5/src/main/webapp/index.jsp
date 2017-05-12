<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width"/>
        <title>AIPOS_5</title>
        <link rel="stylesheet" href="pageResources/css/index.css"/>
    </head>
    <body>
    <header>
        <form method="post" action="/controller" id="send-form">
            <table>
                <tr>
                    <td>
                        Select
                    </td>
                    <td class="end">
                        <input type="radio" name="command" value="select" id="select" onclick="clickRadio()" checked="checked"/>
                    </td>
                    <td>
                        Add
                    </td>
                    <td class="end">
                        <input type="radio" name="command" value="add" id="add" onclick="clickRadio()"/>
                    </td>
                    <td>
                        Update
                    </td>
                    <td class="end">
                        <input type="radio" name="command" value="update" id="update" onclick="clickRadio()"/>
                    </td>
                    <td>
                        Delete
                    </td>
                    <td class="end">
                        <input type="radio" name="command" value="delete" id="delete" onclick="clickRadio()"/>
                    </td>
                </tr>
            </table>
            <table>
                <tr>
                    <td>
                        id (namber):
                    </td>
                    <td class="end">
                        <input type="text" name="id" id="id" pattern="\d{1,7}"/>
                    </td>
                    <td>
                        first name:
                    </td>
                    <td class="end">
                        <input type="text" name="first_name" id="first_name"/>
                    </td>
                    <td>
                        last name:
                    </td>
                    <td class="end">
                        <input type="text" name="last_name" id="last_name"/>
                    </td>
                    <td>
                        group (number - xxxxxx, not null):
                    </td>
                    <td class="end">
                        <input type="text" name="group_name" id="group_name" pattern="\d{6}"/>
                    </td>
                    <td>
                        department:
                    </td>
                    <td class="end">
                        <input type="text" name="department" id="department"/>
                    </td>
                </tr>
            </table>
            <table>
                <tr>
                    <td>
                        average mark (double - x.x | x):
                    </td>
                    <td class="end">
                        <input type="text" name="average_mark" id="average_mark" pattern="(10|[0-9])(.[0-9]+)*"/>
                    </td>
                    <td>
                        city:
                    </td>
                    <td class="end">
                        <input type="text" name="city" id="city"/>
                    </td>
                    <td>
                        address (not null):
                    </td>
                    <td class="end">
                        <input type="text" name="address" id="address"/>
                    </td>
                    <td>
                        phone (not null):
                    </td>
                    <td class="end">
                        <input type="text" name="phone" id="phone"/>
                    </td>
                </tr>
            </table>
            <div class="execute">
                <input type="submit" value="execute"/>
            </div>
        </form>
    </header>
    <main>
        <div class="answer-massage">
            ${answerMessage}
        </div>
        <c:if test="${not empty students}">
            <div class="scroll" style="margin: 0 auto; width: 1500px; height: 400px; overflow: auto;">
                <table class="resul-select">
                    <tr>
                        <th>id</th>
                        <th>first name</th>
                        <th>last name</th>
                        <th>group</th>
                        <th>department</th>
                        <th>average mark</th>
                        <th>city</th>
                        <th>address</th>
                        <th>phone</th>
                    </tr>
                    <c:forEach items="${students}" var="student" varStatus="loopCount">
                        <div itemscope itemtype="http://schema.org/Person">
                        <tr>
                            <td id="id-${loopCount.count}" onclick="select(${loopCount.count})">
                                ${student.getId()}
                            </td>
                            <td id="first_name-${loopCount.count}" onclick="select(${loopCount.count})">
                                <div itemprop="additionalName">
                                        ${student.firstName}
                                </div>
                            </td>
                            <td id="last_name-${loopCount.count}" onclick="select(${loopCount.count})">
                                <div itemprop="familyName">
                                        ${student.lastName}
                                </div>
                            </td>
                            <td id="group_name-${loopCount.count}" onclick="select(${loopCount.count})">
                                ${student.getGroup()}
                            </td>
                            <td id="department-${loopCount.count}" onclick="select(${loopCount.count})">
                                ${student.department}
                            </td>
                            <td id="average_mark-${loopCount.count}" onclick="select(${loopCount.count})">
                                ${student.averageMark}
                            </td>
                            <div itemscope itemtype="http://schema.org/PostalAddress">
                                <td id="city-${loopCount.count}" onclick="select(${loopCount.count})">
                                    <div itemprop="addressLocality">
                                            ${student.city}
                                    </div>
                                </td>
                                <td id="address-${loopCount.count}" onclick="select(${loopCount.count})">
                                    <div itemprop="streetAddress">
                                            ${student.address}
                                    </div>
                                </td>
                            </div>
                            <td id="phone-${loopCount.count}" onclick="select(${loopCount.count})">
                                <span itemprop="telephone">
                                    ${student.phone}
                                </span>
                            </td>
                            </div>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>
    </main>
    <script type="text/javascript" src="pageResources/js/index.js"></script>
    </body>
</html>
