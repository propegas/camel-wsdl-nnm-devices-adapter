
package com.hp.ov.nms.sdk.snmp;


/**
 * <p>Java class for asn1Constant.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="asn1Constant">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ASN_UNIVERSAL"/>
 *     &lt;enumeration value="ASN_APPLICATION"/>
 *     &lt;enumeration value="ASN_CONTEXT"/>
 *     &lt;enumeration value="ASN_PRIVATE"/>
 *     &lt;enumeration value="ASN_PRIMITIVE"/>
 *     &lt;enumeration value="ASN_CONSTRUCTOR"/>
 *     &lt;enumeration value="ASN_LONG_LEN"/>
 *     &lt;enumeration value="ASN_LENGTH_MASK"/>
 *     &lt;enumeration value="ASN_EXTENSION_ID"/>
 *     &lt;enumeration value="ASN_BIT8"/>
 *     &lt;enumeration value="ASN_7BITMASK"/>
 *     &lt;enumeration value="ASN_BOOLEAN"/>
 *     &lt;enumeration value="ASN_INTEGER"/>
 *     &lt;enumeration value="ASN_BIT_STR"/>
 *     &lt;enumeration value="ASN_U_INTEGER"/>
 *     &lt;enumeration value="ASN_OCTET_STR"/>
 *     &lt;enumeration value="ASN_NULL"/>
 *     &lt;enumeration value="ASN_OBJECT_ID"/>
 *     &lt;enumeration value="ASN_SEQUENCE"/>
 *     &lt;enumeration value="ASN_SET"/>
 *     &lt;enumeration value="ASN_IPADDRESS"/>
 *     &lt;enumeration value="ASN_COUNTER"/>
 *     &lt;enumeration value="ASN_GAUGE"/>
 *     &lt;enumeration value="ASN_TIMETICKS"/>
 *     &lt;enumeration value="ASN_OPAQUE"/>
 *     &lt;enumeration value="ASN_COUNTER64"/>
 *     &lt;enumeration value="ASN_UNSIGNED32"/>
 *     &lt;enumeration value="ASN_GAUGE32"/>
 *     &lt;enumeration value="ASN_COUNTER32"/>
 *     &lt;enumeration value="ASN_NOSUCHOBJECT"/>
 *     &lt;enumeration value="ASN_NOSUCHINSTANCE"/>
 *     &lt;enumeration value="ASN_ENDOFMIBVIEW"/>
 *     &lt;enumeration value="ASN_SNMP_MSG"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
public enum Asn1Constant {

    ASN_UNIVERSAL("ASN_UNIVERSAL"),
    ASN_APPLICATION("ASN_APPLICATION"),
    ASN_CONTEXT("ASN_CONTEXT"),
    ASN_PRIVATE("ASN_PRIVATE"),
    ASN_PRIMITIVE("ASN_PRIMITIVE"),
    ASN_CONSTRUCTOR("ASN_CONSTRUCTOR"),
    ASN_LONG_LEN("ASN_LONG_LEN"),
    ASN_LENGTH_MASK("ASN_LENGTH_MASK"),
    ASN_EXTENSION_ID("ASN_EXTENSION_ID"),
    ASN_BIT_8("ASN_BIT8"),
    ASN_7_BITMASK("ASN_7BITMASK"),
    ASN_BOOLEAN("ASN_BOOLEAN"),
    ASN_INTEGER("ASN_INTEGER"),
    ASN_BIT_STR("ASN_BIT_STR"),
    ASN_U_INTEGER("ASN_U_INTEGER"),
    ASN_OCTET_STR("ASN_OCTET_STR"),
    ASN_NULL("ASN_NULL"),
    ASN_OBJECT_ID("ASN_OBJECT_ID"),
    ASN_SEQUENCE("ASN_SEQUENCE"),
    ASN_SET("ASN_SET"),
    ASN_IPADDRESS("ASN_IPADDRESS"),
    ASN_COUNTER("ASN_COUNTER"),
    ASN_GAUGE("ASN_GAUGE"),
    ASN_TIMETICKS("ASN_TIMETICKS"),
    ASN_OPAQUE("ASN_OPAQUE"),
    ASN_COUNTER_64("ASN_COUNTER64"),
    ASN_UNSIGNED_32("ASN_UNSIGNED32"),
    ASN_GAUGE_32("ASN_GAUGE32"),
    ASN_COUNTER_32("ASN_COUNTER32"),
    ASN_NOSUCHOBJECT("ASN_NOSUCHOBJECT"),
    ASN_NOSUCHINSTANCE("ASN_NOSUCHINSTANCE"),
    ASN_ENDOFMIBVIEW("ASN_ENDOFMIBVIEW"),
    ASN_SNMP_MSG("ASN_SNMP_MSG");
    private final String value;

    Asn1Constant(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Asn1Constant fromValue(String v) {
        for (Asn1Constant c: Asn1Constant.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
