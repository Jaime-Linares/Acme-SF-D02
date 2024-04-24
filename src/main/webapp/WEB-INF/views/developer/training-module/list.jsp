<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="developer.trainingModule.list.label.code" path="code" width="10%"/>
	<acme:list-column code="developer.trainingModule.list.label.details" path="details" width="80%"/>
	<acme:list-column code="developer.trainingModule.list.label.difficultyLevel" path="difficultyLevel" width="10%"/>

</acme:list>

<acme:button code="developer.trainingModule.list.button.create" action="/developer/training-module/create"/>