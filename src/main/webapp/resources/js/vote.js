$(document).ready(function () {
    $(".deleteAnswer").click(function () {
        deleteAnswer(this);
    });
    $("#addAnswer").click(function () {
        addAnswer(this);
    });
});

function deleteAnswer(button) {
    var parentBlock = button.parentNode;
    parentBlock.remove();
}

function addAnswer(button) {
    var formElement = document.getElementById("voteForm");
    var div = document.createElement('div');
    div.className = 'form-group';
    var input = document.createElement('input');
    var delButton = document.createElement('button');
    input.type = 'text';
    input.placeholder = 'Possible answer';
    input.name = 'answer';
    input.className = 'form-control';
    input.required = 'required';
    delButton.className = 'deleteAnswer btn btn-danger';
    delButton.innerHTML = 'Delete';
    delButton.addEventListener('click', function () {
        deleteAnswer(this);
    });
    div.appendChild(input);
    div.appendChild(delButton);

    formElement.insertBefore(div, formElement.lastElementChild);
}
