const firstDate = new Date(plannerComponentList[0].date);
const endDate = new Date(plannerComponentList[plannerComponentList.length-1].date);
const firstMonthValue = firstDate.toISOString().slice(0, 7);
const endMonthValue =  endDate.toISOString().slice(0, 7);

let monthsArray = [];
let editors = [];

const firstYear = firstDate.getFullYear();
const firstMonth = firstDate.getMonth();
const endYear = endDate.getFullYear();
const endMonth = endDate.getMonth();
const yearDiff = endYear - firstYear;
const monthDiff = endMonth - firstMonth;
const totalMonthDiff = yearDiff * 12 + monthDiff;

for(let i=0;i<=totalMonthDiff;i++){
    const firstMonthString = firstYear+'-'+ ( firstMonth + 1 ) +'-01';
    const firstMonthDateObject = new Date(firstMonthString);
    firstMonthDateObject.setMonth(firstMonthDateObject.getMonth() + i + 1);
    monthsArray.push({
       dateString : firstMonthDateObject.toISOString().slice(0, 7),
       dateComponent : []
    });
}
for(let i=0;i<plannerComponentList.length;i++) {
    const dateString = plannerComponentList[i].date.slice(0, 7);
    const targetMonthsArray = monthsArray.find(item => {
        return item.dateString === dateString;
    })
    targetMonthsArray.dateComponent.push(plannerComponentList[i]);
}
const plannerInitialMonth = document.querySelector(".current-month");
plannerInitialMonth.innerHTML = `
    ${monthsArray[0].dateString}
`;
// 여기까지가 초기 세팅 그 이후로 함수 적용

function nextDateFunction(){
    const monthValue = document.querySelector(".current-month").innerHTML;
    const monthValueString = monthValue.trim() + '-01';
    const dateObject = new Date(monthValueString);
    dateObject.setMonth(dateObject.getMonth() + 1);
    const targetString = dateObject.toISOString().slice(0, 7);
    document.querySelector('.current-month').innerHTML = targetString;
    const targetMonthArray = monthsArray.find(item => {
        return item.dateString === targetString;
    });
    dateFetchFunction(targetMonthArray.dateComponent);
}

function prevDateFunction(){

    const monthValue = document.querySelector(".current-month").innerHTML;
    const monthValueString = monthValue.trim() + '-01';
    const dateObject = new Date(monthValueString);
    dateObject.setMonth(dateObject.getMonth() - 1);
    const targetString = dateObject.toISOString().slice(0, 7);
    document.querySelector('.current-month').innerHTML = targetString;
    const targetMonthArray = monthsArray.find(item => {
        return item.dateString === targetString;
    })
    dateFetchFunction(targetMonthArray.dateComponent,targetMonthArray.dateString);
}

function arrowFetchFunction(){

    const monthValue = document.querySelector(".current-month").innerHTML;
    const arrowInitialMonth = document.querySelector(".arrow-month");
    if (monthValue.trim() === firstMonthValue && monthValue.trim() === endMonthValue){
        arrowInitialMonth.innerHTML = '';
    }
    else if (monthValue.trim() === firstMonthValue){
        arrowInitialMonth.innerHTML = `
            <i class="fa-solid fa-angles-right" onclick="nextDateFunction()"></i>
        `
    }else if (monthValue.trim() === endMonthValue) {
        arrowInitialMonth.innerHTML = `     
            <i class="fa-solid fa-angles-left" onclick="prevDateFunction()"></i>
        `
    }else{
        arrowInitialMonth.innerHTML = `     
            <i class="fa-solid fa-angles-left" onclick="prevDateFunction()"></i>
            <i class="fa-solid fa-angles-right" onclick="nextDateFunction()"></i>
        `
    }
}

arrowFetchFunction();
dateFetchFunction(monthsArray[0].dateComponent, monthsArray[0].dateString);

