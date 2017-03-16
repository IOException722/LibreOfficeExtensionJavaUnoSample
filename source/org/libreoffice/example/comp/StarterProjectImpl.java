package org.libreoffice.example.comp;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import com.sun.star.lib.uno.helper.Factory;

import org.libreoffice.example.common.DisableCommandsTest;
import org.libreoffice.example.dialog.ActionOneDialog;
import org.libreoffice.example.helper.DialogHelper;

import com.sun.star.lang.XSingleComponentFactory;
import com.sun.star.registry.XRegistryKey;
import com.sun.star.lib.uno.helper.WeakBase;


import com.sun.star.document.XEventBroadcaster;
import com.sun.star.document.XEventListener;;

public final class StarterProjectImpl extends WeakBase
   implements com.sun.star.lang.XServiceInfo,com.sun.star.task.XJobExecutor
           {
	
    private final XComponentContext m_xContext;
    private static final String m_implementationName = StarterProjectImpl.class.getName();
    private static final String[] m_serviceNames = {
        "org.libreoffice.example.StarterProject"};
    Object xGlobalBroadCaster = null;
    XEventListener m_xEventListener = new EventListenerImpl();
    XEventBroadcaster xEventBroad = null;

    public StarterProjectImpl( XComponentContext context )
    {
        m_xContext = context;
    }
    private void registerListener()
    {
    	  try
          { 
              xGlobalBroadCaster = m_xContext.getServiceManager().createInstanceWithContext( 
                      "com.sun.star.frame.GlobalEventBroadcaster", 
                      m_xContext);
          }
          catch(Exception e2)
          {
              e2.printStackTrace();
          }
          xEventBroad = (XEventBroadcaster) UnoRuntime.queryInterface(XEventBroadcaster.class, xGlobalBroadCaster); 
          xEventBroad.addEventListener(m_xEventListener);
    }
    public static XSingleComponentFactory __getComponentFactory( String sImplementationName ) {
        XSingleComponentFactory xFactory = null;

        if ( sImplementationName.equals( m_implementationName ) )
            xFactory = Factory.createComponentFactory(StarterProjectImpl.class, m_serviceNames);
        return xFactory;
    }

    public static boolean __writeRegistryServiceInfo( XRegistryKey xRegistryKey ) {
    	
        return Factory.writeRegistryServiceInfo(m_implementationName,
                                                m_serviceNames,
                                                xRegistryKey);
    }
    
    // com.sun.star.lang.XServiceInfo:
    public String getImplementationName() {
         return m_implementationName;
    }
    
    public boolean supportsService( String sService ) {
        int len = m_serviceNames.length;

        for( int i=0; i < len; i++) {
            if (sService.equals(m_serviceNames[i]))
                return true;
        }
        return false;
    }

    public String[] getSupportedServiceNames() {
        return m_serviceNames;
    }
    // com.sun.star.task.XJobExecutor:
    public void trigger(String action)
    {
    	switch (action) {
    	case "actionOne":
    		ActionOneDialog actionOneDialog = new ActionOneDialog(m_xContext);
    		actionOneDialog.show();
    		break;
    	case "actionTwo":
    		ActionOneDialog actionTwoDialog = new ActionOneDialog(m_xContext);
    		actionTwoDialog.show();
    		break;
    	case "actionSave":
    		showDialog("Save");
//    		DisableCommandsTest.disableCommand();
    		break;
    	case "actionSaveAs":
    		showDialog("Save As");
    		break;
    	case "actionHelp":
    		showDialog("Help");
       		break;
    	default:
    		DialogHelper.showErrorMessage(m_xContext, null, "Unknown action: " + action);
    	}
        
    }
    private void showDialog(String title)
    {
    	ActionOneDialog actionSaveAsDialog = new ActionOneDialog(m_xContext, title);
		actionSaveAsDialog.show();
    }
     
    public void cleanup() {
           System.out.println("removing Listener");
           xEventBroad.removeEventListener(m_xEventListener);
           System.out.println("... done");
     }
    
    private class EventListenerImpl implements XEventListener {
    	        public void disposing(com.sun.star.lang.EventObject eventObject) {
    	             System.out.println("disposing: " + eventObject.Source.toString());
    	         }
    	        
    	        public void notifyEvent(com.sun.star.document.EventObject eventObject) {
    	        	 String eventName = eventObject.EventName;
    	        	 System.out.println(eventName);
    	        	 switch(eventName.toLowerCase())
    	        	 {
//    	        	 case "onsubcomponentclosed":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "onsubcomponentopened":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "ontitlechanged":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "onprepareviewclosing":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "onviewcreated":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "onunfocus":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "onmodifychanged":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "onfocus":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "onunload":
//    	        		 showDialog(eventName);
//    	        	 case "onprepareunload":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "onsavetofailed":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "onsavetodone":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "onsaveto":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "onsaveasfailed":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "onsavefailed":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "onloadfinished":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "oncreate":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "onclick":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "onalphacharinput":
//    	        		 showDialog(eventName);
//    	        		 break;
    	        	 case "onnew":
    	        		 showDialog(eventName);
    	        		 break;
    	        	 case "onload":
    	        		 showDialog(eventName);
    	        		 break;
    	        	 case "onsave":
    	        		 showDialog(eventName);
    	        		 break;
    	        	 case "onsaveas":
    	        		 showDialog(eventName);
    	        		 break;
//    	        	 case "onsavedone":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "onsaveasdone":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "onsavefinished":
//    	        		 showDialog(eventName);
//    	        		 break;
//    	        	 case "onviewclosed":
//    	        		 showDialog(eventName);
//    	        	 	 break;
    	        	 default:
    	        		 break;
    	        	 
    	        	 }
    	        	 
    	         }
    	     }	 

}
