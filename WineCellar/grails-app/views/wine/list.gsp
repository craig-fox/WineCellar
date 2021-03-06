
<%@ page import="winecellar.Wine" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'wine.label', default: 'Wine')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-wine" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-wine" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="style" title="${message(code: 'wine.style.label', default: 'Style')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'wine.name.label', default: 'Name')}" />
					
						<th><g:message code="wine.varietal.label" default="Varietal" /></th>
					
						<th><g:message code="wine.winery.label" default="Winery" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${wineInstanceList}" status="i" var="wineInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${wineInstance.id}">${fieldValue(bean: wineInstance, field: "style")}</g:link></td>
					
						<td>${fieldValue(bean: wineInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: wineInstance, field: "varietal")}</td>
					
						<td>${fieldValue(bean: wineInstance, field: "winery")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${wineInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
