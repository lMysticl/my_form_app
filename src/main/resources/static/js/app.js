$(function () {
    var editingContactId;

    if (document.location.pathname == "/") {

        getAllContact();
    }

    if (document.location.search == "?error" && document.location.pathname == "/login") {
        alert("You enter invalid password or username")
    }

    $("#registration-form").submit(function (event) {
        var formData = {
            "username": $("#username").val(),
            "firstname": $("#firstname").val(),
            "lastname": $("#lastname").val(),
            "middlename": $("#middlename").val(),
            "password": $("#password").val()
        };

        if (!validateRegistrationForm(formData)) {
            event.preventDefault();
            return;
        }

        $.post("/registration", formData)
            .done(function (data) {
                $(location).attr("href", "/login")
            })
            .fail(function (data) {
                alert(data.responseText)
            });
        event.preventDefault();
    });

    $("#add-contact-form").submit(function (event) {
        var formData = {
            "firstname": $("#add_firstname").val(),
            "lastname": $("#add_lastname").val(),
            "middlename": $("#add_middlename").val(),
            "mobilePhone": $("#add_mobilePhone").val(),
            "homePhone": $("#add_homePhone").val(),
            "address": $("#add_address").val(),
            "email": $("#add_email").val()
        };

        if (!validateContact(formData)) {
            event.preventDefault();
            return;
        }

        $.post("/contacts/add", formData)
            .done(function (data) {
                insertDataToTable(data);
            })
            .fail(function () {
                alert("Something has gone wrong");
            });
        event.preventDefault();
    });

    $("#edit-contact-form").submit(function (event) {
        var formData = {
            "contactId": editingContactId,
            "firstname": $("#edit_firstname").val(),
            "lastname": $("#edit_lastname").val(),
            "middlename": $("#edit_middlename").val(),
            "mobilePhone": $("#edit_mobilePhone").val(),
            "homePhone": $("#edit_homePhone").val(),
            "address": $("#edit_address").val(),
            "email": $("#edit_email").val()
        };


        if (!validateContact(formData)) {
            event.preventDefault();
            return;
        }

        var childNodes = $("#" + editingContactId)[0].childNodes;
        childNodes[0].innerHTML = formData.firstname;
        childNodes[1].innerHTML = formData.lastname;
        childNodes[2].innerHTML = formData.middlename;
        childNodes[3].innerHTML = formData.mobilePhone;
        childNodes[4].innerHTML = formData.homePhone;
        childNodes[5].innerHTML = formData.address;
        childNodes[6].innerHTML = formData.email;

        $.post("/contacts/update", formData)
            .done(function (data) {
                alert("Contact edit")
            })
            .fail(function () {
                alert("Something has gone wrong");
            });

        event.preventDefault();

    });

    $("#table-body").on('click', '.delete-contact', function () {
        var contactId = {"contactId": $(this).closest('.table-row').attr("id")};
        $(this).closest('.table-row').remove();
        $.post("/contacts/delete", contactId);

    });

    $("#table-body").on('click', '.edit-contact', function () {
        editingContactId = $(this).closest('.table-row').attr("id");
        var childNodes = $(this).closest('.table-row')[0].childNodes;

        $("#edit_firstname").val(childNodes[1].innerHTML);
        $("#edit_lastname").val(childNodes[0].innerHTML);
        $("#edit_middlename").val(childNodes[2].innerHTML);
        $("#edit_mobilePhone").val(childNodes[3].innerHTML);
        $("#edit_homePhone").val(childNodes[4].innerHTML);
        $("#edit_address").val(childNodes[5].innerHTML);
        $("#edit_email").val(childNodes[6].innerHTML);
    });


    function validateRegistrationForm(formData) {
        var regExp = /^[a-zA-Z0-9]+$/i;
        if ((!regExp.test(formData.username)) || (formData.username.length < 3)) {
            alert("Username must contain English characters only, at least 3 characters, without special characters");
            return false;
        }
        if (formData.password.length < 5) {
            alert("Password must contain at least 5 characters");
            return false;
        }
        if (formData.firstname.length < 5) {
            alert("Firstname must contain at least 5 characters");
            return false;
        }
        if (formData.lastname.length < 5) {
            alert("Lastname must contain at least 5 characters");
            return false;
        }
        if (formData.middlename.length < 5) {
            alert("Middlename must contain at least 5 characters");
            return false;
        }
        return true;
    }

    function getAllContact() {
        $.get("/contacts/get-all")
            .done(function (data) {
                data.forEach(function (item) {
                    insertDataToTable(item)
                });
            })
            .fail(function (data) {
                alert(data.responseText)
            })
    }

    function insertDataToTable(data) {
        if (data.homePhone == null || data.homePhone == "") {
            data.homePhone = "-";
        }
        if (data.address == null || data.address == "") {
            data.address = "-";
        }
        if (data.email == null || data.email == "") {
            data.email = "-";
        }

        var myTable = $('#example').DataTable();

        myTable.row.add([

            data.contactId,
            data.firstname,
            data.lastname,
            data.middlename,
            data.mobilePhone,
            data.homePhone,
            data.address,
            data.email
        ]).draw();


        test();

        for (var i = 0; i < document.querySelectorAll('table td').length; i++) {
            document.querySelectorAll('table td')[i].onblur = function (event) {
                event.target.innerHTML = event.target.innerHTML.replace(/&nbsp;/g, '').replace(/ /g, '').replace(/(<br>)/g, "");
                getData(event);

            };

        }

    }


    function validateContact(formData) {
        if (formData.firstname.length < 4) {
            alert("Firstname must contain at least 4 characters");
            return false;
        }
        if (formData.lastname.length < 4) {
            alert("Lastname must contain at least 4 characters");
            return false;
        }
        if (formData.lastname.length < 4) {
            alert("Middlename must contain at least 4 characters");
            return false;
        }
        if (!(formData.email == null) && !(formData.email == "-") && !(formData.email == "")) {
            var regExp = /^[-._a-z0-9]+@(?:[a-z0-9][-a-z0-9]+\.)+[a-z]{2,6}$/i;
            if (!regExp.test(formData.email)) {
                alert("Please enter correct email");
                return false;
            }
        }
        return true;
    }

});


