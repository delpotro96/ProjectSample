$(document).ready(function () {

    window.SpeechRecognition=window.SpeechRecognition || window.webkitSpeechRecognition;
    const recognition=new SpeechRecognition();
    recognition.interimResults=true;
    //console.log(recognition);
    recognition.addEventListener("result",function(e){
        //console.log(e.results[0][0].transcript);
        $("#ta1").val(e.results[0][0].transcript);
    });


    const id = $.cookie("id");
    if(id){
        $("#loginSpan").html(id+" <button id='logoutBtn'>logout</button>");
    }

    $("#memberInsertBtn").click(function () {

        const name = $("#name").val();
        const id = $("#id").val();
        const pw = $("#pw").val();

        $.post("../memberInsert", {name, id, pw}, function (data) {
            alert(data);
            window.close();
        })
    })
    //로그인 처리
    $("#loginBtn").click(function(){
        const id=$("#id").val();
        const pw=$("#pw").val();

        //alert(id+":"+pw);
        $.post("login",{id,pw},function(data){
            if(data!="Fail"){
                const id=data;
                $.cookie("id",id);
                $("#loginSpan").html(id+" <button id='logoutBtn'>logout</button>");
            }
        });
    });
    $("#logoutBtn").click(function(){
        $.post("logout", {}, function(){
            $.removeCookie("id")
            location.reload();
        })
    })

    $(document).on('click', '#micImg', function(){
        $("#micImg").attr("src", "img/mic_on_small.png")
        recognition.start();
        setTimeout(function (){
            $("#micImg").attr("src", "img/mic_off_small.png")
            recognition.stop();
        }, 5000);
    })

    $(document).on('click', '#insertBtn1', function(){
        const data=$("#ta1").val();
        if(data) {
            $.post('reviewInsert', {data}, function(returnData){
                returnData = JSON.parse(returnData);
                if(returnData.fileName){
                    const audio=new Audio("media/"+returnData.fileName+".mp3");
                    audio.play();

                } else{
                    alert(returnData.msg);
                }
            })
        } else{
            alert("마이크를 누르시고 말씀해주세용")
        }
    })


    //주 기능 insert 처리
    $("#insertDiv1").click(function(){
        $("#insertDiv1_1").html("<textarea id='ta1' class='card-header py-3' style='width: 778px' rows='5'></textarea>; <br><br> <img id ='micImg' src='img/mic_off_small.png' style='margin-left: 360px' ><br><br><button id='insertBtn1' class='btn-primary' style='margin-left: 360px'>등록</button>")
    })

})