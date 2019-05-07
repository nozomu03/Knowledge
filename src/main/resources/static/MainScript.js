$(function () {
    init()
});

let masterPostId;

let init = async () => {
    $.ajax({
        url: "/user/nozomu03",
        type : "GET",
        contentType : "application/json",
        success: function (response) {
            let temp  = response.account
            $(".right-container").append(
                `acount: ${temp}<br>
                 name: ${response.name}<br>
                 join: ${response.created}<br>
                 <button onclick="Write('${temp}', 1)">글쓰기</button>`
            );
            $.ajax({
               url: "/post",
               type: "GET",
               contentType: "application/json",
               success: function (response2) {

                   for(let i = 0; i<response2.length; i++){
                       $("#list").append(
                            `<li onclick="PostMove(${response2[i].id})">${response2[i].title}</li>`
                       );
                       if(i==0){
                           $("#contentDiv").empty()
                           $("#contentDiv").append(
                               `<h3 id="title">${response2[i].title}</h3>
                                <hr>
                                <div id="writer">${response2[i].userName}(${response2[i].created})<button onclick="Delete(${response2[0].id})">삭제</button><button onclick="Write('${response2[i].userName}', 0)">수정</button></div>
                                <div id="contentPost">${response2[i].content}</div>`
                           )
                           masterPostId = response2[0].id;
                       };
                   };
               }
            });
        }
    });
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
    $.ajax({
        url : "/post/"+id,
        type : "DELETE",
        contentType: "application/json",
        success: function (response) {
            if(response){
                alert("삭제 성공");
                history.go(0);
            }
            else{
                alert("삭제 실패");
            }
        }
    });
}

function Write(userName, type) {
    if(type==1) {
        $("#contentDiv").empty()
        $("#contentDiv").append(
            `
            <input id="title" placeholder="제목 입력">
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
                history.go(0);
            }
        });
    }

}