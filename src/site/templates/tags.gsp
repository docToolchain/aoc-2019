<!DOCTYPE html>
<html lang="en">
<% include 'header.gsp' %>
<body onload="prettyPrint()">
<div id="wrap">

    <% include "menu.gsp" %>
    <div class="container content">
    <div class="page-header">
        <h1>Tag: ${tag}</h1>
    </div>

    <!--<ul>-->
    <% def last_month = null; %>
    <% tag_posts.each { post -> %>
    <% if (last_month) { %>
    <% if (new java.text.SimpleDateFormat("MMMM yyyy", Locale.ENGLISH).format(post.date) != last_month) { %>
</ul>
    <h4>${new java.text.SimpleDateFormat("MMMM yyyy", Locale.ENGLISH).format(post.date)}</h4>
    <ul>
        <% } %>
        <% } else { %>
        <h4>${new java.text.SimpleDateFormat("MMMM yyyy", Locale.ENGLISH).format(post.date)}</h4>
        <ul>
            <% } %>

            <li>${post.date.format("dd")} - <a href="${content.rootpath}${post.uri}">${post.title}</a></li>
            <% last_month = new java.text.SimpleDateFormat("MMMM yyyy", Locale.ENGLISH).format(post.date) %>
            <% } %>
        </ul>
    </ul>
</div>

<div id="push"></div>

<% include "footer.gsp" %>

</body>
</html>