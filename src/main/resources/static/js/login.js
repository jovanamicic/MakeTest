function loginJSON(email,password) {
    return JSON.stringify({
        "email" : email,
        "password" : password
    });
}

$(document).on('click','#loginBtn', function () {
    var email = $("#email").val();
    var psw = $("#pwd").val();
    var regexEmail = new RegExp("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$");

    if (email == ""){
        toastr.error("Please enter an email.")
    }
    else if (psw == ""){
        toastr.error("Please enter a password.");
    }
    else if(!regexEmail.test(email)){
        toastr.error("Wrong email format!");
    }
    else{
        $.ajax({
            type: "POST",
            data: loginJSON(email, psw),
            contentType: "application/json",
            url: "/api/users/sessions",
            success: function (res, status, xhr) {
                var url = xhr.getResponseHeader("Location");
                var token = url.split('/').pop();
                setTimeout(function () {
                    window.location.href = "api/users/sessions/"+token;
                }, 2000);
            },
            error: function (xhr, e) {
                console.log(xhr.responseText);
                toastr.error("Wrong email or password. Please try again.");
                $("#pwd").val("");
            }
        });

    }

})

$(document).on('click','#registerLink',function () {
    setTimeout(function () {
        window.location.href = "registration.html";
    }, 1000);
})