const insertIdList = (idList) => {
    const messageTag = document.querySelector(".no-id-message");
    const listTag = document.querySelector(".id-list");

    if (idList.length === 0){
        listTag.style.display = "none";
        messageTag.style.display = "block";
        messageTag.classList.add("animation-tag");
        document.querySelector(".no-id-message").innerHTML = "<i class=\"fa-solid fa-circle-xmark\"></i> 해당 이름의 아이디가 존재하지 않습니다.";
    }else{
        listTag.style.display = "block";
        messageTag.style.display = "none";
        listTag.classList.add("animation-tag");
        const ulTag = document.querySelector(".id-list");
        let body = "";
        body += "<p class='id-message'><i class=\"fa-solid fa-circle-check\"></i> 회원님의 아이디입니다. </p>";
        for(let i=0;i<idList.length;i++){
            body += `
                <li class="target-id">
                   - ${idList[i]}
                </li>
            `
        }
        ulTag.innerHTML = body;
    }
};

document.querySelector("#findId-button").addEventListener("click",function (evt) {
    const nameInput = document.querySelector("#name").value;
    if (nameInput.trim().length === 0){
        swal(
            'Warning!',
            '입력된 내용이 없습니다 <br/> 이름을 입력해주세요.',
            'warning'
        )
        return;
    }
    axios({
        method: "get",
        url: `/auth/${nameInput}/getId`,
    }).then(response => {
        const status = response.status;
        if (status === 200){
            insertIdList(response.data);
        }
    }).catch(error => {
        const status = error.response.data.statusCode;
        if (status === 406){
            swal(
                'Error!',
                '일시적 오류입니다. <br/> 다시 시도해주세요!',
                'error'
            )
        }
    });
});



