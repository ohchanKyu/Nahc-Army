function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = '';
            var extraAddr = '';

            if (data.userSelectedType === 'R') {
                addr = data.roadAddress;
            } else {
                addr = data.jibunAddress;
            }

            if(data.userSelectedType === 'R'){
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                document.getElementById("extraAddress").value = extraAddr;

            } else {
                document.getElementById("extraAddress").value = '';
            }
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
        }
    }).open();
}
document.querySelector("#signup-button").addEventListener("click",function(evt){

    evt.target.disabled = true;
    evt.target.innerText = "제출중...";

    const nameInput = document.querySelector("#name").value;
    const idInput = document.querySelector("#id").value;
    const idDuplicatedCheck = document.querySelector("#id-check").dataset.value;
    const passwordInput = document.querySelector("#password").value;
    const passwordCheckInput = document.querySelector("#passwordCheck").value;
    const fullAddress = document.querySelector("#address").value + document.querySelector("#extraAddress").value;

    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;

    if (fullAddress.trim().length === 0){
        swal(
            'Warning!',
            '부대 주소를 입력해주세요!',
            'warning'
        )
        return;
    }
    if (nameInput.trim().length === 0){
        swal(
            'Warning!',
            '입력된 내용이 없습니다 <br/> 이름을 입력해주세요.',
            'warning'
        )
        return;
    }else if (idInput.trim().length <= 6){
        swal(
            'Warning!',
            '아이디는 7자 이상으로 입력해주세요!',
            'warning'
        )
        return;
    }else if(idDuplicatedCheck !== "1"){
        swal(
            'Warning!',
            '아이디 중복체크를 먼저 해주세요!',
            'warning'
        )
        return;
    }else if (!passwordRegex.test(passwordInput)){
        swal(
            'Warning!',
            '영문자,숫자,특수문자를 하나 포함, <br/> (8~15)자리로 만들어주세요.',
            'warning'
        )
        return;
    }else if (passwordInput !== passwordCheckInput){
        swal(
            'Warning!',
            '비밀번호가 일치하지 않습니다. <br/> 다시 한 번 확인해주세요.',
            'warning'
        )
        return;
    }
    axios({
        method: "post",
        url: `/auth/member/signup`,
        data : {
            name : nameInput,
            userId : idInput,
            password: passwordInput,
            address : fullAddress,
        }
    }).then(response => {
        const status = response.status;
        if (status === 200){
            const responseData = response.data;
            if (responseData){
                evt.target.disabled = false;
                evt.target.innerText = "회원가입";
                swal(
                    'Success!',
                    '회원가입에 성공하였습니다. <br/> 로그인 페이지로 이동합니다.',
                    'success'
                ).then(result => {
                    if (result.value){
                        location.href = "/public/login";
                    }
                })
            }else{
                evt.target.disabled = false;
                evt.target.innerText = "회원가입";
                swal(
                    'Error!',
                    '이미 회원가입 완료되었습니다!',
                    'error'
                )
            }
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
        evt.target.disabled = false;
        evt.target.innerText = "회원가입";
    });
});

document.querySelector("#id-check").addEventListener("click",function (evt){
    const idInput = document.querySelector("#id").value;
    if (idInput.trim().length === 0){
        swal(
            'Warning!',
            '입력된 내용이 없습니다 <br/> 아이디를 입력해주세요.',
            'warning'
        )
        return;
    }else if (idInput.trim().length <= 6){
        swal(
            'Warning!',
            '아이디는 7자 이상으로 입력해주세요!',
            'warning'
        )
        return;
    }
    axios({
        method: "get",
        url: `/auth/${idInput}/duplicated`,
    }).then(response => {
        const status = response.status;
        if (status === 200){
            const responseData = response.data;
            if (responseData){
                document.querySelector("#id-check").dataset.value = "1";
                swal(
                    'Success!',
                    '사용 가능한 아이디 입니다!',
                    'success'
                )
            }else{
                swal(
                    'Warning!',
                    '아이디가 중복됩니다. <br/> 다른 아이디를 사용해주세요.',
                    'warning'
                )
            }

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



