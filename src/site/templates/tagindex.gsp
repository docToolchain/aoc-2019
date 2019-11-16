<!DOCTYPE html>
<html lang="en">
<% include 'header.gsp' %>
<body onload="prettyPrint()">
<div id="wrap">

    <% include "menu.gsp" %>
    <div class="container content">
        <div class="page-header">
            <h1>Blog by Tag</h1>
<div>
    <%
    alltags.sort().each { tag ->
        tag = tag.trim()
        def postsCount = posts.findAll { post ->
            post.status == "published" && post.tags?.contains(tag)
        }.size()
    %>
    <span><a href="${config.site_contextPath}tags/${tag.replace(' ', '-')}.html">${tag}&nbsp;<span class="badge">${postsCount}</span></a></span>

    <%
    }
    %>

</div>
        </div>
${tag_posts.dump()}
${content.dump()}
        <!--<ul>-->
        <% def last_month = true; %>
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