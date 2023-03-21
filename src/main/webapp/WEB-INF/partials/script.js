
function toggleEditDisplay() {
    console.log("Toggled edit display");
    $("#confirm-edit-btn").toggleClass("d-none")
    $("#cancel-edit-btn").toggleClass("d-none")
    $("#delete-btn").toggleClass("d-none")
    $("#edit-btn").toggleClass("d-none")
}
function enableInputs(){
    console.log("Enabled inputs");
    $("#edit-title").prop("disabled", false)
    $("#edit-price").prop("disabled", false)
    $("#edit-description").prop("disabled", false)
}
function disableInputs(){
    console.log("Disabled inputs");
    $("#edit-title").prop("disabled", true)
    $("#edit-price").prop("disabled", true)
    $("#edit-description").prop("disabled", true)
}
$("#edit-btn").click(function(){
    toggleEditDisplay()
    enableInputs()
})
$("#cancel-edit-btn").click(function(){
    toggleEditDisplay()
    disableInputs()
})
