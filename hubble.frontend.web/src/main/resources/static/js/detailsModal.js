$('#myModal').on('show.bs.modal', function (e) {
    var getIdFromRow = $(e.relatedTarget);
    fillModal(getIdFromRow[0].id)
});

$("#myModal").on("hidden.bs.modal", function () {
    $("#myModalData").html("");
});

function fillModal(id) {
    var idList = id.split("_");
    var obj = dataList[idList[0]]['disponibilidadPerformance' + idList[2] + 'MinutosList'];
    document.getElementById("modal_title").textContent = dataList[idList[0]]['name'];
    document.getElementById("modal_button_pressed").textContent = 'informacion de los ultimos ' + idList[2] + ' Minutos';

    if (obj == null || obj == undefined) {
        return;
    }
    for (i = 0; i < obj.length; i++) {
        $('#myModalData').append('<tr><td>' + obj[i]['name'] + '</td><td>' + obj[i]['performance'] + '</td><td>' + obj[i]['tiempoDeRespuesta'] + '</td><td>' + obj[i]['disponibilidad'] + '</td><td>' + obj[i]['date'] + '</td></tr>');
    }
}
