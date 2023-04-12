<html>
<body>
<h1>Currency Converter</h1>


<select id="from">
    <#list data as item>
    <#--    <h2>${item.currencyName}</h2>-->
        <option value="${item.currencyName}">${item.currencyName}</option>
    </#list>
</select>

<select id="to">
    <#list data as item>
    <#--    <h2>${item.currencyName}</h2>-->
        <option value="${item.currencyName}">${item.currencyName}</option>
    </#list>
</select>

<input id="inputAmount" type="text">

<button type="submit" id="demo">GET Somthing</button>
<h1 id="amount">0.0</h1>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>

<script type="text/javascript">

    $(document).ready(function () {
        $("#demo").click(function () {
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
                            document.getElementById('amount').innerHTML = res.value;
                        },200);
                        document.getElementById('amount').innerHTML = "Loading...";

                    }
                });
            });
        });
    });

</script>
</body>
</html>
