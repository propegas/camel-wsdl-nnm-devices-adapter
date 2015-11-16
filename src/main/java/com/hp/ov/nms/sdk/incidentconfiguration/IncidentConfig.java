package com.hp.ov.nms.sdk.incidentconfiguration;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class IncidentConfig extends BaseIncidentConfig implements Serializable {
	//NOTE!!!! The XmlAccessorType annotation causes the wsdl to be generated with attributes in the order they appear below.
	//Prior releases generated wsdl with the attributes in alphabetic order
	//SOOOO, they need to stay in alphabetic order here for backwards compatibility!!!!
	private ActionConfig actionConfig;
	private String author="";
	private String category="";
	private DedupConfig dedupConfig;
	private String description="";
	private String family="";
	private String messageFormat="";
	private RateConfig rateConfig;
	private String severity="";


	public ActionConfig getActionConfig() {
		return actionConfig;
	}

	public void setActionConfig(ActionConfig actionConfig) {
		this.actionConfig = actionConfig;
	}

	public RateConfig getRateConfig() {
		return rateConfig;
	}

	public void setRateConfig(RateConfig rateConfig) {
		this.rateConfig = rateConfig;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMessageFormat() {
		return messageFormat;
	}

	public void setMessageFormat(String messageFormat) {
		this.messageFormat = messageFormat;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public DedupConfig getDedupConfig() {
		return dedupConfig;
	}

	public void setDedupConfig(DedupConfig dedupConfig) {
		this.dedupConfig = dedupConfig;
	}








}
