
<html>
<body>
<h1>Items:</h1>
<select>
<#list data as item>
<#--    <h2>${item.currencyName}</h2>-->

    <option value="${item.currencyName}">${item.currencyName}</option>
</#list>
</select>

<button id="demo">GET Somthing</button>
<h1>About me</h1>
<p>Welcome to my static page!</p>
<p>Feel free to take a look around.</p>
<p>Or go to the <a href="/">main page</a>.</p>


<script>


</script>
</body>
</html>
