  <head>
    <meta charset="utf-8"/>
    <title><%if (content.title) {%>${"Aoc 2019: "+content.title}<% } else { %>AoC 2019<% }%></title>
    <meta charset="UTF-8">
<meta name="google-site-verification" content="JZ7jk3duxzxHDLuOHKPxc-uoz0JPlSBLeaAxh3wGn9Q" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Just for fun">
    <meta name="author" content="Ralf D. Müller">
    <meta name="keywords" content="aoc">
    <meta name="generator" content="JBake">

    <!-- Le styles -->
    <link href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>css/bootstrap.min.css" rel="stylesheet">
    <link href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>css/asciidoctor.css" rel="stylesheet">
    <link href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>css/base.css" rel="stylesheet">
    <link href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>css/prettify.css" rel="stylesheet">
      <link href="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>css/retro.css" rel="stylesheet">
      <style>
      @media only screen and (min-width:768px){
          #toctitle{font-size:1.375em}
          #toc.toc{margin-top:0!important;background-color:#f8f8f7;position:fixed;width:15em;left:0;top:0;border-right:1px solid #efefed;border-top-width:0!important;border-bottom-width:0!important;z-index:1000;padding:1.25em 1em;height:100%;overflow:auto; padding-top: 60px;}
          #toc.toc #toctitle{margin-top:0;margin-bottom:.8rem;font-size:1.2em}
          #toc.toc>ul{font-size:.9em;margin-bottom:0}
          #toc.toc ul ul{margin-left:0;padding-left:1em}
          #toc.toc ul.sectlevel0 ul.sectlevel1{padding-left:0;margin-top:.5em;margin-bottom:.5em}
          body.toc2{ padding-left: 15.5em;padding-right:0; width: 100% !important;}
          body.toc2 div#wrap div.content {width: auto !important;}
          #toc.toc{width:15em; padding-top: 60px;}
      }
      @media only screen and (min-width:1280px){
          body.toc2{ padding-left: 20.5em;padding-right:0; width: 100% !important;}
          body.toc2 div#wrap div.content {width: auto !important;}
          #toc.toc{width:20em; padding-top: 60px;}
          #toc.toc #toctitle{font-size:1.375em}
          #toc.toc>ul{font-size:.95em}
          #toc.toc ul ul{padding-left:1.25em}
      }
      body {
          overflow-y: scroll;
      }
      html, body {
          font-size: 1.06rem;
      }
      body.toc2{ overflow-x: auto}
      #toc.toc ul {
          padding-inline-start: 0;
      }
      :target::before {
          content: "";
          display: block;
          height: 60px; /* fixed header height*/
          margin: -60px 0 0; /* negative fixed header height */
      }
      </style>

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="<%if (content.rootpath) {%>${content.rootpath}<% } else { %><% }%>js/html5shiv.min.js"></script>
    <![endif]-->

      <link rel="apple-touch-icon" sizes="180x180" href="/apple-touch-icon.png">
      <link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png">
      <link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
      <link rel="manifest" href="/site.webmanifest">
      <link rel="mask-icon" href="/safari-pinned-tab.svg" color="#5bbad5">
      <meta name="msapplication-TileColor" content="#da532c">
      <meta name="theme-color" content="#ffffff">
  </head>