function dateFetchFunction(plannerComponentList, dateString){
    const dayOfWeek = new Date(plannerComponentList[0].date).getDay();
    const plannerContainer = document.querySelector(".planner-container");
    let allPlannerRows = document.querySelectorAll(".planner-row");
    allPlannerRows.forEach(item => {
        plannerContainer.removeChild(item);
    });

    let chunks = [];
    switch (dayOfWeek) {
        case 0:
            break;
        case 1:
            chunks.push(["empty",...plannerComponentList.slice(0,6)])
            plannerComponentList = plannerComponentList.slice(6,plannerComponentList.length);
            break;
        case 2:
            chunks.push(["empty","empty",...plannerComponentList.slice(0,5)])
            plannerComponentList = plannerComponentList.slice(5,plannerComponentList.length);
            break;
        case 3:
            chunks.push(["empty","empty","empty",...plannerComponentList.slice(0,4)])
            plannerComponentList = plannerComponentList.slice(4,plannerComponentList.length);
            break;
        case 4:
            chunks.push(["empty","empty","empty","empty",...plannerComponentList.slice(0,3)])
            plannerComponentList = plannerComponentList.slice(3,plannerComponentList.length);
            break;
        case 5:
            chunks.push(["empty","empty","empty","empty","empty",...plannerComponentList.slice(0,2)])
            plannerComponentList = plannerComponentList.slice(2,plannerComponentList.length);
            break;
        case 6:
            chunks.push(["empty","empty","empty","empty","empty","empty",...plannerComponentList.slice(0,1)])
            plannerComponentList = plannerComponentList.slice(1,plannerComponentList.length);
            break;
        default:
            break;
    }
    for (let i = 0; i < plannerComponentList.length; i += 7) {
        chunks.push(plannerComponentList.slice(i, i + 7));
    }
    let lastArray = chunks.pop();
    switch (lastArray.length) {
        case 7:
            chunks.push([...lastArray])
            break;
        case 6:
            chunks.push([...lastArray,"empty"])
            break;
        case 5:
            chunks.push([...lastArray,"empty","empty"])
            break;
        case 4:
            chunks.push([...lastArray,"empty","empty","empty"])
            break;
        case 3:
            chunks.push([...lastArray,"empty","empty","empty","empty"])
            break;
        case 2:
            chunks.push([...lastArray,"empty","empty","empty","empty","empty"])
            break;
        case 1:
            chunks.push([...lastArray,"empty","empty","empty","empty","empty","empty"])
            break;
        default:
            break;
    }

    chunks.forEach(function(chunk) {
        const row = document.createElement('div');
        row.classList.add('planner-row');
        row.classList.add(`component${dateString}`);

        chunk.forEach(function(result) {
            if (result === "empty"){
                const emptyBox = document.createElement('div');
                emptyBox.classList.add('empty-element');
                row.appendChild(emptyBox);
            }else{
                const divBox = document.createElement('div');
                const typeData = result.type;
                if (typeData && typeData === "army"){
                    divBox.classList.add("planner-element");
                    divBox.classList.add("army");
                }else{
                    divBox.classList.add("planner-element");
                }
                divBox.setAttribute("data-bs-toggle","modal");
                divBox.setAttribute("data-bs-target",`#modal${result.date}`);
                divBox.addEventListener("click",function (evt){
                    const id = result.id;
                    const editorContainer = document.querySelector(`.editor${id}`);
                    const isCreate = editorContainer.dataset.value;
                    if (isCreate !== "1"){
                        ClassicEditor
                            .create(editorContainer, {
                                removePlugins: ['Image', 'ImageCaption', 'ImageStyle', 'ImageToolbar', 'ImageUpload', 'MediaEmbed'],
                            })
                            .then(editor => {
                                editors.push({
                                    id: id,
                                    editor: editor
                                });
                            })
                            .catch(error => {
                                console.error('에디터 생성 중 오류 발생:', error);
                            });
                        editorContainer.dataset.value = "1";
                    }
                });

                const dateParagraph = document.createElement('p');
                dateParagraph.classList.add('planner-element-date')
                dateParagraph.innerText = result.date;
                const titleParagraph = document.createElement('p');
                titleParagraph.classList.add('planner-element-title')
                titleParagraph.innerText = result.title;
                divBox.appendChild(dateParagraph);
                divBox.appendChild(titleParagraph);

                const modalBox = document.createElement('div');
                modalBox.innerHTML = `
           <div class="modal fade" 
                id="modal${result.date}" 
                data-bs-backdrop="static" 
                data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <input type="text" 
                                class="form-control modal-input title${result.id}"
                                value="${result.title ? result.title : ''}"
                                placeholder="제목을 입력해주세요."/>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p class="form-check-description"><i class="fa-solid fa-check"></i> 훈련날이라면 체크해주세요.</p>
                            <div class="form-check form-switch">
                              <input class="form-check-input type${result.id}" 
                                type="checkbox" 
                                role="switch" 
                                id="flexSwitchCheckDefault${result.id}"
                                ${result.type === "army" ? 'checked' : ''}>
                              <label class="form-check-label" for="flexSwitchCheckDefault${result.id}">훈련</label>
                            </div>
                            <p class="form-check-description2"><i class="fa-solid fa-check"></i> 현재 선택하신 날짜입니다. </p>
                            <p class="modal-date">${result.date}</p>
                             <textarea 
                                data-value="0"
                                class="editor${result.id}" 
                                placeholder="내용을 입력해주세요.">${result.content ? result.content : ''}</textarea>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="close-button" data-bs-dismiss="modal">닫기</button>
                            <button data-value="${result.id}" type="button" id="save-button">저장</button>
                        </div>
                    </div>
                </div>
            </div>
        `
                row.appendChild(modalBox);
                row.appendChild(divBox);
            }
        });
        plannerContainer.appendChild(row);
    });
    arrowFetchFunction();
    saveButtonInitialize();
}

