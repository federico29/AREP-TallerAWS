var apiclient = (function () {
    var urlGet = window.location.href + "get";
    var urlPost = window.location.href + "post";

    function sendMessage() {
        var mensaje = document.getElementById("mensaje").value;
        console.log(mensaje);
        axios.post(urlPost, {"valor": mensaje})
                .then(res => {
                    getMessage();
                }
                );
    }

    function getMessage() {
        axios.get(urlGet).then(res => {
            var datos = res.data;
            var num = 1;
            $("#cuerpo").html("");
            datos.cadenas.map(function (y) {
                $("#cuerpo").append(
                        "<tr>" +
                        "<td>" + num + "</td>" +
                        "<td>" + y.valor + "</td>" +
                        "<td>" + y._id.date + "</td>" +
                        "</tr>"
                        );
                num = num + 1;
            });
        });
    }

    function run() {
        console.log(urlGet);
        return getMessage();
    }

    return {
        run: run,
        sendMessage:sendMessage
    };
})();
