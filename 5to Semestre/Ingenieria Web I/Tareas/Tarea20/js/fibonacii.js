const inputFibonacii = document.getElementById("inputFibonacii");
const lbFibonaciiOutput = document.getElementById("lbFibonaciiOutput");
const formFibonacii = document.getElementById("formFibonacii");

function fibonaciiSubmitClick(event){
    if(inputFibonacii.value === ""){
        inputFibonacii.insertAdjacentElement('beforebegin', emptyError);
        return
    }
    lbFibonaciiOutput.textContent = fibonacii(inputFibonacii.value)
    formFibonacii.removeChild(errorSpan[0]);
}

formFibonacii.addEventListener('submit', fibonaciiSubmitClick)