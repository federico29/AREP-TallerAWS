var apiclient = (function () {
    var url = window.location.href + "consultar";

    function getMessage() {
        axios.get(url).then(res => {
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
        console.log(url);
        return getMessage();
    }

    return {
        run: run
    };
})();
