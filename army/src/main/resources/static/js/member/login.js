document.querySelector("#login-button").addEventListener("click",function (evt){
    const idInput = document.querySelector("#input_id").value;
    const passwordInput = document.querySelector("#input_password").value;

    if (idInput.trim().length === 0){
        swal(
            'Warning!',
            '입력된 내용이 없습니다 <br/> 아이디를 입력해주세요.',
            'warning'
        )
        return;
    }else if (passwordInput.trim().length === 0){
        swal(
            'Warning!',
            '입력된 내용이 없습니다 <br/> 비밀번호를 입력해주세요.',
            'warning'
        )
        return;
    }
    axios({
        method: "post",
        url: `/auth/member/login`,
        params : {
            id : idInput,
            password : passwordInput
        }
    }).then(response => {
        const status = response.status;
        if (status === 200){
            location.href = "/public/main";
        }
    }).catch(error => {
        const status = error.response.data.statusCode;
        if (status === 406){
            swal(
                'Warning!',
                '회원정보가 존재하지 않습니다. <br/> 다시 시도해주세요.',
                'warning'
            )
        }
    });
})



