
<%@ page import="winecellar.Wine" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'wine.label', default: 'Wine')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-wine" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-wine" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list wine">
			
				<g:if test="${wineInstance?.style}">
				<li class="fieldcontain">
					<span id="style-label" class="property-label"><g:message code="wine.style.label" default="Style" /></span>
					
						<span class="property-value" aria-labelledby="style-label"><g:fieldValue bean="${wineInstance}" field="style"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wineInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="wine.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${wineInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wineInstance?.varietal}">
				<li class="fieldcontain">
					<span id="varietal-label" class="property-label"><g:message code="wine.varietal.label" default="Varietal" /></span>
					
						<span class="property-value" aria-labelledby="varietal-label"><g:link controller="varietal" action="show" id="${wineInstance?.varietal?.id}">${wineInstance?.varietal?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${wineInstance?.winery}">
				<li class="fieldcontain">
					<span id="winery-label" class="property-label"><g:message code="wine.winery.label" default="Winery" /></span>
					
						<span class="property-value" aria-labelledby="winery-label"><g:link controller="winery" action="show" id="${wineInstance?.winery?.id}">${wineInstance?.winery?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${wineInstance?.id}" />
					<g:link class="edit" action="edit" id="${wineInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
