function unLogin()
{
    axios({
        method: "DELETE",
        url: "/api/unlogin"
    })
    .then(function (response) {
        window.location = "/login";
    })
    .catch(function (error) {
        console.log(error);
    });
}
