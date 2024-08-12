const armyCodeButtons = document.querySelectorAll(".army-code-button");

function insertListContainer(list,type,category){
    const listContainer = document.querySelector(".recommend-list");
    const header = document.createElement("h4");
    header.innerHTML = " <i class=\"fa-brands fa-cc-jcb\"></i> 추천 자격증 ";
    const headerDescription = document.createElement("p")
    headerDescription.classList.add("header-description")
    headerDescription.innerHTML = `
        총 ${list.length}건의 검색결과가 있습니다. <br>
        ${type} ${category}에 대해 검색한 결과입니다.
    `;
    listContainer.appendChild(header);
    listContainer.appendChild(headerDescription);

    const itemWrapper = document.createElement("div");
    itemWrapper.classList.add("item-wrapper");
    for(let i=0;i<list.length;i++){
        const itemBox = document.createElement("div");
        itemBox.classList.add("recommend-item");
        itemBox.innerHTML = `
            <div class="item">
                <p><i class="fa-solid fa-note-sticky"></i> ${list[i].certificateName}</p>
                <p>합격률 <i class="fa-solid fa-angle-right"></i> <span style="color:#e45735">${list[i].passingRate}%</span></p>
                <p>총 응시 수 <i class="fa-solid fa-angle-right"></i> <span style="color:#e45735">${list[i].totalExamination} 건</span></p>
                <a target="_blank" href="${list[i].certificateUrl}"><i class="fa-solid fa-location-arrow"></i> 자세히보기</a>
            </div>
        `
        itemWrapper.appendChild(itemBox);
    }
    listContainer.appendChild(itemWrapper);
}
function recommendApiRequest(type){
    const listComponent = document.querySelector(".recommend-list");
    while (listComponent.firstChild) {
        listComponent.removeChild(listComponent.firstChild);
    }
    const category = document.querySelector(".form-select").value;
    if (category === '육군' || category === '해군' || category === '공군' || category === '해병대'){
        swal(
            'Warning!',
            '희망직종을 선택해주세요.',
            'warning'
        )
        return;
    }
    axios({
        method: "get",
        url: `/api/certificate/recommend/${type}/${category}`,
    }).then(response => {
        const status = response.status;
        if (status === 200){
            insertListContainer(response.data,type,category);
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
function insertSelectContainer(category,type){
  let categoryContainer = document.querySelector(".display-container");
  const childElement = categoryContainer.children;
  for(let i=0;i<childElement.length;i++){
      if (childElement[i].id === 'army-code-select'){
          categoryContainer.removeChild(childElement[i]);
      }
      if (childElement[i].id === 'recommend-button-container'){
          categoryContainer.removeChild(childElement[i]);
      }
  }
  let selectContainer = document.createElement("select");
  let buttonContainer = document.createElement("div");

  selectContainer.classList.add('form-select');
  selectContainer.setAttribute("id","army-code-select");
  buttonContainer.setAttribute("id","recommend-button-container");

  let selectInnerHTML = '';
  selectInnerHTML += `<option value="${type}">${type} - 희망직종을 선택해주세요.</option>`
  for (let i = 0; i < category.length; i++) {
      selectInnerHTML += `<option value="${category[i]}">${category[i]}</option>`;
  }
  buttonContainer.innerHTML = `
    <button
        onclick="recommendApiRequest('${type}')"
        class="recommend-button">검색
    </button>
` ;
  selectContainer.innerHTML = selectInnerHTML;
  categoryContainer.appendChild(selectContainer);
  categoryContainer.appendChild(buttonContainer);
}

armyCodeButtons.forEach(button => {
    button.addEventListener("click",function (evt){
        const type = evt.target.innerHTML;
        axios({
            method: "get",
            url: `/api/certificate/category/${type}`,
        }).then(response => {
            const status = response.status;
            if (status === 200){
                insertSelectContainer(response.data,type);
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
    })
});
