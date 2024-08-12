const elements = document.querySelectorAll('.in_view');
const otherElements = document.querySelectorAll('.in_sectionView');

document.querySelector(".go-gemini").addEventListener("click",function (evt){
    location.href="/private/planner"
})
document.querySelector(".go-certificate").addEventListener("click",function (evt){
   location.href="/private/certificate"
})
document.querySelector(".go-planner").addEventListener("click",function (evt){
    location.href="/private/planner"
})

function isInViewport(el) {
    const rect = el.getBoundingClientRect();
    const top = Math.max(rect.top, 0);
    const left = Math.max(rect.left, 0);

    return (
        top <= (window.innerHeight || document.documentElement.clientHeight) &&
        left <= (window.innerWidth || document.documentElement.clientWidth)
    );
}
function checkInView() {
    elements.forEach(function(element) {
        if (isInViewport(element)) {
            element.classList.add('visible');
        } else {
            element.classList.remove('visible');
        }
    });
    otherElements.forEach(function(element) {
        if (isInViewport(element)) {
            element.classList.add('secondVisible');
        } else {
            element.classList.remove('secondVisible');
        }
    });
}

window.addEventListener('scroll', checkInView);
window.addEventListener('resize', checkInView);

checkInView();