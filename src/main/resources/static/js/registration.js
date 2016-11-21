// var flagForMatchingPsw = -1; //0 = not maching, 1 = matching
// var flagForExistingEmail = -1; // 0= already exists, 1 = does not exist
//
// function registrationJSON(email, firstName, lastName, password) {
//     return JSON.stringify({
//         "email" : email,
//         "firstName" : firstName,
//         "lastName" : lastName,
//         "password" : password
//     });
// }
// $(document).on('click','#registerBtn', function () {
//     if (flagForMatchingPsw == 1 && flagForExistingEmail == 1) {
//
//         var email = $("#email").val();
//         var fName = $("#firstName").val();
//         var lName = $("#lastName").val();
//         var psw = $("#pwd").val();
//         var pswR = $("#repeatPwd").val();
//
//         var regexEmail = new RegExp("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$");
//
//         if(fName == ""){
//             toastr.error("Please enter a first name!");
//         }
//         else if(lName == ""){
//             toastr.error("Please enter a last name!");
//         }
//         else if(email == ""){
//             toastr.error("Please enter an e-mail!");
//         }
//         else if(psw == ""){
//             toastr.error("Please enter a password!");
//         }
//         else if(pswR == ""){
//             toastr.error("Please enter a repeated password!");
//         }
//         else if(!regexEmail.test(email)){
//             toastr.error("Wrong email format!");
//         }
//         else {
//             $.ajax({
//                 type: "POST",
//                 data: registrationJSON(email, fName, lName, psw),
//                 contentType: "application/json",
//                 dataType : "json",
//                 url: "/api/users",
//                 success: function (data) {
//                     toastr.info("Email with activation link is sent to your email address!");
//                     setTimeout(function () {
//                         window.location.href = "login.html";
//                     }, 2000);
//                 },
//                 error: function (e) {
//                     alert('Error: ' + e);
//                 }
//             });
//         }
//     }
// });
//
// $(document).on('blur',"#email",function () {
//     var email = $("#email").val();
//
//     if (email != ""){
//         $.ajax({
//             type: "POST",
//             data: JSON.stringify(email),
//             contentType: "application/json",
//             url: "/api/users/user-email",
//             success: function (data) {
//                 if (data === "exists"){
//                     flagForExistingEmail = 0;
//                     toastr.error("Email addres "+ email+" is already using. Try with another one.");
//                 }
//                 else{
//                     flagForExistingEmail = 1;
//                 }
//             },
//             error: function (e) {
//                 alert('Error: ' + e);
//             }
//         });
//     }
// })
//
// $(document).on('blur',"#repeatPwd",function () {
//     var psw = $("#pwd").val();
//     var pswR = $("#repeatPwd").val();
//
//     if (psw === pswR){
//         flagForMatchingPsw = 1;
//         console.log("Matching passwords");
//     }
//     else{
//         flagForMatchingPsw = 0;
//         toastr.error("Not matching passwords!");
//     }
// })
//
// $(document).on('click','#loginLink',function () {
//     setTimeout(function () {
//         window.location.href = "login.html";
//     }, 1000);
// })