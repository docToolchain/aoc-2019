<%
    //tag::map[]
    def nav = [
        title: 'AoC-2019',
        entries: [
          'Rules': 'rules/',
          'Solutions': 'solutions/',
          'TIL': 'til/',
          'Challenges': 'news/',
          'About': 'about/',
        ]
    ]
    //end::map[]
/**
def nav = [
        title: 'Docs-as-Co.de',
        entries: [:]
]
System.err.println "processing menu"

published_pages.each { page ->
    def source = page.sourceuri
    def menu = source.split("/")[0]
    def item = source.split("/")[1].split("[.]")[0]
    if (!nav.entries[menu]) {
        nav.entries[menu] = [:]
    }
    nav.entries[menu][item]=menu+'/'+item
}
    **/
%>
	<!-- Fixed navbar -->
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>">${nav.title}</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
              <% nav.entries.each { key, value -> %>
                <% if (value.class == String.class) { %>
                  <li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>${value}${value.endsWith('/')?'':'.html'}">${key}</a></li>
                <% } else { %>
                  <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">${key}<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                      <% value.each { it -> %>
                        <li><a href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>${it.value}${it.value.endsWith('/')?'':'.html'}">${it.key}</a></li>
                      <% } %>
                    </ul>
                  </li>
                <% } %>
              <% } %>
          </ul>
            <!-- tag::search[] -->
            <form class="navbar-form navbar-right" action="https://google.de/search" style="border: none;">
                <div class="form-group">
                    <input type="hidden" name="q" value="site:aoc-2019.netlify.com">
                    <input type="text" name="q" class="form-control" id="search">
                    <button type="submit" class="btn btn-default hidden-sm ">Find</button>
                </div>
            </form>
            <!-- end::search[] -->
        </div><!--/.nav-collapse -->
      </div>
    </div>
