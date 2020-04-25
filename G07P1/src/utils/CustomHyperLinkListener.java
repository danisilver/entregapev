package utils;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class CustomHyperLinkListener implements HyperlinkListener {
	@Override
	public void hyperlinkUpdate(final HyperlinkEvent pE) {
		if (HyperlinkEvent.EventType.ACTIVATED == pE.getEventType()) {
			String reference = pE.getDescription();
			JEditorPane jEditorPane = (JEditorPane) pE.getSource();
			if (reference != null && reference.startsWith("#")) {
				reference = reference.substring(1);
				jEditorPane.scrollToReference(reference);
			}
		}
	}
}
