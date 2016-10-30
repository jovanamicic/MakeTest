var flagForMatchingPsw = -1; //0 = not maching, 1 = matching
var flagForExistingEmail = -1; // 0= already exists, 1 = does not exist

function registrationJSON(email, firstName, lastName, password) {
    return JSON.stringify({
        "email" : email,
        "firstName" : firstName,
        "lastName" : lastName,
        "password" : password
    });
}
//TODO: Proveriti da li se email vec koristi
//TODO: Validacija forme i za ostala polja
$(document).on('click','#registerBtn', function () {

    if (flagForMatchingPsw == 1 && flagForExistingEmail == 1) {

        var email = $("#email").val();
        var fName = $("#firstName").val();
        var lName = $("#lastName").val();
        var psw = $("#pwd").val();

        $.ajax({
            type: "POST",
            data: registrationJSON(email, fName, lName, psw),
            contentType: "application/json",
            url: "/registerNewUser",
            success: function (data) {
                toastr.info("Email with activation link is sent to your email address!");
                setTimeout(function(){
                    window.location.href = "index.html";
                }, 2000);
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    }
})

$(document).on('blur',"#email",function () {
    var email = $("#email").val();

    if (email != ""){
        $.ajax({
            type: "POST",
            data: JSON.stringify(email),
            contentType: "application/json",
            url: "/checkIfEmailExists",
            success: function (data) {
                if (data === "exists"){
                    flagForExistingEmail = 0;
                    toastr.error("Email addres "+ email+" is already using. Try with another one.");
                }
                else{
                    flagForExistingEmail = 1;
                }
            },
            error: function (e) {
                alert('Error: ' + e);
            }
        });
    }
})

$(document).on('blur',"#repeatPwd",function () {
    var psw = $("#pwd").val();
    var pswR = $("#repeatPwd").val();

    if (psw === pswR){
        flagForMatchingPsw = 1;
        console.log("Matching passwords");
    }
    else{
        flagForMatchingPsw = 0;
        toastr.error("Not matching passwords!");
    }
})