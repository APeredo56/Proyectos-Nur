const numero1 = document.getElementById("num1");
const numero2 = document.getElementById("num2");
const numero3 = document.getElementById("num3");
const lbOutput = document.getElementById("greaterOutput");
const submitGreater = document.getElementById("formGreater");

function greaterSubmitClick(event){
    if(numero1.value == ""){
        numero1.insertAdjacentElement("beforebegin", emptyError);
        return;
    }
    if(numero2.value == ""){
        numero2.insertAdjacentElement("beforebegin", emptyError);
        return
    }
    if(numero3.value == ""){
        numero3.insertAdjacentElement("beforebegin", emptyError);
        return
    }
    lbOutput.textContent = getGreater(numero1.value, numero2.value, numero3.value);
    submitGreater.removeChild(emptyError);
}

submitGreater.addEventListener('submit', greaterSubmitClick);
