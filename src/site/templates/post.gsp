<!DOCTYPE html>
<html lang="en">
<%include 'header.gsp'%>
<body onload="prettyPrint()" >
<div id="wrap">

	<%include "menu.gsp"%>
	<div class="container content">
	<div class="page-header">
		<h1>${content.title}</h1>
<div>Tags:
    <%
    content.tags.sort().each { tag ->
        tag = tag.trim()
    %>
    <span><a href="${config.site_contextPath}tags/${tag.replace(' ', '-')}.html">${tag}</a></span>

    <%
    }
    %>

</div>
	</div>

	<p><em>${new java.text.SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(content.date)}</em></p>

	<p>${content.body}</p>

</div>

</div>
<div id="push"></div>
</div>

<%include "footer.gsp"%>

</body>
</html>