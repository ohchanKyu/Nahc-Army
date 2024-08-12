document.querySelector("#findPassword-button").addEventListener("click",function (evt) {
    const nameInput = document.querySelector("#name").value;
    if (nameInput.trim().length === 0){
        swal(
            'Warning!',
            '입력된 내용이 없습니다 <br/> 이름을 입력해주세요.',
            'warning'
        )
        return;
    }
    const idInput = document.querySelector("#id").value;
    if (idInput.trim().length === 0){
        swal(
            'Warning!',
            '입력된 내용이 없습니다 <br/> 아이디를 입력해주세요.',
            'warning'
        )
        return;
    }
    axios({
        method: "get",
        url: `/auth/${nameInput}/${idInput}/getPassword`,
    }).then(response => {
        const status = response.status;
        if (status === 200){
            const targetPassword = response.data;
            swal({
                title: '비밀번호를 찾았습니다.',
                html: `회원님의 비밀번호는 <b>${targetPassword}</b>입니다!`,
                type: 'success',

                showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
                confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
                cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
                confirmButtonText: '로그인하기', // confirm 버튼 텍스트 지정
                cancelButtonText: '취소', // cancel 버튼 텍스트 지정
                reverseButtons: false, // 버튼 순서 거꾸로
            }).then(result => {
                if (result.value) {
                    location.href = "/public/login";
                }
            });
        }
    }).catch(error => {
        const status = error.response.data.statusCode;
        if (status === 406){
            swal(
                'Warning!',
                '해당 입력의 가입된 정보가 없습니다. <br/> 다시 시도해주세요!',
                'warning'
            )
        }
    });
});




