async function blog(){
    let response = await fetch('/api/articles',{method:'get'}); //await로 데이터가 들어올때까지 대기

    try{
        //display
        let responseJson = await response.text();
        let responseObj = await JSON.parse(responseJson); //자바스크립트 객체로 바꾸어주는 역할
        let display = `<table class="table">
                               <thead>
                                 <tr>
                                   <th>id</th>
                                   <th>title</th>
                                   <th>content</th>
                                 </tr>
                               </thead>
                               <tbody>
                             `;

            responseObj.forEach((item)=> {
                display+= ` <tr>
                                   <td> ${item.id}</td>
                                   <td> ${item.title}</td>
                                   <td> ${item.content}</td>
                            </tr>
                          `
            });


            display += ` </tbody>
                          </table>
                       `;


            document.getElementById('contentDiv').innerHTML=display;
    }catch(err){
        alert("로그인 해주세요");
        window.open("html/login.html", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=100,left=100,width=400,height=500");
    }
}

async function login()
{
    const id = document.getElementById('id').value;
    const pw = document.getElementById('pw').value;

    let bodyData = JSON.stringify({id,pw});
    let response = await fetch('/login',{method:'post', body:bodyData, headers:{'Content-Type':'application/json'}});
    let responseJson = await response.text();

    console.log(responseJson);

    window.close();

}