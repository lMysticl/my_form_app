/**
 * @author Pavel Putrenkov
 */

var str_pattern_name = /^[а-яА-ЯЁёA-Za-z\-]+$/g;
var str_pattern_email = /^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$/g;
var str_pattern_password = /^[a-zA-Z0-9\--\-!\-@\-#\-$\-*\-.\-,\-(\-)\-+]+$/g;
var ch_pattern_name = /[а-яА-ЯЁёA-Za-z\-]+/g;
var ch_pattern_password = /[a-zA-Z0-9\--\-!\-@\-#\-$\-*\-.\-,\-(\-)\-+]+/g;
function validateEmail(obj) {
    if (obj.value.match(str_pattern_email) != null && obj.value.length < 81) {
        $('#' + obj.id).removeClass("wrong");
    }
};

function validatePassword(obj) {

    var input_name = obj.value;
    var ch = input_name[input_name.length - 1];

    if (ch.match(ch_pattern_password) == null) {
        var output_name = input_name.slice(0, -1);
        $('#' + obj.id).val(output_name);
    }

    if (obj.value.match(str_pattern_password) && obj.value.length > 5 && obj.value.length < 21) {
        $('#' + obj.id).removeClass("wrong");
    }
};