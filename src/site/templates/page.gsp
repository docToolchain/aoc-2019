<!DOCTYPE html>
<html lang="en">
    <% include 'header.gsp' %>
    <body onload="prettyPrint()">
        <div id="wrap">

            <% include "menu.gsp" %>

            <div class="container content">
                <p>${content.body}</p>
            </div>

            <div id="push"></div>
        </div>

        <% include "footer.gsp" %>

    </body>
</html>