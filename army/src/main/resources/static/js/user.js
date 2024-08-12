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
document.querySelector('#name-save-button').addEventListener('click',function (evt){
    const inputValue = document.querySelector('#name').value;
    if (inputValue.trim().length === 0){
        swal(
            'Warning!',
            '새로운 이름을 입력하신 후 <br> 저장 버튼을 눌러주세요!',
            'warning'
        )
        return;
    }
    axios({
        method: "patch",
        url: `/api/member/${user.id}/name`,
        params : {
           name : inputValue
        }
    }).then(response => {
        const status = response.status;
        if (status === 200){
            swal(
                'Success!',
                '이름 수정이 완료되었습니다.',
                'success'
            ).then(result => {
                if (result.value){
                    location.href = `/private/me`;
                }
            });
        }
    }).catch(error => {
        const status = error.response.data.statusCode;
        if (status === 406){
            swal(
                'Error!!',
                '일시적 오류입니다. <br> 다시 시도해주세요.',
                'error'
            )
        }
    });
})
document.querySelector("#address-save-button").addEventListener('click',function (evt){
    const fullAddress = document.querySelector("#address").value + document.querySelector("#extraAddress").value;
    if (fullAddress.trim().length === 0){
        swal(
            'Warning!',
            '부대 주소를 입력 후 <br> 저장 버튼을 눌러주세요!',
            'warning'
        )
        return;
    }
    axios({
        method: "patch",
        url: `/api/member/${user.id}/address`,
        params : {
            address : fullAddress
        }
    }).then(response => {
        const status = response.status;
        if (status === 200){
            swal(
                'Success!',
                '부대 주소 수정이 완료되었습니다.',
                'success'
            ).then(result => {
                if (result.value){
                    location.href = `/private/me`;
                }
            });
        }
    }).catch(error => {
        const status = error.response.data.statusCode;
        if (status === 406){
            swal(
                'Error!!',
                '일시적 오류입니다. <br> 다시 시도해주세요.',
                'error'
            )
        }
    });
});
document.querySelector('#id-save-button').addEventListener('click',function (evt){
    const idInput = document.querySelector("#id").value;
    const idDuplicatedCheck = document.querySelector("#id-check").dataset.value;
    if (idInput.trim().length <= 6) {
        swal(
            'Warning!',
            '아이디는 7자 이상으로 입력해주세요!',
            'warning'
        )
        return;
    }if(idDuplicatedCheck !== "1"){
        swal(
            'Warning!',
            '아이디 중복체크를 먼저 해주세요!',
            'warning'
        )
        return;
    }
    axios({
        method: "patch",
        url: `/api/member/${user.id}/id`,
        params : {
            userId : idInput
        }
    }).then(response => {
        const status = response.status;
        if (status === 200){
            swal(
                'Success!',
                '아이디 수정이 완료되었습니다.',
                'success'
            ).then(result => {
                if (result.value){
                    location.href = `/private/me`;
                }
            });
        }
    }).catch(error => {
        const status = error.response.data.statusCode;
        if (status === 406){
            swal(
                'Error!!',
                '일시적 오류입니다. <br> 다시 시도해주세요.',
                'error'
            )
        }
    });
});
document.querySelector('#password-save-button').addEventListener('click',function (evt){
    const passwordInput = document.querySelector("#password").value;
    const passwordCheckInput = document.querySelector("#passwordCheck").value;
    const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$/;
    if (!passwordRegex.test(passwordInput)){
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
        method: "patch",
        url: `/api/member/${user.id}/password`,
        params : {
            password : passwordInput
        }
    }).then(response => {
        const status = response.status;
        if (status === 200){
            swal(
                'Success!',
                '비밀번호 수정이 완료되었습니다.',
                'success'
            ).then(result => {
                if (result.value){
                    location.href = `/private/me`;
                }
            });
        }
    }).catch(error => {
        const status = error.response.data.statusCode;
        if (status === 406){
            swal(
                'Error!!',
                '일시적 오류입니다. <br> 다시 시도해주세요.',
                'error'
            )
        }
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
document.querySelector("#delete-user-button").addEventListener('click',function (evt){
    const userId = evt.target.getAttribute("data");
    axios({
        method: "delete",
        url: `/api/member/${userId}`,
    }).then(response => {
        const status = response.status;
        if (status === 200){
            const responseData = response.data;
            if (responseData){
                swal(
                    'Success!',
                    '회원 탈퇴를 하셨습니다. <br> 메인 페이지로 이동합니다.',
                    'success'
                ).then(result => {
                    if (result.value){
                        location.href = `/public/main`;
                    }
                });
            }else{
                swal(
                    'Warning!',
                    '탈퇴에 실패하셨습니다. <br/> 다시 시도해주세요.',
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
