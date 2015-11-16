package com.hp.ov.nms.sdk.incidentconfiguration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DedupConfig {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
	private String comparisonCriteria;
	private ComparisonParam[] comparisonParams;
	private String correlationIncidentConfigName;
	private String enable;

	public String getCorrelationIncidentConfigName() {
		return correlationIncidentConfigName;
	}
	public void setCorrelationIncidentConfigName(
			String correlationIncidentConfigName) {
		this.correlationIncidentConfigName = correlationIncidentConfigName;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public String getComparisonCriteria() {
		return comparisonCriteria;
	}
	public void setComparisonCriteria(String comparisonCriteria) {
		this.comparisonCriteria = comparisonCriteria;
	}
	public ComparisonParam[] getComparisonParams() {
		return comparisonParams;
	}
	public void setComparisonParams(ComparisonParam[] comparisonParams) {
		this.comparisonParams = comparisonParams;
	}


}
