const inEvenNumbers = document.getElementById("inEvenNumbers");
const lbEvenNumOutput = document.getElementById("lbEvenNumOutput");
const formEvenNumbers = document.getElementById("formEvenNumbers");

function evenSubmitClick(event){
    if(inEvenNumbers.value == ""){
        inEvenNumbers.insertAdjacentElement("beforebegin", emptyError);
        return;
    }
    lbEvenNumOutput.textContent = getNaturalEvenNumbers(inEvenNumbers.value);
    formEvenNumbers.removeChild(emptyError);
}

formEvenNumbers.addEventListener('submit', evenSubmitClick)