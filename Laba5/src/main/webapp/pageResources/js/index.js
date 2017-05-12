/**
 * Created by Misha Ro on 18.03.2017.
 */
function clickRadio() {
    if(document.getElementById("add").checked) {
        setDisabled(["id"], true);
        setDisabled(["first_name", "last_name", "group_name", "department", "average_mark", "city", "address", "phone"],
            false);
        document.getElementById("send-form").method = "post";
    } else if(document.getElementById("select").checked) {
        setDisabled(["id", "first_name", "last_name", "group_name", "department", "average_mark", "city", "address",
                "phone"], false);
        document.getElementById("send-form").method = "get";
    } else if(document.getElementById("update").checked) {
        setDisabled(["id", "first_name", "last_name", "group_name", "department", "average_mark", "city", "address",
            "phone"], false);
        document.getElementById("send-form").method = "post";
    } else {
        setDisabled(["id"], false);
        setDisabled(["first_name", "last_name", "group_name", "department", "average_mark", "city", "address", "phone"],
            true);
        document.getElementById("send-form").method = "post";
    }
}

function setDisabled(array, value) {
    for(var indexArray = 0; indexArray < array.length; indexArray++) {
        document.getElementById(array[indexArray]).disabled = value;
    }
}

function select(idNumber) {
    var idArray = ["id", "first_name", "last_name", "group_name", "department",
        "average_mark", "city", "address", "phone"];
    cancelBackgroundColor();

    for(var i=0; i < idArray.length; i++) {
        var value = document.getElementById(idArray[i] + '-' + idNumber).innerHTML;
        document.getElementById(idArray[i]).value = value.replace(/^\s*(.*)\s*$/, '$1');
        document.getElementById(idArray[i] + '-' + idNumber).style.backgroundColor = 'red';
        document.getElementById(idArray[i] + '-' + idNumber).style.color = 'white';
    }
}

function cancelBackgroundColor() {
    var tags = document.getElementsByTagName('td');

    for(var i = 0; i < tags.length; i++) {
        tags[i].style.backgroundColor = 'white';
        tags[i].style.color = 'black';
    }
}