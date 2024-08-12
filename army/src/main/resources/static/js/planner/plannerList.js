document.querySelector("#add-planner").addEventListener("click",function(evt){
   const titleInput = document.querySelector("#title").value;
   const startDate = document.querySelector("#startDate").value;
   const endDate = document.querySelector("#endDate").value;

   const dateDifference = new Date(endDate) - new Date(startDate);

   if (titleInput.trim().length === 0){
       swal(
           'Warning!',
           '입력된 내용이 없습니다 <br/> 제목을 입력해주세요.',
           'warning'
       )
       return;
    }
    if (!startDate){
        swal(
            'Warning!',
            '시작 날짜를 지정해주세요.',
            'warning'
        )
        return;
    }
    if (!endDate){
        swal(
            'Warning!',
            '마지막 날짜를 지정해주세요.',
            'warning'
        )
        return;
    }
    if (dateDifference < 0){
        swal(
            'Warning!',
            '날짜 지정이 잘못되었습니다. <br> 다시 설정해주세요.',
            'warning'
        )
        return;
    }
    axios({
        method: "post",
        url: `/api/planner/add`,
        data : {
            title : titleInput,
            startDate : startDate,
            endDate: endDate,
        }
    }).then(response => {
        const status = response.status;
        if (status === 200){
            const responseData = response.data;
            if (responseData){
                swal(
                    'Success!',
                    '플래너가 새로 등록되었습니다.',
                    'success'
                ).then(result => {
                    if (result.value){
                        location.href = "/private/planner";
                    }
                })
            }else{
                swal(
                    'Error!',
                    '일시적 오류입니다. <br/> 다시 시도해주세요!',
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
    });
});
const editTitleButtonTags = document.querySelectorAll("#save-title-button");
editTitleButtonTags.forEach(button => {
    button.addEventListener("click",function (evt){
        const plannerId = evt.target.getAttribute("data");
        const titleInput = document.querySelector(`#title${plannerId}`).value;
        if (titleInput.trim().length === 0){
            swal(
                'Warning!',
                '입력된 내용이 없습니다 <br/> 제목을 입력해주세요.',
                'warning'
            )
            return;
        }
        axios({
            method: "patch",
            url: `/api/planner/patch/title/${plannerId}`,
            params : {
                title : titleInput,
            }
        }).then(response => {
            const status = response.status;
            if (status === 200){
                swal(
                    'Success!',
                    '제목 수정이 완료되었습니다.',
                    'success'
                ).then(result => {
                    if (result.value){
                        location.href = `/private/planner`;
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
});

const editDateButtonTags = document.querySelectorAll("#save-date-button");
editDateButtonTags.forEach(button => {
    button.addEventListener("click",function (evt){
        const plannerId = evt.target.getAttribute("data");
        const startDate = document.querySelector(`#startDate${plannerId}`).value;
        const endDate = document.querySelector(`#endDate${plannerId}`).value;

        const dateDifference = new Date(endDate) - new Date(startDate);
        if (!startDate){
            swal(
                'Warning!',
                '시작 날짜를 지정해주세요.',
                'warning'
            )
            return;
        }
        if (!endDate){
            swal(
                'Warning!',
                '마지막 날짜를 지정해주세요.',
                'warning'
            )
            return;
        }
        if (dateDifference < 0){
            swal(
                'Warning!',
                '날짜 지정이 잘못되었습니다. <br> 다시 설정해주세요.',
                'warning'
            )
            return;
        }
        axios({
            method: "patch",
            url: `/api/planner/patch/date/${plannerId}`,
            data : {
                startDate : startDate,
                endDate : endDate
            }
        }).then(response => {
            const status = response.status;
            if (status === 200){
                swal(
                    'Success!',
                    '날짜 수정이 완료되었습니다.',
                    'success'
                ).then(result => {
                    if (result.value){
                        location.href = `/private/planner`;
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
});

const deleteButtonTags = document.querySelectorAll("#delete-planner");
deleteButtonTags.forEach(button => {
    button.addEventListener("click",function (evt){
        const plannerId = evt.target.getAttribute("data");
        swal({
            title: '정말로 삭제하시겠습니까?',
            html: '삭제를 원하시면 삭제 버튼을 눌러주세요.',
            type: 'question',

            showCancelButton: true, // cancel버튼 보이기. 기본은 원래 없음
            confirmButtonColor: '#3085d6', // confrim 버튼 색깔 지정
            cancelButtonColor: '#d33', // cancel 버튼 색깔 지정
            confirmButtonText: '삭제', // confirm 버튼 텍스트 지정
            cancelButtonText: '취소', // cancel 버튼 텍스트 지정
            reverseButtons: false, // 버튼 순서 거꾸로
        }).then(result => {
            if (result.value) {
                axios({
                    method: "delete",
                    url: `/api/planner/${plannerId}`,
                }).then(response => {
                    const status = response.status;
                    if (status === 200){
                        const responseData = response.data;
                        if (responseData){
                            swal(
                                'Success!',
                                '삭제 완료하였습니다!',
                                'success'
                            ).then(result => {
                                if (result.value){
                                    location.href = "/private/planner";
                                }
                            })
                        }else{
                            swal(
                                'Error!',
                                '삭제를 실패하였습니다. <br/> 다시 시도해주세요.',
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
                });
            }
        });
    });
});






