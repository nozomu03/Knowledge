let masterPostId;
(function() {
    loading();
    if (getCookie("Login") != null) {
        $(".right-container").empty();
        $(".right-container").append(
            `name: ${getCookie("NickName")}님 환영합니다.<br>
             <button onclick="Write('${getCookie("Login")}', 1)">글쓰기</button>
             <button onclick="logout()">로그아웃</button>`
        );
    }
}());

function logout() {
    deleteCookie("Login");
    deleteCookie("NickName");
    history.go(0);
}

function loading() {
    $.ajax({
        url: "/post",
        type: "GET",
        contentType: "application/json",
        success: function (response2) {

            for (let i = 0; i < response2.length; i++) {
                $("#list").append(
                    `<li onclick="PostMove(${response2[i].id})">${response2[i].title}</li>`
                );
                if (i == 0) {
                    $("#contentDiv").empty()
                    $("#contentDiv").append(
                        `<h3 id="title">${response2[i].title}</h3>
                                    <hr>
                                    <div id="writer">${response2[i].userName}(${response2[i].created})<button onclick="Delete(${response2[0].id})">삭제</button><button onclick="Write('${response2[i].userName}', 0)">수정</button></div>
                                    <div id="contentPost">${response2[i].content}</div>`
                    )
                    masterPostId = response2[0].id;
                }
                ;
            }
            ;
        }
    });
}

function login(){
    if(getCookie("Login") == null) {
        let data = new Object();
        data.account = $("#id").val();
        data.password = $("#pw").val();
        data = JSON.stringify(data);
        $.ajax({
            url: "/login",
            type: "POST",
            data: data,
            contentType: "application/json",
            success: function (response) {
                let temp = response.account
                $(".right-container").empty();
                $(".right-container").append(
                    `name: ${response.name}님 환영합니다.<br>
                     <button onclick="Write('${temp}', 1)">글쓰기</button>
                     <button onclick="logout()">로그아웃</button>\`
`
                );
                setCookie("Login", response.account, 1);
                setCookie("NickName", response.name, 1);
            }
        });
    }
}

function PostMove(id) {
    $.ajax({
        url : "/post/"+id,
        type : "GET",
        contentType: "application/json",
        success: function (response) {
            $("#contentDiv").empty()
            $("#contentDiv").append(
                `<h3 id="title">${response.title}</h3>
                <hr>
                <div id="writer">${response.userName}(${response.created})<button onclick="Delete(${response.id})">삭제</button><button onclick="Write('${response.userName}', 0)">수정</button></div>
                <div id="contentPost">${response.content}</div>`
            )
            masterPostId = id;
        }
    });
}

function Edit(userName) {
    if((!$("#title").val() == "") && (!$("#contents").val() == "")) {
        let data = new Object();
        data.userName = userName;
        data.title = $("#title").val();
        data.content = replaceAll($("#contents").val(), "\n", "<br>");
        data = JSON.stringify(data);
        $.ajax({
            url: "/post/" + masterPostId,
            type: "PUT",
            contentType: "application/json",
            data: data,
            success: function (response) {
                history.go(0);
            }
        });
    }
}

function Delete(id) {
    if ($(".right-container").text().includes($("#writer").text())) {
        $.ajax({
            url: "/post/" + id,
            type: "DELETE",
            contentType: "application/json",
            success: function (response) {
                if (response) {
                    alert("삭제 성공");
                    history.go(0);
                } else {
                    alert("삭제 실패");
                }
            }
        });
    } else {
        alert("권한이 없습니다.");
    }
}


function Write(userName, type) {
    if(type==1) {
        $("#contentDiv").empty()
        $("#contentDiv").append(
            `
            <input id="title" placeholder="제목 입력">
            <input multiple="multiple" type="file" id="upload-file">
            <button onclick="uploadFile()">이미지 업로드</button>
            <button onclick="Upload('${userName}')">확인</button>
            <hr>
            <textarea rows="5" cols="30" id="contents" placeholder="내용 입력 바람..."></textarea>

        `
        )
    }
    else{
        let title = $("#title").text();
        let writer = $("#writer").text();
        writer = writer.substring(0, writer.lastIndexOf("("));
        let accountFormat = $(".right-container").text();
        let content = $("#contentPost").text()
        if(accountFormat.includes(writer)) {
            $("#contentDiv").empty()
            $("#contentDiv").append(
                `
                <input id="title" value="${title}">
                <button onclick="Edit('${userName}')">확인</button>
                <hr>
                <textarea rows="5" cols="1000" id="contents">${content}</textarea>
    
            `
            )
        }
        else{
            alert("권한이 없습니다.");
        }
    }
}

function replaceAll(str, searchStr, replaceStr) {
    return str.split(searchStr).join(replaceStr);
}

function Upload(userName) {
    if((!$("#title").val() == "") && (!$("#contents").val() == "")){
        let data = new Object();
        data.userName = userName;
        data.title = $("#title").val();
        data.content = replaceAll($("#contents").val(), "\n", "<br>");
        data = JSON.stringify(data);
        $.ajax({
           url: "/post",
           contentType: "application/json",
           type: "POST",
            data: data,
            success: function (response) {
                try {
                    if($("#upload-file")[0].files){
                        let formData = new FormData();
                        for(let index = 0; index < $("#upload-file")[0].files.length; index++){
                            formData.append("srcFile", $("#upload-file")[0].files[index]);
                        }
                        $.ajax({
                            type: "POST",
                            url: ""
                        });
                    }
                }catch(err){
                    console.log(JSON.stringify(err));
                }
                history.go(0);
            }
        });
    }
}

async function uploadFile(){

}

function setCookie(name, value, expiredays) {
    let cookie = name + "=" + value + "; path=/;"
    if (typeof expiredays != 'undefined') {
        let todayDate = new Date();
        todayDate.setDate(todayDate.getDate() + expiredays);
        cookie += "expires=" + todayDate.toGMTString() + ";"
    }
    document.cookie = cookie;
}

function getCookie(name) {
    let cookies = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
    return cookies? cookies[2] : null;
}

function deleteCookie(name) {
    setCookie(name, "", -1);
}
