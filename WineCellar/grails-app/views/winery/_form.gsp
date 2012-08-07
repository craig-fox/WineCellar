<%@ page import="winecellar.Winery" %>



<div class="fieldcontain ${hasErrors(bean: wineryInstance, field: 'location', 'error')} ">
	<label for="location">
		<g:message code="winery.location.label" default="Location" />
		
	</label>
	<g:textField name="location" value="${wineryInstance?.location}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: wineryInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="winery.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${wineryInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: wineryInstance, field: 'varietals', 'error')} ">
	<label for="varietals">
		<g:message code="winery.varietals.label" default="Varietals" />
		
	</label>
	<g:select name="varietals" from="${winecellar.Varietal.list()}" multiple="multiple" optionKey="id" size="5" value="${wineryInstance?.varietals*.id}" class="many-to-many"/>
</div>