function getData(event) {
    var target = $(event.target);
    var parentRowData = target.parent('tr').children();
    var formData = {
        "contactId": parentRowData.eq(0).html(),
        "firstname": parentRowData.eq(1).html(),
        "lastname": parentRowData.eq(2).html(),
        "middlename": parentRowData.eq(3).html(),
        "mobilePhone": parentRowData.eq(4).html(),
        "homePhone": parentRowData.eq(5).html(),
        "address": parentRowData.eq(6).html(),
        "email": parentRowData.eq(7).html()
    };

    $.post("/contacts/update", formData)
        .done(function (data) {
            console.log("Contact edit");
        })
        .fail(function () {
            console.log("Something has gone wrong");
        });

}

$(document).ready(function () {
    var table = $('#example').DataTable();

    $('#example tbody').on('click', 'tr', function () {
        $(this).toggleClass('selected');
    });

    $('#deleteContact').click(function () {
        console.log(table.rows('.selected').data().length + ' row(s) selected');
        for (var i = 0; i < table.rows('.selected').data().length; i++) {
            console.log(table.rows('.selected').data()[i][0]);
            var contactId = {"contactId": table.rows('.selected').data()[i][0]};
            $.post("/contacts/delete", contactId);
        }

        table.rows('.selected').remove();
        table.draw();

    });
});


function test() {
    var rows = document.querySelectorAll('table tr');

    for (var i = 1; i < rows.length; i++) {
        var cell = rows[i].childNodes;
        for (var j = 0; j < cell.length; j++) {
            // var id = ['id', 'fname', 'lname', 'mname', 'mphone', 'hphone', 'address', 'email'];
            var id = ['1', '2', '3', '4', '5', '6', '7', '8'];
            cell[j].setAttribute('data', id[j] + i);
            cell[j].setAttribute('contenteditable', 'true');
        }
    }
}