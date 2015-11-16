package com.hp.ov.nms.sdk.incidentconfig;

import java.util.logging.Logger;

import org.jboss.ejb3.annotation.Management;
import org.jboss.ejb3.annotation.Service;

import com.hp.ov.nms.sdk.client.SampleClient;
import com.hp.ov.nms.sdk.filter.Constraint;
import com.hp.ov.nms.sdk.filter.Filter;
import com.hp.ov.nms.sdk.incidentconfiguration.ActionConfig;
import com.hp.ov.nms.sdk.incidentconfiguration.ComparisonParam;
import com.hp.ov.nms.sdk.incidentconfiguration.DedupConfig;
import com.hp.ov.nms.sdk.incidentconfiguration.IncidentConfig;
import com.hp.ov.nms.sdk.incidentconfiguration.LifecycleTransitionAction;
import com.hp.ov.nms.sdk.incidentconfiguration.ManagementEventConfig;
import com.hp.ov.nms.sdk.incidentconfiguration.NmsIncidentConfig;
import com.hp.ov.nms.sdk.incidentconfiguration.NmsIncidentConfigException;
import com.hp.ov.nms.sdk.incidentconfiguration.PairItem;
import com.hp.ov.nms.sdk.incidentconfiguration.PairwiseConfig;
import com.hp.ov.nms.sdk.incidentconfiguration.RateConfig;
import com.hp.ov.nms.sdk.incidentconfiguration.RemoteNNMEventConfig;
import com.hp.ov.nms.sdk.incidentconfiguration.SnmpTrapConfig;


@Service(objectName = "com.hp.ov.nms.sdk.incidentconfig:mbean=IncidentConfigClient")
@Management(IncidentConfigClientMBean.class)
public class IncidentConfigClient extends SampleClient implements IncidentConfigClientMBean {
    private static final Logger log = Logger.getLogger(IncidentConfigClient.class.getName());
    protected   int filterIndex=0;
    protected Filter[] incidentConfigFilters=null;

    public IncidentConfigClient(){
        incidentConfigFilters=getCannedIncidentConfigFilters();
    }

    private Filter[] getCannedIncidentConfigFilters(){
         Filter[] filters=new Filter[]{new Constraint()};//empty constraint for now
       return filters;
    }


    public String listManagementEventIncidentConfig() {

        Filter filter=(filterIndex<incidentConfigFilters.length)?incidentConfigFilters[filterIndex]:incidentConfigFilters[0];
        NmsIncidentConfig service=getIncidentConfigService();
        String results="Name,Description,Category,Author,Severity,Message Format,Family,Enable\n";
        try{
            ManagementEventConfig[] configurations=service.getManagementEventConfig(new Filter());
            int count=configurations.length;
            for(int i=0;i<count;i++){
                results+=
                configurations[i].getName()+", "+
                configurations[i].getDescription()+", "+
                configurations[i].getCategory()+", "+
                configurations[i].getAuthor()+", "+
                configurations[i].getSeverity()+", "+
                configurations[i].getMessageFormat()+", "+
                configurations[i].getFamily()+",  "+
                configurations[i].getEnable()+" "+
                getDedupConfigDetails(configurations[i])+" "+
                getRateConfigDetails(configurations[i])+" "+
                getActionConfigDetails(configurations[i])+"\n\n";
            }

        }catch(NmsIncidentConfigException e){
            e.printStackTrace();
        }
        return results;
    }

    public String listPairwiseIncidentConfig() {

         Filter filter=(filterIndex<incidentConfigFilters.length)?incidentConfigFilters[filterIndex]:incidentConfigFilters[0];
         NmsIncidentConfig service=getIncidentConfigService();
         String results="Name, First Incident Name, Second Incident Name, Enable\n";
          try{
             PairwiseConfig[] configurations=service.getPairwiseConfig(filter);
             int count=configurations.length;
             for(int i=0;i<count;i++){
                 results+=
                 configurations[i].getName()+", "+
                 configurations[i].getFirstIncidentName()+", "+
                 configurations[i].getSecondIncidentName()+", "+
                 configurations[i].getEnable()+"\n"+
                 "PAIR ITEMS:";
                 PairItem[] pairItems=configurations[i].getPairItems();
                 if(pairItems!=null){
                    for(int j=0;j<pairItems.length;j++){
                         results+=" First in Pair:"+pairItems[j].getFirstInPair()+
                                  ";Second in Pair:"+pairItems[j].getSecondInPair();
                 }
                 results+="\n\n";
               }
                }
          }catch(NmsIncidentConfigException e){
             e.printStackTrace();
          }

        return results;
    }

