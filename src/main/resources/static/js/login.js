var formData;
/**
 * @author Pavel Putrenkov
 */
var email;
var password;
function test() {
$("#login-in-form").submit(function (event) {
    var formData = {
        "email": $("#email").val(),
        "password": $("#password").val()
    };


    $.post("/login", formData)
        .done(function (data) {
            console.log("login"+data);
        })
        .fail(function () {
            console.log("Something has gone wrong");
        });


})
}