package com.dhcc.bussiness.sxydidc.alarm.models;

// Generated 2016-11-1 10:20:58 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * AlarmRule generated by hbm2java
 */
public class AlarmRule implements java.io.Serializable {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AlarmRule [ruleId=" + ruleId + ", alarmIndicator="
				+ alarmIndicator + ", alarmRuleValueType=" + alarmRuleValueType
				+ ", alarmSeverity=" + alarmSeverity + ", name=" + name
				+ ", isSuppress=" + isSuppress + ", value=" + value
				+ ", valueField=" + valueField + "]";
	}

	private BigDecimal ruleId;
	private AlarmIndicator alarmIndicator;
	private AlarmRuleValueType alarmRuleValueType;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((alarmIndicator == null) ? 0 : alarmIndicator.hashCode());
		result = prime * result + ((ruleId == null) ? 0 : ruleId.hashCode());
		result = prime * result
				+ ((valueField == null) ? 0 : valueField.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlarmRule other = (AlarmRule) obj;
		if (alarmIndicator == null) {
			if (other.alarmIndicator != null)
				return false;
		} else if (!alarmIndicator.equals(other.alarmIndicator))
			return false;
		if (ruleId == null) {
			if (other.ruleId != null)
				return false;
		} else if (!ruleId.equals(other.ruleId))
			return false;
		if (valueField == null) {
			if (other.valueField != null)
				return false;
		} else if (!valueField.equals(other.valueField))
			return false;
		return true;
	}

	private AlarmSeverity alarmSeverity;
	private String name;
	private Character isSuppress;
	private String value;
	private BigDecimal valueField;
	private Set<AlarmNoticeType> alarmNoticeTypes = new HashSet<AlarmNoticeType>(
			0);
	private Set<Tsuser> tsusers = new HashSet<Tsuser>(0);

	public AlarmRule() {
	}

	public AlarmRule(BigDecimal ruleId, AlarmIndicator alarmIndicator,
			AlarmSeverity alarmSeverity) {
		this.ruleId = ruleId;
		this.alarmIndicator = alarmIndicator;
		this.alarmSeverity = alarmSeverity;
	}

	public AlarmRule(BigDecimal ruleId, AlarmIndicator alarmIndicator,
			AlarmRuleValueType alarmRuleValueType, AlarmSeverity alarmSeverity,
			String name, Character isSuppress, String value,
			BigDecimal valueField, Set<AlarmNoticeType> alarmNoticeTypes,
			Set<Tsuser> tsusers) {
		this.ruleId = ruleId;
		this.alarmIndicator = alarmIndicator;
		this.alarmRuleValueType = alarmRuleValueType;
		this.alarmSeverity = alarmSeverity;
		this.name = name;
		this.isSuppress = isSuppress;
		this.value = value;
		this.valueField = valueField;
		this.alarmNoticeTypes = alarmNoticeTypes;
		this.tsusers = tsusers;
	}

	public BigDecimal getRuleId() {
		return this.ruleId;
	}

	public void setRuleId(BigDecimal ruleId) {
		this.ruleId = ruleId;
	}

	public AlarmIndicator getAlarmIndicator() {
		return this.alarmIndicator;
	}

	public void setAlarmIndicator(AlarmIndicator alarmIndicator) {
		this.alarmIndicator = alarmIndicator;
	}

	public AlarmRuleValueType getAlarmRuleValueType() {
		return this.alarmRuleValueType;
	}

	public void setAlarmRuleValueType(AlarmRuleValueType alarmRuleValueType) {
		this.alarmRuleValueType = alarmRuleValueType;
	}

	public AlarmSeverity getAlarmSeverity() {
		return this.alarmSeverity;
	}

	public void setAlarmSeverity(AlarmSeverity alarmSeverity) {
		this.alarmSeverity = alarmSeverity;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Character getIsSuppress() {
		return this.isSuppress;
	}

	public void setIsSuppress(Character isSuppress) {
		this.isSuppress = isSuppress;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public BigDecimal getValueField() {
		return this.valueField;
	}

	public void setValueField(BigDecimal valueField) {
		this.valueField = valueField;
	}

	public Set<AlarmNoticeType> getAlarmNoticeTypes() {
		return this.alarmNoticeTypes;
	}

	public void setAlarmNoticeTypes(Set<AlarmNoticeType> alarmNoticeTypes) {
		this.alarmNoticeTypes = alarmNoticeTypes;
	}

	public Set<Tsuser> getTsusers() {
		return this.tsusers;
	}

	public void setTsusers(Set<Tsuser> tsusers) {
		this.tsusers = tsusers;
	}

}
