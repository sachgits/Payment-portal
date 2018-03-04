<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>

<h4>GET</h4>
<em>Check balance</em>
<hr>
<form id="getForm" action="http://localhost:8080/payment_service/account/v1" method="get">
    <label>Account:</label> &nbsp;
    <input type="text" name="account" required="true" minlength="1" maxlength="10"/> <br/>

    <input type="button" value="Search" onclick="getAccount()"/>
</form>

<h4>POST</h4>
<em>Create account</em>
<hr>
<form id="postForm" action="http://localhost:8080/payment_service/account/v1" method="post">
    <label>Name:</label> &nbsp;
    <input type="text" name="account" required="true" minlength="2" maxlength="10"/> <br/>

    <label>Type:</label> &nbsp;
    <select name="type" required="true">
        <option value="0">Electron</option>
        <option value="1">Visa</option>
        <option value="2">Master</option>
    </select></br>

    <label>Amount:</label> &nbsp;
    <input type="number" name="amount" value="0" required="true" minlength="1" maxlength="10"/><br/>

    <input type="button" value="Finish" onclick="createAccount()"/>
</form>

<h4>PUT</h4>
<em>Withdraw money</em>
<hr>
<form id="putForm" action="http://localhost:8080/payment_service/account/v1" method="put">
    <label>Account:</label> &nbsp;
    <input type="text" name="account" required="true" minlength="1" maxlength="10"/> </br>

    <label>Amount:</label> &nbsp;
    <input type="number" name="amount" value="10" required="true" minlength="1" maxlength="10"/></br>

    <input type="button" value="Withdraw" onclick="updateAccount()"/>
</form>

<h4>DELETE</h4>
<em>Remove account</em>
<hr>
<form id="deleteForm" action="http://localhost:8080/payment_service/account/v1" method="delete">
    <label>Account:</label>&nbsp;
    <input type="text" name="account" required="true" minlength="1" maxlength="10"/> </br>

    <input type="button" value="Remove" onclick="deleteAccount()"/>
</form>

</body>

<script type="application/javascript">
    var URL = "http://localhost:8080/payment_service/account/v1";

    function createRequest() {
        // For browsers other than ie
        if (window.XMLHttpRequest) {
            return new window.XMLHttpRequest;
        } else { // for ie
            return new ActiveXObject("Microsoft.XMLHTTP");
        }
    }

    function getAccount() {
        var htmlForm = document.getElementById("getForm");

        if (!htmlForm['account'].value || htmlForm['account'].value.length == 0) {
            alert("Insert account name or id");
            return;
        }

        var xhttp = createRequest();
        xhttp.open("GET", URL + "/" + htmlForm['account'].value, true);
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4) {
                showResults(xhttp);
            }
        };
        xhttp.send();
    }

    function createAccount() {
        var htmlForm = document.getElementById("postForm");

        if (!htmlForm['account'].value || htmlForm['account'].value.length == 0) {
            alert("Insert account name or id");
        } else if (!htmlForm['amount'].value || htmlForm['amount'].value.length == 0) {
            alert("Insert total amount");
        } else if (!htmlForm['type'].value || htmlForm['type'].value.length == 0) {
            alert("Insert card type");
        } else {
            var xhttp = createRequest();
            xhttp.open("POST", URL, true);
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4) {
                    showResults(xhttp);
                }

            };
            var account = new Object();
            account.name = htmlForm['account'].value;
            account.amount = htmlForm['amount'].value;
            account.type = htmlForm['type'].value;
            xhttp.setRequestHeader("Content-type", "application/json");
            xhttp.send(JSON.stringify(account));
        }
    }

    function updateAccount() {
        var htmlForm = document.getElementById("putForm");

        if (!htmlForm['account'].value || htmlForm['account'].value.length == 0) {
            alert("Insert account name or id");
        } else if (!htmlForm['amount'].value || htmlForm['amount'].value.length == 0) {
            alert("Insert total amount");
        } else {
            var xhttp = createRequest();
            xhttp.open("PUT", URL, true);
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4) {
                    showResults(xhttp);
                }

            };
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("name=" + htmlForm['account'].value + "&amount=" + htmlForm['amount'].value);
        }
    }

    function deleteAccount() {
        var htmlForm = document.getElementById("deleteForm");

        if (!htmlForm['account'].value || htmlForm['account'].value.length == 0) {
            alert("Insert account name or id");
        } else {
            var xhttp = createRequest();
            xhttp.open("DELETE", URL, true);
            xhttp.onreadystatechange = function () {
                if (this.readyState == 4) {
                    showResults(this);
                }

            };
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("name=" + htmlForm['account'].value);
        }
    }

    function showResults(xhttp) {
        var text = "SERVER: " + xhttp.status + "\n" + xhttp.responseText;
        alert(text);
    }

</script>
</html>
