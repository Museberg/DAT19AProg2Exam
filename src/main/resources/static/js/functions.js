function preventAddFormFromSending(submitForm){
    submitForm.submit(function (event){
        event.preventDefault(); // Prevents the form from being sent
        let name = $("#name").val();
        let email = $("#email").val();
        let supervisorId = $("#supervisor").val();
        addStudent(name, email, supervisorId);
    });
}


function addStudent(name, email, supervisorId){ // AJAX request
    //console.log("Add student called with: [" + name + "] [" + email + "] [" + supervisorId + "]");
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
            console.log("SUCCESS response from server med id : " + data.id);
            // Adding Student row to table
            $("#studentsTable").append("<tr id='" + data.id + "'> " +
                "<td> <input type='text' value='" + data.name + "' id='Name" + data.id + "'></td>" +
                "<td> <input type='email' value='" + data.email + "' id='Email" + data.id + "'></td>" +
                "<td> <select name='" + data.email + "Select' id='Supervisor" + data.id + "'> " +
                "</select></td>" +
                "</tr>");

            // Adding all Supervisor options for new user added via ajax
            $("#supervisor option").each(function()
            {
                let o = new Option("Teacher " + $(this).val(), $(this).val());
                // Making sure the selected option is also selected in the list
                if($(this).val() == data.supervisorId){
                    o = new Option("Teacher " + $(this).val(), $(this).val(), true, true);
                }
                $('[name="' + data.email + 'Select"]').append(o);

            });

            // Adding update button
            let ub = $('<td> <form id=Update' + data.id + '><button form=Update' + data.id + ' type="submit" class="btn btn-primary">Update</button> </form> </td>');
            $('#' + data.id).append(ub);

            // Adding delete button
            let db = $('<td> <form id=Delete' + data.id + '><button form=Delete' + data.id + ' type="submit" class="btn btn-danger">Delete</button> </form> </td>');
            $('#' + data.id).append(db);
        },
        error:function (data){
            console.log("ERROR response from server");
        }
    });
}


function deleteStudent(id){
    console.log("Delete student called with: [" + id + "]");
    let deleteObject = {};
    deleteObject["id"] = id;
    $.ajax({
        url:"/deleteStudent",
        type:"POST",
        contentType:"application/JSON",
        data: JSON.stringify(deleteObject),
        success:function (data){
            // Removing row
            $("#" + data.id).remove();
            console.log("SUCCESS response from server");
        },
        error:function (data){
            console.log("ERROR response from server");
        }
    });
}

function updateStudent(id, name, email, supervisorId){
    //console.log("Update called with: [" + id + "] [" + name + "] [" + email + "] [" + supervisorId + "]");
    let updateObject = {};
    updateObject["id"] = id;
    updateObject["name"] = name;
    updateObject["email"] = email;
    updateObject["supervisorId"] = supervisorId;
    $.ajax({
        url:"/updateStudent",
        type:"POST",
        contentType:"application/JSON",
        data: JSON.stringify(updateObject),
        success:function (data){
            // Nothing needs to be done here, as the changes are already rendered on the page
            // Only change in the DB needs to happen - handled by the RestController
            console.log("SUCCESS response from server");
        },
        error:function (data){
            console.log("ERROR response from server");
        }
    });
}

function getAll(){
    let Student = new Object();
    console.log("GET ALL CALLED");
    $.ajax({
        type : 'POST',
        url : '/getAll',
        dataType : 'json',
        data : JSON.stringify(Student),
        contentType : 'application/json',
        success:function (data){
            // Removing all rows (except for header row)
            $("#studentsTable").find("tr:gt(0)").remove();
            // Drawing all rows again...
            for (let i = 0, len = data.length; i < len; ++i){
                let s = data[i];

                $("#studentsTable").append("<tr id='" + s.id + "'> " +
                    "<td> <input type='text' value='" + s.name + "' id='Name" + s.id + "'></td>" +
                    "<td> <input type='email' value='" + s.email + "' id='Email" + s.id + "'></td>" +
                    "<td> <select name='" + s.email + "Select' id='Supervisor" + s.id + "'> " +
                    "</select></td>" +
                    "</tr>");

                // Adding all supervisor options
                $("#supervisor option").each(function()
                {
                    let o = new Option("Teacher " + $(this).val(), $(this).val());
                    // Making sure the selected option is also selected in the list
                    if($(this).val() == s.supervisorId){
                        o = new Option("Teacher " + $(this).val(), $(this).val(), true, true);
                    }
                    $('[name="' + s.email + 'Select"]').append(o);

                });

                // Adding update button
                let ub = $('<td> <form id=Update' + s.id + '><button form=Update' + s.id + ' type="submit" class="btn btn-primary">Update</button> </form> </td>');
                $('#' + s.id).append(ub);

                // Adding delete button
                let db = $('<td> <form id=Delete' + s.id + '><button form=Delete' + s.id + ' type="submit" class="btn btn-danger">Delete</button> </form> </td>');
                $('#' + s.id).append(db);
            }
            console.log("SUCCESS response from server");
        },
        error:function (data){
            console.log("ERROR response from server");
        }
    });
}

function checkjQuery(){
    if(typeof jQuery != undefined){
        console.log("jQuery is loaded")
    }else {
        console.log("jQuery is NOT loaded")
    }
}

