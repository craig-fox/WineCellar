<%@ page import="winecellar.Bottle" %>



<div class="fieldcontain ${hasErrors(bean: bottleInstance, field: 'wine', 'error')} required">
	<label for="wine">
		<g:message code="bottle.wine.label" default="Wine" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="wine" name="wine.id" from="${winecellar.Wine.list()}" optionKey="id" required="" value="${bottleInstance?.wine?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bottleInstance, field: 'year', 'error')} required">
	<label for="year">
		<g:message code="bottle.year.label" default="Year" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="year" type="number" value="${bottleInstance.year}" required=""/>
</div>

