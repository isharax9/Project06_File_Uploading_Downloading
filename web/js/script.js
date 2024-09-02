/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

async function uploadFile() {
    const email = document.getElementById("email").value;
    const file = document.getElementById("file1").files[0];
    
//    console.log(email);
//    console.log(file);
    
    const data = new FormData();
    data.append("email", email);
    data.append("file", file);
    
    const response = await fetch(
            "Uploading",
            {
                method: "POST",
                body: data
            }
    );
    
    if (response.ok) {
        console.log("success");
    } else {
        console.log("error");
    }
    
}
