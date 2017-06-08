$(document).ready(function () {
    $("#addVote").click(function () {
        addVote();
    });
    $(".deleteAnswer").click(function () {
        deleteAnswer(this);
    });
    $("#addAnswer").click(function () {
        addAnswer(this);
    });

});

function addVote() {
    var creteVoteBlock = document.getElementById('createVote');
    if (creteVoteBlock.style.display == 'block') {
        creteVoteBlock.style.display = 'none';
    }
    else {
        creteVoteBlock.style.display = 'block';
    }
}

function deleteAnswer(button) {
    var parentBlock = button.parentNode;
    parentBlock.remove();
}

function addAnswer(button) {
    var formElement = document.getElementById("createVote");
    var div = document.createElement('div');
    var input = document.createElement('input');
    var delButton = document.createElement('button');
    input.type = 'text';
    input.placeholder = 'Вариант ответа';
    input.name = 'answer';
    delButton.className = 'deleteAnswer';
    delButton.innerHTML = 'удалить';
    delButton.addEventListener('click', function () {
        deleteAnswer(this);
    });
    div.appendChild(input);
    div.appendChild(delButton);
    div.appendChild(document.createElement('br'))

    formElement.insertBefore(div, formElement.lastElementChild);
}

function sendVoteForm() {
    var voteElements = document.forms["createVote"].elements;
    var question = voteElements["question"].value;
    var answers = [];
    for (var index = 0; index < voteElements.length; index++) {
        if (voteElements[index].name == "answer") {
            answers.push({"text": voteElements[index].value, "votersNumber": 0});
        }
    }
    var voting = {
        "question": '' + question,
        "answers": answers.toString()
    };
    $.ajax({
        accept: "application/json",
        contentType: "application/json",
        dataType: "json",
        type: 'POST',
        url: '/votings',
        data: voting,
        success: function (data) {
            alert(data.message + "\n" + data.url)
        },
        error: function (data) {
            alert(data);
        }
    });

}
