<!DOCTYPE html>
<html lang="en">
<head>
        <meta charset="UTF-8"/>
        <title>How to create currency converter</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
        <link rel="stylesheet" href="/static/style.css"/>
        <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

<!-- Add Main.js File -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script src="/static/main.js"></script>

<div class="currency-row-outer">
        <div class="currency-converter">
                <h2>Currency Converter</h2>

                <div class="field grid-50-50">
                        <div class="colmun col-left">
                                <input type="number" class="form-input" id="inputAmount" placeholder="00000">
                        </div>
                        <div class="colmun col-right">
                                <div class="select">
                                        <select id="from" class="currency">
                                                <#list data as item>
                                                        <option value="${item.currencyName}">${item.currencyName}</option>
                                                </#list>
                                        </select>
                                </div>
                        </div>
                </div>

                <div class="field grid-50-50">
                        <div class="colmun col-left">
                                <input type="text" class="form-input" id="amount" placeholder="00000" disabled>
                        </div>
                        <div class="colmun col-right">
                                <div class="select">
                                        <select id="to" class="currency" >
                                                <#list data as item>
                                                        <option value="${item.currencyName}">${item.currencyName}</option>
                                                </#list>
                                        </select>
                                </div>
                        </div>
                </div>

                <div class="field grid-50-50">
                        <button class="ui-button"  type="button" id="btnConvert">Convert</button>
                </div>


        </div>
</div>
</body>
</html>