function saveButtonInitialize(){

    const saveButtons = document.querySelectorAll("#save-button");
    saveButtons.forEach(button => {
        button.addEventListener("click",function (evt){

            const plannerComponentId = evt.target.dataset.value;

            const titleInput = document.querySelector(`.title${plannerComponentId}`).value;
            const typeInput = document.querySelector(`.type${plannerComponentId}`).checked;
            let type;
            if (typeInput){
                type = "army";
            }
            const targetObject = editors.find(data => {
                return data.id === parseInt(plannerComponentId)
            });
            const contentInput = targetObject.editor.getData();
            if (contentInput.length > 19999){
                swal(
                    'Warning!',
                    '글자는 최대 20000자까지만 입력 가능합니다!',
                    'warning'
                )
                return;
            }
            axios({
                method: "patch",
                url: `/api/planner/patch/component/${plannerComponentId}`,
                data : {
                    title : titleInput,
                    content : contentInput,
                    type : type,
                }
            }).then(response => {
                const status = response.status;
                if (status === 200){
                    swal(
                        'Success!',
                        '저장이 완료되었습니다.',
                        'success'
                    ).then(result => {
                        if (result.value){
                            location.href = `/private/planner/${plannerId}`;
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
}

function resizeTextarea(element) {
    element.style.height = 'auto';
    element.style.height = (element.scrollHeight) + 'px';
}

function markdownToHTML(markdown) {

    let html = markdown
        .replace(/(^|\n)###\s*(.+)/g, '$1<h3>$2</h3>')
        .replace(/(^|\n)##\s*(.+)/g, '$1<h2>$2</h2>')
        .replace(/(^|\n)#\s*(.+)/g, '$1<h1>$2</h1>')
        .replace(/\*\*(.+?)\*\*/g, '<strong>$1</strong>')
        .replace(/\*(.+?)\*/g, '<em>$1</em>')
        .replace(/(^|\n)-\s*(.+)/g, '$1<li>$2</li>')
        .replace(/\n/g, '<br>');

    html = html.replace(/(<br>\s*<li>.+<\/li>)/g, '<ul>$1</ul>');
    html = html.replace(/<\/li><br>\s*<li>/g, '</li><li>');
    return html;
}

function insertAIResponse(question,response){
    const container = document.querySelector(".ai-response-container");

    const wrapperBox = document.createElement('div');
    wrapperBox.classList.add('ai-wrapper');

    const questionContainer = document.createElement("div");
    questionContainer.classList.add('question-container');
    questionContainer.innerHTML = `
        <p class="question"><i style="margin-right: 10px" class="fa-solid fa-circle-question"></i> Question</p>
        <p class="question-text">${question}</p>
    `

    const answerContainer = document.createElement('div');
    answerContainer.classList.add('answer-container');
    const markDownResponse = markdownToHTML(response);
    answerContainer.innerHTML = `
        <p class="answer"><i style="margin-right: 10px;font-size:20px" class="fa-brands fa-square-google-plus"></i> Gemini Answer</p>
        <p class="answer-text">${markDownResponse}</p>
    `
    wrapperBox.appendChild(questionContainer);
    wrapperBox.appendChild(answerContainer);
    container.appendChild(wrapperBox);

    document.querySelector('.loading-container').style.display = "none";
}

function aiRequest(event) {
    if (event.key === 'Enter') {
        const userQuestion = document.getElementById('gemini-input').value;
        if (userQuestion.trim().length === 0){
            event.preventDefault();
            swal(
                'Warning!!',
                '질문 내용을 입력해주세요!',
                'warning'
            );
            return;
        }
        document.querySelector('.loading-container').style.display = "block";
        document.getElementById('gemini-input').value = '';
        document.getElementById('gemini-input').blur();
        resizeTextarea(document.getElementById('gemini-input'));
        axios({
            method: "post",
            url: `/api/gemini/question`,
            params : {
                question : userQuestion
            }
        }).then(response => {
            const status = response.status;
            if (status === 200){
               insertAIResponse(userQuestion,response.data.toString());
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
    }
}