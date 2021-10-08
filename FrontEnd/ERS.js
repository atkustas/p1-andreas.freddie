const url = "http://localhost:8090/"


document.getElementById("loginButton").addEventListener("click", loginFunc);


//login functionalltiy
async function loginFunc() {

    let usern = document.getElementById("username").value;
    let userp = document.getElementById("password").value;

    //we want to send the user/pass as JSON, so we need to make a JS object to send
    let user = {
        username: usern,
        password: userp,
        role_id: role = null
    }

    console.log(user);

    let response = await fetch(url + "login", {

        method: "POST",
        body: JSON.stringify(user),
        credentials: "include"
    });



    console.log(response.status);

    if (response.status === 200) {

    //     let response2 = await fetch(url + "logins", {

    //         method: "GET",
    //         credentials: "include"
    //     });
    
        let data = await response.json();

        document.getElementById("login-row").innerText = "Welcome!"



        for (let user of data) {

            console.log(user);

            if (user.role_id.roleID == 1) {
                let empPage = window.location.href = 'ERSemployee.html'
            }
            if (user.role_id.roleID == 2) {
                let empMang = window.location.href = 'ERSmanager.html'
            }
       }


    } else {
        document.getElementById("login-row").innerText = "Login failed! Check credentials and try again."
    }


}
