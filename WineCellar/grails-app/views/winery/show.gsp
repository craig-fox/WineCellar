
<%@ page import="winecellar.Winery" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'winery.label', default: 'Winery')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-winery" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-winery" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list winery">
			
				<g:if test="${wineryInstance?.location}">
				<li class="fieldcontain">
					<span id="location-label" class="property-label"><g:message code="winery.location.label" default="Location" /></span>
					
						<span class="property-value" aria-labelledby="location-label"><g:fieldValue bean="${wineryInstance}" field="location"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wineryInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="winery.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${wineryInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wineryInstance?.varietals}">
				<li class="fieldcontain">
					<span id="varietals-label" class="property-label"><g:message code="winery.varietals.label" default="Varietals" /></span>
					
						<g:each in="${wineryInstance.varietals}" var="v">
						<span class="property-value" aria-labelledby="varietals-label"><g:link controller="varietal" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${wineryInstance?.id}" />
					<g:link class="edit" action="edit" id="${wineryInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
