$(document).ready(function () {
    $("#btnConvert").click(function () {
        const from = document.getElementById("from").value;
        const to = document.getElementById("to").value
        const amountInput = document.getElementById("inputAmount").value
        $(document).ready(function () {
            $.ajax({
                url: "/convert/" + from + "/" + to + "/"+ amountInput,
                type: 'GET',
                dataType: 'json', // added data type
                success: function (res) {
                    console.log(res.value);
                    setTimeout(function () {
                        // Whatever you want to do after the wait
                        document.getElementById('amount').value = res.value;
                    },200);
                    document.getElementById('amount').value = "Loading...";
                }
            });
        });
    });
});
