package org.libreoffice.example.dialog;
import org.libreoffice.example.common.AESFileEncryption;
import org.libreoffice.example.common.Constant;
import org.libreoffice.example.helper.DialogHelper;

import com.sun.star.awt.XDialog;
import com.sun.star.awt.XDialogEventHandler;
import com.sun.star.lang.WrappedTargetException;
import com.sun.star.uno.XComponentContext;


public class ActionOneDialog implements XDialogEventHandler {
	
	private XDialog dialog;
	private static final String actionOk = "actionOk";
	private static final String actionSave = "actionSave";
	private String[] supportedActions = new String[] { actionOk,actionSave };
	
	public ActionOneDialog(XComponentContext xContext) {
		this.dialog = DialogHelper.createDialog("ActionOneDialog.xdl", xContext, this);	
	}
	
	public ActionOneDialog(XComponentContext xContext, String title) {
		this.dialog = DialogHelper.createDialog("ActionOneDialog.xdl", xContext, this);
		this.dialog.setTitle(title);
	}
	
	public void show() {
		dialog.execute();
	}
	
	private void onOkButtonPressed() {
		dialog.endExecute();
	}
	
	@Override
	public boolean callHandlerMethod(XDialog dialog, Object eventObject, String methodName) throws WrappedTargetException {
		if (methodName.equals(actionOk)) {
			onOkButtonPressed();
			return true; // Event was handled
		}
		if(methodName.equals(actionSave))
		{
			onOkButtonPressed();
//			AESFileEncryption aesFileEncryption = new AESFileEncryption(Constant.path, Constant.fileName);
			return true;
		}
		return false; // Event was not handled
	}

	@Override
	public String[] getSupportedMethodNames() {
		return supportedActions;
	}

}
