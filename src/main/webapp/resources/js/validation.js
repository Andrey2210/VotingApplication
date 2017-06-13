$(document).ready(function () {
    document.getElementById("confirmPassword").addEventListener("change", function () {
        resetError(this);
        if (this.value != document.getElementById("password").value) {
            showError(this, 'Passwords must matches.');
        }
    });
});

function showError(container, errorMessage) {
    var label = document.createElement("label");
    label.style.color = 'red';
    label.innerHTML = errorMessage;
    container.parentNode.insertBefore(label, container);
}

function resetError(container) {
    if (container.previousElementSibling.tagName == "LABEL") {
        container.previousElementSibling.remove();
    }
}
