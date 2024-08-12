const logoutButton = document.querySelector("#logout-button")

if (logoutButton != null){
    logoutButton.addEventListener("click",function(evt){

        axios({
            method: "post",
            url: `/auth/member/logout`,
        }).then(response => {
            const status = response.status;
            if (status === 200){
                if (response.data){
                    swal(
                        'Success!',
                        '로그아웃 하였습니다. <br/> 메인 페이지로 이동합니다.',
                        'success'
                    ).then(result => {
                        if (result.value){
                            location.href = "/public/main";
                        }
                    })
                }
            }

        }).catch(error => {
            const status = error.response.data.statusCode;
            if (status === 401){
                swal(
                    'Error!',
                    '일시적 오류입니다. <br/> 다시 시도해주세요.',
                    'error'
                )
            }
        });
    });
}

axios.defaults.paramsSerializer = function (paramObj) {
    const params = new URLSearchParams();
    for (const key in paramObj) {
        params.append(key, paramObj[key]);
    }
    return params.toString();
};
