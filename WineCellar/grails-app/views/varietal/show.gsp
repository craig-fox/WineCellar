
<%@ page import="winecellar.Varietal" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'varietal.label', default: 'Varietal')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-varietal" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-varietal" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list varietal">
			
				<g:if test="${varietalInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="varietal.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${varietalInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${varietalInstance?.color}">
				<li class="fieldcontain">
					<span id="color-label" class="property-label"><g:message code="varietal.color.label" default="Color" /></span>
					
						<span class="property-value" aria-labelledby="color-label"><g:fieldValue bean="${varietalInstance}" field="color"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${varietalInstance?.lifespan}">
				<li class="fieldcontain">
					<span id="lifespan-label" class="property-label"><g:message code="varietal.lifespan.label" default="Lifespan" /></span>
					
						<span class="property-value" aria-labelledby="lifespan-label"><g:fieldValue bean="${varietalInstance}" field="lifespan"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${varietalInstance?.maturity}">
				<li class="fieldcontain">
					<span id="maturity-label" class="property-label"><g:message code="varietal.maturity.label" default="Maturity" /></span>
					
						<span class="property-value" aria-labelledby="maturity-label"><g:fieldValue bean="${varietalInstance}" field="maturity"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${varietalInstance?.id}" />
					<g:link class="edit" action="edit" id="${varietalInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
