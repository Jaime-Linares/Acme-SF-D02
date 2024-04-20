<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form> 
	<acme:input-textbox code="sponsor.invoice.form.label.code" path="code"/>
	<acme:input-moment code="sponsor.invoice.form.label.registrationTime" path="registrationTime"/>
	<acme:input-moment code="sponsor.invoice.form.label.dueDate" path="dueDate"/>
	<acme:input-money code="sponsor.invoice.form.label.quantity" path="quantity"/>
	<acme:input-double code="sponsor.invoice.form.label.tax" path="tax"/>
	<acme:input-url code="sponsor.invoice.form.label.link" path="link"/>
	<acme:input-checkbox code="sponsor.invoice.form.label.published" path="published" readonly="true"/>
	<acme:input-money code="sponsor.invoice.form.label.totalAmount" path="totalAmount" readonly="true"/>
	<acme:input-textbox code="sponsor.invoice.form.label.sponsorshipCode" path="sponsorshipCode" readonly="true"/>
	
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && published == false}">
			<acme:submit code="sponsor.invoice.form.button.update" action="/sponsor/invoice/update"/>
			<acme:submit code="sponsor.invoice.form.button.delete" action="/sponsor/invoice/delete"/>
		</jstl:when>	
	</jstl:choose>
</acme:form>