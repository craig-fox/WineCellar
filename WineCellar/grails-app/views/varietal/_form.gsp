<%@ page import="winecellar.Varietal" %>



<div class="fieldcontain ${hasErrors(bean: varietalInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="varietal.name.label" default="Name" />
		
	</label>
	<g:textField name="name" maxlength="50" value="${varietalInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: varietalInstance, field: 'color', 'error')} ">
	<label for="color">
		<g:message code="varietal.color.label" default="Color" />
		
	</label>
	<g:select name="color" from="${varietalInstance.constraints.color.inList}" value="${varietalInstance?.color}" valueMessagePrefix="varietal.color" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: varietalInstance, field: 'lifespan', 'error')} required">
	<label for="lifespan">
		<g:message code="varietal.lifespan.label" default="Lifespan" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="lifespan" from="${1..10}" class="range" required="" value="${fieldValue(bean: varietalInstance, field: 'lifespan')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: varietalInstance, field: 'maturity', 'error')} required">
	<label for="maturity">
		<g:message code="varietal.maturity.label" default="Maturity" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="maturity" type="number" value="${varietalInstance.maturity}" required=""/>
</div>

