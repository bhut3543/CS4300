<html>
<head>
<link rel="stylesheet" type="text/css" href="css/main.css">
  <title>Results</title>
</head>
<body>
  <h1>Information about: ${query}</h1>
    <#if query == "actors">
    <table border=1>
      <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Gender</th>
        <th>Film Count</th>
    </tr>
    <#list actors as actor>
      <tr><td>${actor.id}<td>${actor.firstName}<td>${actor.lastName}<td>${actor.gender}<td>${actor.filmCount}</tr>
    </#list>
  </table>
  </#if>

  <#if query == "movies">
  <table border=1>
    <tr>
    <th>Movie Name</th>
    <th>Movie ID</th>
    <th>Movie Year</th>
    <th>Movie Rank</th>
  </tr>
  <#list movies as movie>
    <tr><td>${movie.name}<td>${movie.id}<td>${movie.year}<td>${movie.rank}</tr>
  </#list>
  </table>
  </#if>

  <#if query == "directors">
  <table border=1>
    <tr>
    <th>First Name</th>
    <th>Last Name</th>
    <th>ID</th>
  </tr>
  <#list directors as director>
    <tr><td>${director.firstName}<td>${director.lastName}<td>${director.id}</tr>
  </#list>
  </table>
  </#if>

  <#if query == "roles">
  <table border=1>
    <tr>
    <th>Actor ID</th>
    <th>Movie ID</th>
    <th>Role</th>
  </tr>
  <#list roles as role>
    <tr><td>${role.actorId}<td>${role.movieId}<td>${role.role}</tr>
  </#list>
  </table>
  </#if>

  <#if query == "directors_genres">
  <table border=1>
    <tr>
    <th>Director ID</th>
    <th>Genre</th>
    <th>Prob</th>
  </tr>
  <#list dirgenre as dirgen>
    <tr><td>${dirgen.directorId}<td>${dirgen.genre}<td>${dirgen.prob}</tr>
  </#list>
  </table>
  </#if>

  <#if query == "movies_directors">
  <table border=1>
    <tr>
    <th>Director ID</th>
    <th>Movie ID</th>
  </tr>
  <#list moviedir as mvd>
    <tr><td>${mvd.directorId}<td>${mvd.movieId}</tr>
  </#list>
  </table>
  </#if>

  <#if query == "movies_genres">
  <table border=1>
    <tr>
    <th>Movie ID</th>
    <th>Genre</th>
  </tr>
  <#list moviegen as mvg>
    <tr><td>${mvg.movieId}<td>${mvg.genre}</tr>
  </#list>
  </table>
  </#if>
</body>
</html>
