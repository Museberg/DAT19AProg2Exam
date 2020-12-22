function preventFormFromSending(submitForm){
    submitForm.submit(function (event){
        event.preventDefault(); // Prevents the form from being sent
        let name = $("#name").val(); // Getting value from <input...>
        let email = $("#email").val(); // Getting value from <input...>
        let supervisorId = $("#supervisor").val(); // Getting value from <input...>
        addStudent(name, email, supervisorId);
    });
}


function addStudent(name, email, supervisorId){ // AJAX request
    console.log("Add student called with: [" + name + "] [" + email + "] [" + supervisorId + "]");
    let addObject = {};
    addObject["name"] = name;
    addObject["email"] = email;
    addObject["supervisorId"] = supervisorId;
    $.ajax({
        url:"/addStudent",
        type:"POST",
        contentType:"application/JSON",
        data: JSON.stringify(addObject),
        success:function (data){
            console.log("SUCCESS response from server");
            $("#studentsTable").append("<tr> " +
                "<td> <input type='text' value='" + data.name + "'></td>" +
                "<td> <input type='email' value='" + data.email + "'></td>" +
                "<td> <select name='" + data.email + "Select'> " +
                "</select></td>" +
                "</tr>");

            let o = new Option("Teacher " + data.supervisorId, data.supervisorId);
            $('[name="' + data.email + 'Select"]').append(o);
        },
        error:function (data){
            console.log("ERROR response from server");
            //$("#userTD").html("ERROR i svar fra server");
        }
    });
}


function checkjQuery(){
    if(typeof jQuery != undefined){
        console.log("jQuery er loaded")
    }else {
        console.log("jQuery er IKKE loaded")
    }
}