    public String listRemoteEventIncidentConfig() {

        Filter filter=(filterIndex<incidentConfigFilters.length)?incidentConfigFilters[filterIndex]:incidentConfigFilters[0];
        NmsIncidentConfig service=getIncidentConfigService();
        String results="Oid,Name,Description,Category,Author,Severity,Message Format,Family,Enable\n";
        try{
             RemoteNNMEventConfig[] configurations=service.getRemoteEventConfig(filter);
             int count=configurations.length;
             for(int i=0;i<count;i++){
                results+=
                configurations[i].getOId()+", "+
                configurations[i].getName()+", "+
                configurations[i].getDescription()+", "+
                configurations[i].getCategory()+", "+
                configurations[i].getAuthor()+", "+
                configurations[i].getSeverity()+", "+
                configurations[i].getMessageFormat()+", "+
                configurations[i].getFamily()+",  "+
                configurations[i].getEnable()+" "+
                getDedupConfigDetails(configurations[i])+" "+
                getRateConfigDetails(configurations[i])+" "+
                getActionConfigDetails(configurations[i])+"\n\n";
            }

          }catch(NmsIncidentConfigException e){
              e.printStackTrace();
          }

          return results;
    }

    public String listSnmpTrapIncidentConfig() {
        Filter filter=(filterIndex<incidentConfigFilters.length)?incidentConfigFilters[filterIndex]:incidentConfigFilters[0];
        NmsIncidentConfig service=getIncidentConfigService();
        String results="Name,Oid,Description,Category,Author,Severity,Message Format,Family,Enable\n";
        try{
            SnmpTrapConfig[] configurations=service.getSnmpTrapConfig(new Filter());
            int count=configurations.length;
            for(int i=0;i<count;i++){
                results+=
                configurations[i].getName()+", "+
                configurations[i].getOId()+", "+
                configurations[i].getDescription()+", "+
                configurations[i].getCategory()+", "+
                configurations[i].getAuthor()+", "+
                configurations[i].getSeverity()+", "+
                configurations[i].getMessageFormat()+", "+
                configurations[i].getFamily()+",  "+
                configurations[i].getEnable()+" "+
                getDedupConfigDetails(configurations[i])+" "+
                getRateConfigDetails(configurations[i])+" "+
                getActionConfigDetails(configurations[i])+"\n\n";
            }

        }catch(NmsIncidentConfigException e){
            e.printStackTrace();
        }
        return results;
    }

     private String getDedupConfigDetails(IncidentConfig configuration){
          String results="\nDEDUPLICATE CONFIGURATION:";
          DedupConfig dedupConfig=configuration.getDedupConfig();
          results+="Enable:"+dedupConfig.getEnable()+","+
                   " Correlation Incident Configuration:"+dedupConfig.getCorrelationIncidentConfigName()+
                   " Comparison Criteria:"+dedupConfig.getComparisonCriteria()+",Comparison Parameters:";

          ComparisonParam[] compParams=dedupConfig.getComparisonParams();
          if(compParams!=null){
             for(int j=0;j<compParams.length;j++){
                   results+=compParams[j].getParamValue()+",";
            }
          }

         return results;
      }

      private String getRateConfigDetails(IncidentConfig configuration){
         String results="\nRATE CONFIGURATION:";
         RateConfig rateConfig=configuration.getRateConfig();
         results+="Enable:"+rateConfig.getEnable()+","+
                 " Count:"+rateConfig.getRateCount()+","+
                 " Hours:"+rateConfig.getHourInterval()+","+
                 " Minutes:"+rateConfig.getMinuteInterval()+","+
                 " Seconds:"+rateConfig.getSecondInterval()+","+
                 " Correlation Incident Configuration:"+rateConfig.getCorrelationIncidentConfigName()+
                 " Comparison Criteria:"+rateConfig.getComparisonCriteria()+",Comparison Parameters:";

         ComparisonParam[] compParams=rateConfig.getComparisonParams();
         if(compParams!=null){
            for(int k=0;k<compParams.length;k++){
              results+=compParams[k].getParamValue()+",";
             }
         }
          return results;
       }

      private String getActionConfigDetails(IncidentConfig configuration){
         String results="\nACTION CONFIGURATION:";
         ActionConfig actionConfig=configuration.getActionConfig();
         results+="Enable:"+actionConfig.getEnable()+";"+
                 "Lifecycle Transition Actions:";

         LifecycleTransitionAction[] actions=actionConfig.getActions();
         if(actions!=null){
            for(int n=0;n<actions.length;n++){
                results+="Command:"+actions[n].getCommand()+
                         "; Command Type:"+actions[n].getCommandType()+
                         "; Lifecycle State:"+actions[n].getLifeCycleState();
            }
        }
        return results;
      }
}
