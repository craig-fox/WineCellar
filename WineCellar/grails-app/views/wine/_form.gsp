<%@ page import="winecellar.Wine" %>



<div class="fieldcontain ${hasErrors(bean: wineInstance, field: 'style', 'error')} ">
	<label for="style">
		<g:message code="wine.style.label" default="Style" />
		
	</label>
	<g:select name="style" from="${wineInstance.constraints.style.inList}" value="${wineInstance?.style}" valueMessagePrefix="wine.style" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: wineInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="wine.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${wineInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: wineInstance, field: 'varietal', 'error')} required">
	<label for="varietal">
		<g:message code="wine.varietal.label" default="Varietal" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="varietal" name="varietal.id" from="${winecellar.Varietal.list()}" optionKey="id" required="" value="${wineInstance?.varietal?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: wineInstance, field: 'winery', 'error')} required">
	<label for="winery">
		<g:message code="wine.winery.label" default="Winery" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="winery" name="winery.id" from="${winecellar.Winery.list()}" optionKey="id" required="" value="${wineInstance?.winery?.id}" class="many-to-one"/>
</div>

