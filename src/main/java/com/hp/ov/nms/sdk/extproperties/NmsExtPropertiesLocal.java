package com.hp.ov.nms.sdk.extproperties;

//import javax.ejb.Local;

import com.hp.ov.nms.sdk.inventory.CustomAttribute;

//@Local
public interface NmsExtPropertiesLocal {
     void setProperty(String domain, String key, String label, String type, String value);
     void removeProperty(String domain, String key);
     String getPropertyValue(String domain, String key);
     CustomAttribute[] getProperties(String domain);
}
